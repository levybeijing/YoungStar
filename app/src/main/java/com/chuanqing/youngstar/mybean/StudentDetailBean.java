package com.chuanqing.youngstar.mybean;

public class StudentDetailBean {

    /**
     * data : {"code":"5253078439","flag":1,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"5262634081/201901251158373230.png","recommendTotal":814}
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
         * code : 5253078439
         * flag : 1
         * user_code : 5262634081
         * sex : 男
         * label : 模特 | 演员
         * user_img : 5262634081/201901251158373230.png
         * recommendTotal : 814
         */

        private String code;
        private int flag;
        private String user_code;
        private String sex;
        private String label;
        private String user_img;
        private int recommendTotal;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getUser_img() {
            return user_img;
        }

        public void setUser_img(String user_img) {
            this.user_img = user_img;
        }

        public int getRecommendTotal() {
            return recommendTotal;
        }

        public void setRecommendTotal(int recommendTotal) {
            this.recommendTotal = recommendTotal;
        }
    }
}
