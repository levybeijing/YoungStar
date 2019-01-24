package com.chuanqing.youngstar.mybean;

import java.util.List;

public class WorksBean {

    /**
     * data : {"collection_code":"768691857133","createTime":"2019-01-24 17:09:25","mediaTime":[{"create_time":1548349771000,"time":"刚刚","media":[{"createTime":"2019-01-24 17:09:31","id":286,"type":1,"media_url":"201901241709320.png","status":5},{"createTime":"2019-01-24 17:09:31","id":287,"type":1,"media_url":"201901241709321.png","status":5}]},{"create_time":1548349796000,"time":"刚刚","media":[{"createTime":"2019-01-24 17:09:56","id":288,"type":2,"media_url":"20190124170956.mp4","status":5}]}],"recommend":0,"time":"刚刚","status":5}
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
         * collection_code : 768691857133
         * createTime : 2019-01-24 17:09:25
         * mediaTime : [{"create_time":1548349771000,"time":"刚刚","media":[{"createTime":"2019-01-24 17:09:31","id":286,"type":1,"media_url":"201901241709320.png","status":5},{"createTime":"2019-01-24 17:09:31","id":287,"type":1,"media_url":"201901241709321.png","status":5}]},{"create_time":1548349796000,"time":"刚刚","media":[{"createTime":"2019-01-24 17:09:56","id":288,"type":2,"media_url":"20190124170956.mp4","status":5}]}]
         * recommend : 0
         * time : 刚刚
         * status : 5
         */

        private String collection_code;
        private String createTime;
        private int recommend;
        private String time;
        private int status;
        private List<MediaTimeBean> mediaTime;

        public String getCollection_code() {
            return collection_code;
        }

        public void setCollection_code(String collection_code) {
            this.collection_code = collection_code;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getRecommend() {
            return recommend;
        }

        public void setRecommend(int recommend) {
            this.recommend = recommend;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<MediaTimeBean> getMediaTime() {
            return mediaTime;
        }

        public void setMediaTime(List<MediaTimeBean> mediaTime) {
            this.mediaTime = mediaTime;
        }

        public static class MediaTimeBean {
            /**
             * create_time : 1548349771000
             * time : 刚刚
             * media : [{"createTime":"2019-01-24 17:09:31","id":286,"type":1,"media_url":"201901241709320.png","status":5},{"createTime":"2019-01-24 17:09:31","id":287,"type":1,"media_url":"201901241709321.png","status":5}]
             */

            private long create_time;
            private String time;
            private List<MediaBean> media;

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public List<MediaBean> getMedia() {
                return media;
            }

            public void setMedia(List<MediaBean> media) {
                this.media = media;
            }

            public static class MediaBean {
                /**
                 * createTime : 2019-01-24 17:09:31
                 * id : 286
                 * type : 1
                 * media_url : 201901241709320.png
                 * status : 5
                 */

                private String createTime;
                private int id;
                private int type;
                private String media_url;
                private int status;

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
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

                public String getMedia_url() {
                    return media_url;
                }

                public void setMedia_url(String media_url) {
                    this.media_url = media_url;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }
        }
    }
}
