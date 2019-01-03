package com.chuanqing.youngstar.mybean;

import java.util.List;

public class HomeActivityBean {

    /**
     * data : [{"currentTime":"报名截止时间:2020-01-01 00:00:00","activity_name":"活动1","start_time":"2019-01-01 00:00:00","end_time":"2020-01-01 00:00:00","line_time":"2020-01-01 00:00:00","activity_code":"145495544327","list_img":"1546417764.jpg","attendCount":0}]
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
         * currentTime : 报名截止时间:2020-01-01 00:00:00
         * activity_name : 活动1
         * start_time : 2019-01-01 00:00:00
         * end_time : 2020-01-01 00:00:00
         * line_time : 2020-01-01 00:00:00
         * activity_code : 145495544327
         * list_img : 1546417764.jpg
         * attendCount : 0
         */

        private String currentTime;
        private String activity_name;
        private String start_time;
        private String end_time;
        private String line_time;
        private String activity_code;
        private String list_img;
        private String attendCount;

        public String getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(String currentTime) {
            this.currentTime = currentTime;
        }

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

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getLine_time() {
            return line_time;
        }

        public void setLine_time(String line_time) {
            this.line_time = line_time;
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

        public String getAttendCount() {
            return attendCount;
        }

        public void setAttendCount(String attendCount) {
            this.attendCount = attendCount;
        }
    }
}
