package com.cursonjiang.mobilephone.bean;

/**
 * Created by Curson on 15/7/5.
 */
public class BlackNumberInfo {

    private String number;
    private String mode;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BlackNumberInfo{" +
                "mode='" + mode + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

}
