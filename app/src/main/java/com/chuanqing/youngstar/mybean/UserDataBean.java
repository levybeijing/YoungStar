package com.chuanqing.youngstar.mybean;

public class UserDataBean {

    /**
     * data : {"qq":"","user_code":"5262634081","wb_code":"","user_img":"qweqw","wx_code":"","mail":""}
     * message : 请求成功
     * state : 1
     */

    private DataBean data;
    private String message;
    private int state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * qq :
         * user_code : 5262634081
         * wb_code :
         * user_img : qweqw
         * wx_code :
         * mail :
         */

        private String qq;
        private String user_code;
        private String wb_code;
        private String user_img;
        private String wx_code;
        private String mail;

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getWb_code() {
            return wb_code;
        }

        public void setWb_code(String wb_code) {
            this.wb_code = wb_code;
        }

        public String getUser_img() {
            return user_img;
        }

        public void setUser_img(String user_img) {
            this.user_img = user_img;
        }

        public String getWx_code() {
            return wx_code;
        }

        public void setWx_code(String wx_code) {
            this.wx_code = wx_code;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }
    }
}
