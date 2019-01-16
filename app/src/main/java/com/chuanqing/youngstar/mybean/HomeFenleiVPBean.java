package com.chuanqing.youngstar.mybean;

import java.util.List;

public class HomeFenleiVPBean {


    /**
     * data : [{"deatil":[{"img":"20181203135201.png","user_code":"8572451327","local_desc":1,"detailId":7},{"img":"20181203135201.png","user_code":"8219127408","local_desc":2,"detailId":8},{"img":"20181203135201.png","user_code":"8572451327","local_desc":3,"detailId":9},{"img":"20181203135201.png","user_code":"8219127408","local_desc":4,"detailId":10},{"img":"20181203135201.png","user_code":"8572451327","local_desc":5,"detailId":11},{"img":"20181203135201.png","user_code":"8572451327","local_desc":6,"detailId":12}],"name":"歌手","local_desc":1,"id":2},{"deatil":[{"img":"20181203135201.png","user_code":"8572451327","local_desc":1,"detailId":13},{"img":"20181203135201.png","user_code":"8219127408","local_desc":2,"detailId":14},{"img":"20181203135201.png","user_code":"8219127408","local_desc":3,"detailId":15},{"img":"20181203135201.png","user_code":"8572451327","local_desc":4,"detailId":16},{"img":"20181203135201.png","user_code":"8572451327","local_desc":5,"detailId":17},{"img":"20181203135201.png","user_code":"8219127408","local_desc":6,"detailId":18}],"name":"编剧","local_desc":2,"id":3},{"deatil":[{"img":"20181203135201.png","user_code":"8219127408","local_desc":1,"detailId":1},{"img":"20181203135201.png","user_code":"8572451327","local_desc":2,"detailId":2},{"img":"20181203135201.png","user_code":"8219127408","local_desc":3,"detailId":3},{"img":"20181203135201.png","user_code":"8219127408","local_desc":4,"detailId":4},{"img":"20181203135201.png","user_code":"8219127408","local_desc":5,"detailId":5},{"img":"20181203135201.png","user_code":"8219127408","local_desc":6,"detailId":6}],"name":"演员","local_desc":3,"id":1}]
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
         * deatil : [{"img":"20181203135201.png","user_code":"8572451327","local_desc":1,"detailId":7},{"img":"20181203135201.png","user_code":"8219127408","local_desc":2,"detailId":8},{"img":"20181203135201.png","user_code":"8572451327","local_desc":3,"detailId":9},{"img":"20181203135201.png","user_code":"8219127408","local_desc":4,"detailId":10},{"img":"20181203135201.png","user_code":"8572451327","local_desc":5,"detailId":11},{"img":"20181203135201.png","user_code":"8572451327","local_desc":6,"detailId":12}]
         * name : 歌手
         * local_desc : 1
         * id : 2
         */

        private String name;
        private int local_desc;
        private int id;
        private List<DeatilBean> deatil;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLocal_desc() {
            return local_desc;
        }

        public void setLocal_desc(int local_desc) {
            this.local_desc = local_desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<DeatilBean> getDeatil() {
            return deatil;
        }

        public void setDeatil(List<DeatilBean> deatil) {
            this.deatil = deatil;
        }

        public static class DeatilBean {
            /**
             * img : 20181203135201.png
             * user_code : 8572451327
             * local_desc : 1
             * detailId : 7
             */

            private String img;
            private String user_code;
            private int local_desc;
            private int detailId;

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

            public int getLocal_desc() {
                return local_desc;
            }

            public void setLocal_desc(int local_desc) {
                this.local_desc = local_desc;
            }

            public int getDetailId() {
                return detailId;
            }

            public void setDetailId(int detailId) {
                this.detailId = detailId;
            }
        }
    }
}
