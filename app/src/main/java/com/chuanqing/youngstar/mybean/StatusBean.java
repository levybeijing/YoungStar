package com.chuanqing.youngstar.mybean;

import java.util.List;

/**
 * 获取身份
 */
public class StatusBean {

    /**
     * data : [{"name":"歌手","local_desc":1,"id":2},{"name":"编剧","local_desc":2,"id":3},{"name":"演员","local_desc":3,"id":1}]
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
         * name : 歌手
         * local_desc : 1
         * id : 2
         */

        private String name;
        private int local_desc;
        private int id;

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
    }
}

