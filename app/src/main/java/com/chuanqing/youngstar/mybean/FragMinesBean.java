package com.chuanqing.youngstar.mybean;

public class FragMinesBean {

    /**
     * data : {"standard_coin":10000000,"user_code":"8572451327","userRecommend":875,"sex":"男","userConcernNum":17,"label":"演员 | 歌手","type":4,"user_img":"8572451327/201901141613583226.png","activityUserNum":8}
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
         * standard_coin : 10000000
         * user_code : 8572451327
         * userRecommend : 875
         * sex : 男
         * userConcernNum : 17
         * label : 演员 | 歌手
         * type : 4
         * user_img : 8572451327/201901141613583226.png
         * activityUserNum : 8
         */

        private int standard_coin;
        private String user_code;
        private int userRecommend;
        private String sex;
        private int userConcernNum;
        private String label;
        private int type;
        private String user_img;
        private int activityUserNum;

        public int getStandard_coin() {
            return standard_coin;
        }

        public void setStandard_coin(int standard_coin) {
            this.standard_coin = standard_coin;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public int getUserRecommend() {
            return userRecommend;
        }

        public void setUserRecommend(int userRecommend) {
            this.userRecommend = userRecommend;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getUserConcernNum() {
            return userConcernNum;
        }

        public void setUserConcernNum(int userConcernNum) {
            this.userConcernNum = userConcernNum;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
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

        public int getActivityUserNum() {
            return activityUserNum;
        }

        public void setActivityUserNum(int activityUserNum) {
            this.activityUserNum = activityUserNum;
        }
    }
}
