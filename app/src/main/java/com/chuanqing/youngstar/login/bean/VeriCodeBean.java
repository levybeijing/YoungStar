package com.chuanqing.youngstar.login.bean;

public class VeriCodeBean {

    /**
     * message : 验证码失效或验证码错误
     * state : 0
     */

    private String message;
    private int state;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
