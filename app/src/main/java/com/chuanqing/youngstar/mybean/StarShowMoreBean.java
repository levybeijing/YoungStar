package com.chuanqing.youngstar.mybean;

public class StarShowMoreBean {

    /**
     * data : {"flag":0,"user_code":"9590485059","createTime":"2018-12-15 20:39:05","blog_detail":"51251254asdfe1wesd","time":"2018-12-15","type":1,"title":"14124","media_url":"output-2018-12-21-13:34:43-567.mp4","user_img":"123","userCode":"145495544327"}
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
         * flag : 0
         * user_code : 9590485059
         * createTime : 2018-12-15 20:39:05
         * blog_detail : 51251254asdfe1wesd
         * time : 2018-12-15
         * type : 1
         * title : 14124
         * media_url : output-2018-12-21-13:34:43-567.mp4
         * user_img : 123
         * userCode : 145495544327
         */

        private int flag;
        private String user_code;
        private String createTime;
        private String blog_detail;
        private String time;
        private String type;
        private String title;
        private String media_url;
        private String user_img;
        private String userCode;
        private String label;
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getBlog_detail() {
            return blog_detail;
        }

        public void setBlog_detail(String blog_detail) {
            this.blog_detail = blog_detail;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMedia_url() {
            return media_url;
        }

        public void setMedia_url(String media_url) {
            this.media_url = media_url;
        }

        public String getUser_img() {
            return user_img;
        }

        public void setUser_img(String user_img) {
            this.user_img = user_img;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }
    }
}
