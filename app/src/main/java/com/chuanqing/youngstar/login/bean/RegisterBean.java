package com.chuanqing.youngstar.login.bean;

public class RegisterBean {

    /**
     * data : {"password":"33054eacef5156f411036507080aceaf","picture_switch":1,"user_code":"5253078439","video_switch":1,"createTime":"2019-01-07 15:38:52","music_switch":1,"mobile":"13302168008","type":4,"user_img":"20181203135201.png","shock_switch":1,"status":2}
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
         * password : 33054eacef5156f411036507080aceaf
         * picture_switch : 1
         * user_code : 5253078439
         * video_switch : 1
         * createTime : 2019-01-07 15:38:52
         * music_switch : 1
         * mobile : 13302168008
         * type : 4
         * user_img : 20181203135201.png
         * shock_switch : 1
         * status : 2
         */

        private String password;
        private int picture_switch;
        private String user_code;
        private int video_switch;
        private String createTime;
        private int music_switch;
        private String mobile;
        private int type;
        private String user_img;
        private int shock_switch;
        private int status;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getPicture_switch() {
            return picture_switch;
        }

        public void setPicture_switch(int picture_switch) {
            this.picture_switch = picture_switch;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public int getVideo_switch() {
            return video_switch;
        }

        public void setVideo_switch(int video_switch) {
            this.video_switch = video_switch;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getMusic_switch() {
            return music_switch;
        }

        public void setMusic_switch(int music_switch) {
            this.music_switch = music_switch;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUser_img() {
            return user_img;
        }

        public void setUser_img(String user_img) {
            this.user_img = user_img;
        }

        public int getShock_switch() {
            return shock_switch;
        }

        public void setShock_switch(int shock_switch) {
            this.shock_switch = shock_switch;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
