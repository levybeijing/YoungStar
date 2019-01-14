package com.chuanqing.youngstar.mybean;

public class ZhaopingMoreBean {

    /**
     * data : {"benefits":"酒店住宿","img":"20181203135201.png,","requirements":"12312312312124141251123","num":"0-10人","title":"招聘","salary":"1000-2000元","experience":0,"user_img":"20181203135201.png","user_code":"8572451327","major":"演员","employ_code":"123123","location":"天津市","position":"男一"}
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
         * benefits : 酒店住宿
         * img : 20181203135201.png,
         * requirements : 12312312312124141251123
         * num : 0-10人
         * title : 招聘
         * salary : 1000-2000元
         * experience : 0
         * user_img : 20181203135201.png
         * user_code : 8572451327
         * major : 演员
         * employ_code : 123123
         * location : 天津市
         * position : 男一
         */

        private String benefits;
        private String img;
        private String requirements;
        private String num;
        private String title;
        private String salary;
        private String experience;
        private String user_img;
        private String user_code;
        private String major;
        private String employ_code;
        private String location;
        private String position;

        public String getBenefits() {
            return benefits;
        }

        public void setBenefits(String benefits) {
            this.benefits = benefits;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getRequirements() {
            return requirements;
        }

        public void setRequirements(String requirements) {
            this.requirements = requirements;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getUser_img() {
            return user_img;
        }

        public void setUser_img(String user_img) {
            this.user_img = user_img;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getEmploy_code() {
            return employ_code;
        }

        public void setEmploy_code(String employ_code) {
            this.employ_code = employ_code;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }
    }
}
