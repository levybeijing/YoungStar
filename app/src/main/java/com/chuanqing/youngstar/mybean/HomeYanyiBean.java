package com.chuanqing.youngstar.mybean;

import java.util.List;

public class HomeYanyiBean {

    /**
     * data : [{"img":"20181203135201.png,","user_code":"8572451327","employ_code":"123123","attendCount":0,"title":"招聘","user_img":"20181203135201.png"}]
     * message : 请求成功
     * state : 1
     */

    private String message;
    private int state;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * img : 20181203135201.png,
         * user_code : 8572451327
         * employ_code : 123123
         * attendCount : 0
         * title : 招聘
         * user_img : 20181203135201.png
         */

        private String img;
        private String user_code;
        private String employ_code;
        private int attendCount;
        private String title;
        private String user_img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getEmploy_code() {
            return employ_code;
        }

        public void setEmploy_code(String employ_code) {
            this.employ_code = employ_code;
        }

        public int getAttendCount() {
            return attendCount;
        }

        public void setAttendCount(int attendCount) {
            this.attendCount = attendCount;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUser_img() {
            return user_img;
        }

        public void setUser_img(String user_img) {
            this.user_img = user_img;
        }
    }
}
