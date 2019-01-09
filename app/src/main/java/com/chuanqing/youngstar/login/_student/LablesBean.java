package com.chuanqing.youngstar.login._student;

import java.util.List;

public class LablesBean {

    /**
     * data : ["演员","歌手","配音","编辑"]
     * message : 请求成功
     * state : 1
     */

    private String message;
    private int state;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
