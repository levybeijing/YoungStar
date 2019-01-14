package com.chuanqing.youngstar.mybean;

import java.util.List;

public class StarBangMoreBean {

    /**
     * data : {"activity_name":"1111","start_time":1545609600000,"micro_img":"1545727619.jpg","detail_img":"1545727621.jpg","student":[{"user_code":"0723499476","recommend":1,"user_img":"123"},{"user_code":"8219127408","recommend":1,"user_img":"20181203135201.png"},{"user_code":"8572451327","recommend":1,"user_img":"20181203135201.png"}],"activity_introduce":"123456123","end_time":1546300800000,"activity_detail":"1545727628.jpg"}
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
         * activity_name : 1111
         * start_time : 1545609600000
         * micro_img : 1545727619.jpg
         * detail_img : 1545727621.jpg
         * student : [{"user_code":"0723499476","recommend":1,"user_img":"123"},{"user_code":"8219127408","recommend":1,"user_img":"20181203135201.png"},{"user_code":"8572451327","recommend":1,"user_img":"20181203135201.png"}]
         * activity_introduce : 123456123
         * end_time : 1546300800000
         * activity_detail : 1545727628.jpg
         */

        private String activity_name;
        private String start_time;
        private String micro_img;
        private String detail_img;
        private String activity_introduce;
        private String end_time;
        private String activity_detail;
        private List<StudentBean> student;

        public String getActivity_name() {
            return activity_name;
        }

        public void setActivity_name(String activity_name) {
            this.activity_name = activity_name;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getMicro_img() {
            return micro_img;
        }

        public void setMicro_img(String micro_img) {
            this.micro_img = micro_img;
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

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getActivity_detail() {
            return activity_detail;
        }

        public void setActivity_detail(String activity_detail) {
            this.activity_detail = activity_detail;
        }

        public List<StudentBean> getStudent() {
            return student;
        }

        public void setStudent(List<StudentBean> student) {
            this.student = student;
        }

        public static class StudentBean {
            /**
             * user_code : 0723499476
             * recommend : 1
             * user_img : 123
             */

            private String user_code;
            private int recommend;
            private String user_img;

            public String getUser_code() {
                return user_code;
            }

            public void setUser_code(String user_code) {
                this.user_code = user_code;
            }

            public int getRecommend() {
                return recommend;
            }

            public void setRecommend(int recommend) {
                this.recommend = recommend;
            }

            public String getUser_img() {
                return user_img;
            }

            public void setUser_img(String user_img) {
                this.user_img = user_img;
            }
        }
    }
}
