package com.chuanqing.youngstar.mybean;

import java.util.List;

public class HomeLunboBean {

    /**
     * data : [{"img":"1545207069.jpg","create_time":1546091913000,"id":5,"type":2,"url":"www.wowo3.com"},{"img":"1545358696.jpg","create_time":1545387493000,"id":13,"type":2,"url":"www.taobao.com"},{"img":"1545381401.jpg","create_time":1545410203000,"id":14,"type":2,"url":"www.taoyao.com"},{"img":"1545206886.jpg","create_time":1545235687000,"id":30,"type":1,"url":"www.baixiao.com"},{"img":"1545358628.jpg","create_time":1545387441000,"id":32,"type":1,"url":"www.woka.com"},{"img":"1545716860.jpg","create_time":1545745660000,"id":38,"type":2,"url":"www.fun.com"},{"img":"1545718157.jpg","create_time":1545746960000,"id":39,"type":1,"url":"www.loko.com"},{"img":"1545717944.jpg","create_time":1545746746000,"id":40,"type":1,"url":"www.mango.com"},{"img":"1546050626.jpg","create_time":1546079427000,"id":42,"type":2,"url":"21421"}]
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
         * img : 1545207069.jpg
         * create_time : 1546091913000
         * id : 5
         * type : 2
         * url : www.wowo3.com
         */

        private String img;
        private long create_time;
        private int id;
        private int type;
        private String url;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
