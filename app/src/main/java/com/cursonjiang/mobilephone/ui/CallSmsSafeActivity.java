package com.cursonjiang.mobilephone.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cursonjiang.mobilephone.App;
import com.cursonjiang.mobilephone.R;
import com.cursonjiang.mobilephone.bean.BlackNumberInfo;
import com.cursonjiang.mobilephone.db.BlackNumberDao;
import com.cursonjiang.mobilephone.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * 通讯卫士页面
 * Created by Curson on 15/7/5.
 */
public class CallSmsSafeActivity extends BaseActivity {

    private static final String TAG = "ListView";
    private LinearLayout dialogView;
    private CallSmsSafeAdapter mAdapter;
    private List<BlackNumberInfo> mInfoList;
    private BlackNumberDao dao;

    private LinearLayout ll_loading;
    private AlertDialog dialog;
    private ListView mListView;
    private TextView tv_state;
    private EditText et_blackNumber;
    private CheckBox cb_phone;
    private CheckBox cb_sms;
    private Button canel;
    private Button ok;

    private int startIndex = 0;
    private final static int maxnumber = 20;

    /**
     * 一共有多少条数据
     */
    private int totalNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_sms_safe);
        initView();
        setTitle("通讯卫士");

        dao = new BlackNumberDao(App.getContext());
        totalNumber = dao.findTotalNumber();

        fillData();

        //ListView注册一个滚动事件的监听器
        mListView.setOnScrollListener(
                new AbsListView.OnScrollListener() {

                    //当ListView滚动的状态发生变化的时候调用
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        switch (scrollState) {

                            //静止状态
                            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                                tv_state.setVisibility(View.INVISIBLE);
                                //获取最后一个可见条目在集合里面的位置
                                int lastVisiblePosition = mListView.getLastVisiblePosition();
                                Logger.d("最后一个条目的位置", lastVisiblePosition + "");

                                //假设集合里面有20个item 位置从0开始的最后一个条目的位置是19
                                if (lastVisiblePosition == (mInfoList.size() - 1)) {
                                    Logger.d(TAG, "列表被移动到了最后一个位置,要加载更多的数据");
                                    fillData();
                                }
                                startIndex += maxnumber;
                                if (startIndex >= totalNumber) {
                                    ToastUtils.showToast(CallSmsSafeActivity.this, "没有更多数据了~", Toast.LENGTH_SHORT);
                                    return;
                                }
                                break;

                            //惯性滑行状态
                            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                                break;

                            //触摸滚动状态
                            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                                break;
                        }
                    }

                    //当ListView滚动的时候调用的方法
                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                        if (mInfoList != null) {
//                            tv_state.setVisibility(View.VISIBLE);
//                            tv_state.setText(mInfoList.get(firstVisibleItem).getNumber());
//                        }
                    }
                }
        );
    }

    /**
     * 获取数据
     */
    private void fillData() {
        ll_loading.setVisibility(View.VISIBLE);
        new Thread() {
            @Override
            public void run() {
                //如果集合中没有数据
                if (mInfoList == null) {
                    //从数据库查询所有数据
                    mInfoList = dao.findPart(startIndex, maxnumber);
                } else {//如果已经加载过数据了
                    //把新的数据加载到集合末尾
                    mInfoList.addAll(dao.findPart(startIndex, maxnumber));
                }
                runOnUiThread(
                        new Runnable() {
                            @Override
                            public void run() {
                                if (mAdapter == null) {
                                    mAdapter = new CallSmsSafeAdapter();
                                    mListView.setAdapter(mAdapter);
                                } else {
                                    mAdapter.notifyDataSetChanged();
                                }
                                ll_loading.setVisibility(View.INVISIBLE);
                            }
                        }
                );
            }
        }.start();
    }

    @SuppressLint("InflateParams")
    @Override
    public void initView() {
        mListView = (ListView) findViewById(R.id.lv_callsms_safe);
        ll_loading = (LinearLayout) findViewById(R.id.ll_loading);

        tv_state = (TextView) findViewById(R.id.tv_state);

        //diglog的控件
        dialogView = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_add_blacknumber, null);
        et_blackNumber = (EditText) dialogView.findViewById(R.id.et_black_number);
        cb_phone = (CheckBox) dialogView.findViewById(R.id.cb_phone);
        cb_sms = (CheckBox) dialogView.findViewById(R.id.cb_sms);
        canel = (Button) dialogView.findViewById(R.id.black_cancel);
        ok = (Button) dialogView.findViewById(R.id.black_ok);
    }

    private class CallSmsSafeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mInfoList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("InflateParams")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(App.getContext()).inflate(R.layout.list_item_callsms, null);
                viewHolder.tv_black_number = (TextView) convertView.findViewById(R.id.tv_black_number);
                viewHolder.tv_black_mode = (TextView) convertView.findViewById(R.id.tv_black_mode);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv_black_number.setText(mInfoList.get(position).getNumber());
            String mode = mInfoList.get(position).getMode();
            switch (mode) {
                case "1":
                    viewHolder.tv_black_mode.setText("短信拦截");
                    break;
                case "2":
                    viewHolder.tv_black_mode.setText("电话拦截");
                    break;
                case "3":
                    viewHolder.tv_black_mode.setText("全部拦截");
                    break;
                default:
                    break;
            }
            return convertView;
        }
    }

    static class ViewHolder {
        TextView tv_black_number;
        TextView tv_black_mode;
    }

    /**
     * 弹出添加黑名单号码的对话框
     */
    public void addBlackNumber() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog = builder.create();
        dialog.setView(dialogView, 0, 0, 0, 0);
        canel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        //对话框关闭了同时需要把对话框从父布局移除
                        ((ViewGroup) dialogView.getParent()).removeView(dialogView);
                    }
                }
        );

        ok.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String blackNumber = et_blackNumber.getText().toString().trim();
                        if (TextUtils.isEmpty(blackNumber)) {
                            ToastUtils.showToast(CallSmsSafeActivity.this, "号码不能为空~", Toast.LENGTH_SHORT);
                            return;
                        }
                        String mode;
                        if (cb_sms.isChecked()) {
                            //电话拦截
                            mode = "1";
                        } else if (cb_phone.isChecked()) {
                            //短信拦截
                            mode = "2";
                        } else if (cb_phone.isChecked() & cb_sms.isChecked()) {
                            //全部拦截
                            mode = "3";
                        } else {
                            //没有选中
                            ToastUtils.showToast(CallSmsSafeActivity.this, "请选择拦截模式~", Toast.LENGTH_SHORT);
                            return;
                        }
                        //添加到数据库
                        dao.add(blackNumber, mode);

                        //添加到集合中
                        BlackNumberInfo info = new BlackNumberInfo();
                        info.setNumber(blackNumber);
                        info.setMode(mode);

                        //添加到第一个位置
                        mInfoList.add(0, info);

                        //数据添加到集合之后通过adapter更新ListView
                        mAdapter.notifyDataSetChanged();

                        //把输入框和复选框置为空
                        et_blackNumber.setText("");
                        cb_sms.setChecked(false);
                        cb_phone.setChecked(false);

                        //添加数据的时候,数据总数也加一次
                        totalNumber++;

                        //关闭dialog
                        dialog.dismiss();

                        //对话框关闭了同时需要把对话框从父布局移除
                        ((ViewGroup) dialogView.getParent()).removeView(dialogView);
                    }
                }
        );
        dialog.show();
    }

    /**
     * 创建菜单
     *
     * @param menu 菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 某项菜单被选中
     *
     * @param item 菜单项
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                addBlackNumber();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
