package com.chuanqing.youngstar.mybean;

public class LeitaiMoreBean {
    /**
     * data : {"activity_name":"活动1","detail_img":"1546417769.jpg","activity_introduce":"活动1简介啦啦啦","activity_detail":"1546417780.jpg","id":36,"activity_code":"145495544327","list_img":"1546417764.jpg"}
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
         * activity_name : 活动1
         * detail_img : 1546417769.jpg
         * activity_introduce : 活动1简介啦啦啦
         * activity_detail : 1546417780.jpg
         * id : 36
         * activity_code : 145495544327
         * list_img : 1546417764.jpg
         */

        private String activity_name;
        private String detail_img;
        private String activity_introduce;
        private String activity_detail;
        private int id;
        private String activity_code;
        private String list_img;
        private String flag;
        private String userFlag;

        public String getUserFlag() {
            return userFlag;
        }

        public void setUserFlag(String userFlag) {
            this.userFlag = userFlag;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getActivity_name() {
            return activity_name;
        }

        public void setActivity_name(String activity_name) {
            this.activity_name = activity_name;
        }

        public String getDetail_img() {
            return detail_img;
        }

        public void setDetail_img(String detail_img) {
            this.detail_img = detail_img;
        }

        public String getActivity_introduce() {
            return activity_introduce;
        }

        public void setActivity_introduce(String activity_introduce) {
            this.activity_introduce = activity_introduce;
        }

        public String getActivity_detail() {
            return activity_detail;
        }

        public void setActivity_detail(String activity_detail) {
            this.activity_detail = activity_detail;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getActivity_code() {
            return activity_code;
        }

        public void setActivity_code(String activity_code) {
            this.activity_code = activity_code;
        }

        public String getList_img() {
            return list_img;
        }

        public void setList_img(String list_img) {
            this.list_img = list_img;
        }
    }
}
