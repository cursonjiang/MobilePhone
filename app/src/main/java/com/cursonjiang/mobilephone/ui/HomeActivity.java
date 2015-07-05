package com.cursonjiang.mobilephone.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cursonjiang.mobilephone.R;

/**
 * 主页面
 * Created by root on 15/6/9.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "HomeActivity";

    private AlertDialog mAlertDialog;

    private EditText et_setup_pwd;
    private EditText et_setup_pwd_confirm;

    private Button ok;
    private Button cancel;

    private GridView list_home;

    private SharedPreferences mSharedPreferences;

    /**
     * 每个Item的名字
     */
    private static String[] names = {
            "手机防盗", "通讯卫士", "软件管理",
            "进程管理", "流量统计", "手机杀毒",
            "缓存清理", "高级工具", "设置中心"
    };

    /**
     * 每个Item的图片
     */
    private static int[] images = {
            R.drawable.safe,
            R.drawable.callmsgsafe,
            R.drawable.app,
            R.drawable.taskmanager,
            R.drawable.netmanager,
            R.drawable.trojan,
            R.drawable.sysoptimize,
            R.drawable.atools,
            R.drawable.settings
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        mSharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        setTitle("功能列表");
    }

    @Override
    public void initView() {
        list_home = (GridView) findViewById(R.id.list_home);
        list_home.setAdapter(new MyAdapter());
        list_home.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                //进入手机防盗页面
                                showLoatFindDialog();
                                break;
                            case 1:
                                //加载黑名单拦截界面
                                startActivity(new Intent(HomeActivity.this, CallSmsSafeActivity.class));
                                break;
                            case 8:
                                //设置中心
                                startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                                break;
                        }
                    }
                }
        );
    }

    private void showLoatFindDialog() {
        //判断是否设置过密码
        if (isSetupPwd()) {
            //已经设置密码,弹出输入对话框
            showEnterDialog();
        } else {
            //没有设置密码,弹出设置密码对话框
            showSetupPwdDialog();
        }
    }

    /**
     * 设置密码对话框
     */
    private void showSetupPwdDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        //自定义一个布局文件
        View view = View.inflate(HomeActivity.this, R.layout.dialog_setup_password, null);

        et_setup_pwd = (EditText) view.findViewById(R.id.et_setup_pwd);
        et_setup_pwd_confirm = (EditText) view.findViewById(R.id.et_setup_confirm);
        ok = (Button) view.findViewById(R.id.ok);
        cancel = (Button) view.findViewById(R.id.cancel);

        cancel.setOnClickListener(this);
        ok.setOnClickListener(this);

        mAlertDialog = builder.create();
        mAlertDialog.setView(view, 0, 0, 0, 0);
        mAlertDialog.show();
    }

    /**
     * 输入密码对话框
     */
    private void showEnterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        View view = View.inflate(HomeActivity.this, R.layout.dialog_input_password, null);
        et_setup_pwd = (EditText) view.findViewById(R.id.et_setup_pwd);
        ok = (Button) view.findViewById(R.id.ok);
        cancel = (Button) view.findViewById(R.id.cancel);

        ok.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String pwd = et_setup_pwd.getText().toString().trim();
                        String password = mSharedPreferences.getString("password", "");
                        if (TextUtils.isEmpty(pwd)) {
                            Toast.makeText(HomeActivity.this, "密码不能为空~", Toast.LENGTH_SHORT).show();
                        }
                        if (pwd.equals(password)) {
                            mAlertDialog.dismiss();
                            Log.d(TAG, "进入主页面");
                        } else {
                            Toast.makeText(HomeActivity.this, "密码错误~", Toast.LENGTH_SHORT).show();
                            et_setup_pwd.setText("");
                        }
                    }
                }
        );
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAlertDialog.dismiss();
                    }
                }
        );
        mAlertDialog = builder.create();
        mAlertDialog.setView(view, 0, 0, 0, 0);
        mAlertDialog.show();
    }

    /**
     * 是否设置密码
     *
     * @return true or false
     */
    private boolean isSetupPwd() {
        String password = mSharedPreferences.getString("password", null);
//        if (TextUtils.isEmpty(password)) {
//            return false;
//        } else {
//            return true;
//        }
        return !TextUtils.isEmpty(password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                mAlertDialog.dismiss();
                break;
            case R.id.ok:
                String pwd = et_setup_pwd.getText().toString().trim();
                String pwd_confirm = et_setup_pwd_confirm.getText().toString().trim();
                //判断两个是否为空
                if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd_confirm)) {
                    Toast.makeText(HomeActivity.this, "密码不能为空~", Toast.LENGTH_SHORT).show();
                }
                if (pwd.equals(pwd_confirm)) {
                    mSharedPreferences.edit().putString("password", pwd).apply();
                    mAlertDialog.dismiss();
                    Log.d(TAG, "密码一致");
                } else {
                    Toast.makeText(HomeActivity.this, "密码不一致~", Toast.LENGTH_SHORT).show();

                }

        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return names.length;
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
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_item_home, null);
                viewHolder = new ViewHolder();
                viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.iv_item);
                viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_item);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.mTextView.setText(names[position]);
            viewHolder.mImageView.setImageResource(images[position]);
            return convertView;
        }
    }

    private class ViewHolder {
        ImageView mImageView;
        TextView mTextView;
    }

    private long exitTime = 0;

    //点击两次退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 1500) {
                Toast.makeText(HomeActivity.this, "再点一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
