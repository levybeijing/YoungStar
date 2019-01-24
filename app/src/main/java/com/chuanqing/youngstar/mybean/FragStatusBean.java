package com.chuanqing.youngstar.mybean;

import java.util.List;

public class FragStatusBean {

    /**
     * data : {"pageInfo":{"endRow":15,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":2,"list":[{"blog_img":"20190124103846.png","media_type":1,"createTime":"2019-01-24 10:38:45","num":2,"blog_detail":"啊","recommend":0,"id":91,"time":"41分钟前","title":"测试2","media_url":"201901241038460.png,201901241038461.png"},{"blog_img":"20190124103659.png","media_type":2,"createTime":"2019-01-24 10:36:59","num":1,"blog_detail":"哈哈","recommend":0,"id":90,"time":"43分钟前","title":"薛阳","media_url":"20190124103659.mp4"},{"blog_img":"20190123163740.png","media_type":1,"createTime":"2019-01-23 16:37:40","num":2,"blog_detail":"哈哈哈","recommend":0,"id":86,"time":"18小时前","title":"哈哈哈","media_url":"201901231637400.png,201901231637401.png"},{"blog_img":"20190123160912.png","media_type":1,"createTime":"2019-01-23 16:09:11","num":7,"blog_detail":"测试","recommend":0,"id":85,"time":"19小时前","title":"哈哈","media_url":"201901231609122.png,201901231609120.png,201901231609121.png,201901231609123.png,201901231609125.png,201901231609126.png,201901231609127.png"},{"blog_img":"20190123160458.png","media_type":1,"createTime":"2019-01-23 16:04:58","num":8,"blog_detail":"酒窖","recommend":0,"id":84,"time":"19小时前","title":"测试一下","media_url":"null201901231604582.png,201901231604580.png,201901231604583.png,201901231604581.png,201901231604584.png,201901231604586.png,201901231604585.png,201901231604587.png"},{"blog_img":"20190123160055.png","media_type":1,"createTime":"2019-01-23 16:00:55","num":6,"blog_detail":"沆瀣一气","recommend":0,"id":83,"time":"19小时前","title":"茕茕孑立","media_url":"null201901231600554.png,201901231600550.png,201901231600551.png,201901231600552.png,201901231600553.png,201901231600557.png"},{"blog_img":"20190123155742.png","media_type":1,"createTime":"2019-01-23 15:57:42","num":1,"blog_detail":"哈哈","recommend":0,"id":82,"time":"19小时前","title":"旮旯","media_url":"201901231557433.png"},{"blog_img":"haha","media_type":1,"createTime":"2019-01-23 15:55:23","num":1,"blog_detail":"","recommend":0,"id":81,"time":"19小时前","title":"","media_url":"201901231555242.png"},{"media_type":3,"createTime":"2019-01-23 14:19:44","num":1,"blog_detail":"太急","recommend":0,"id":80,"time":"21小时前","title":"天津","media_url":"20190123141944.mp4"},{"media_type":3,"createTime":"2019-01-23 14:17:08","num":1,"blog_detail":"哈哈哈哈哈哈哈","recommend":0,"id":79,"time":"21小时前","title":"测试一下","media_url":"20190123141708.mp3"},{"media_type":3,"createTime":"2019-01-23 13:46:14","num":1,"blog_detail":"您你","recommend":0,"id":78,"time":"21小时前","title":"哦哦","media_url":"20190123134615.mp4"},{"media_type":3,"createTime":"2019-01-23 13:26:41","num":1,"blog_detail":"啦啦","recommend":0,"id":77,"time":"21小时前","title":"你好","media_url":"20190123132641.mp4"},{"media_type":3,"createTime":"2019-01-23 11:50:34","num":1,"blog_detail":"寂寞吗","recommend":0,"id":76,"time":"23小时前","title":"哈哈","media_url":"20190123115035.mp4"},{"media_type":3,"createTime":"2019-01-23 11:48:37","num":1,"blog_detail":"哈哈","recommend":0,"id":75,"time":"23小时前","title":"哈","media_url":"20190123114054.mp4"},{"media_type":3,"createTime":"2019-01-23 11:43:42","num":1,"blog_detail":"哈all","recommend":0,"id":74,"time":"23小时前","title":"哈哈","media_url":"20190123114343.mp4"}],"navigatePages":8,"navigatepageNums":[1,2],"nextPage":2,"pageNum":1,"pageSize":15,"pages":2,"prePage":0,"size":15,"startRow":1,"total":23}}
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
         * pageInfo : {"endRow":15,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":2,"list":[{"blog_img":"20190124103846.png","media_type":1,"createTime":"2019-01-24 10:38:45","num":2,"blog_detail":"啊","recommend":0,"id":91,"time":"41分钟前","title":"测试2","media_url":"201901241038460.png,201901241038461.png"},{"blog_img":"20190124103659.png","media_type":2,"createTime":"2019-01-24 10:36:59","num":1,"blog_detail":"哈哈","recommend":0,"id":90,"time":"43分钟前","title":"薛阳","media_url":"20190124103659.mp4"},{"blog_img":"20190123163740.png","media_type":1,"createTime":"2019-01-23 16:37:40","num":2,"blog_detail":"哈哈哈","recommend":0,"id":86,"time":"18小时前","title":"哈哈哈","media_url":"201901231637400.png,201901231637401.png"},{"blog_img":"20190123160912.png","media_type":1,"createTime":"2019-01-23 16:09:11","num":7,"blog_detail":"测试","recommend":0,"id":85,"time":"19小时前","title":"哈哈","media_url":"201901231609122.png,201901231609120.png,201901231609121.png,201901231609123.png,201901231609125.png,201901231609126.png,201901231609127.png"},{"blog_img":"20190123160458.png","media_type":1,"createTime":"2019-01-23 16:04:58","num":8,"blog_detail":"酒窖","recommend":0,"id":84,"time":"19小时前","title":"测试一下","media_url":"null201901231604582.png,201901231604580.png,201901231604583.png,201901231604581.png,201901231604584.png,201901231604586.png,201901231604585.png,201901231604587.png"},{"blog_img":"20190123160055.png","media_type":1,"createTime":"2019-01-23 16:00:55","num":6,"blog_detail":"沆瀣一气","recommend":0,"id":83,"time":"19小时前","title":"茕茕孑立","media_url":"null201901231600554.png,201901231600550.png,201901231600551.png,201901231600552.png,201901231600553.png,201901231600557.png"},{"blog_img":"20190123155742.png","media_type":1,"createTime":"2019-01-23 15:57:42","num":1,"blog_detail":"哈哈","recommend":0,"id":82,"time":"19小时前","title":"旮旯","media_url":"201901231557433.png"},{"blog_img":"haha","media_type":1,"createTime":"2019-01-23 15:55:23","num":1,"blog_detail":"","recommend":0,"id":81,"time":"19小时前","title":"","media_url":"201901231555242.png"},{"media_type":3,"createTime":"2019-01-23 14:19:44","num":1,"blog_detail":"太急","recommend":0,"id":80,"time":"21小时前","title":"天津","media_url":"20190123141944.mp4"},{"media_type":3,"createTime":"2019-01-23 14:17:08","num":1,"blog_detail":"哈哈哈哈哈哈哈","recommend":0,"id":79,"time":"21小时前","title":"测试一下","media_url":"20190123141708.mp3"},{"media_type":3,"createTime":"2019-01-23 13:46:14","num":1,"blog_detail":"您你","recommend":0,"id":78,"time":"21小时前","title":"哦哦","media_url":"20190123134615.mp4"},{"media_type":3,"createTime":"2019-01-23 13:26:41","num":1,"blog_detail":"啦啦","recommend":0,"id":77,"time":"21小时前","title":"你好","media_url":"20190123132641.mp4"},{"media_type":3,"createTime":"2019-01-23 11:50:34","num":1,"blog_detail":"寂寞吗","recommend":0,"id":76,"time":"23小时前","title":"哈哈","media_url":"20190123115035.mp4"},{"media_type":3,"createTime":"2019-01-23 11:48:37","num":1,"blog_detail":"哈哈","recommend":0,"id":75,"time":"23小时前","title":"哈","media_url":"20190123114054.mp4"},{"media_type":3,"createTime":"2019-01-23 11:43:42","num":1,"blog_detail":"哈all","recommend":0,"id":74,"time":"23小时前","title":"哈哈","media_url":"20190123114343.mp4"}],"navigatePages":8,"navigatepageNums":[1,2],"nextPage":2,"pageNum":1,"pageSize":15,"pages":2,"prePage":0,"size":15,"startRow":1,"total":23}
         */

        private PageInfoBean pageInfo;

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public static class PageInfoBean {
            /**
             * endRow : 15
             * firstPage : 1
             * hasNextPage : true
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : false
             * lastPage : 2
             * list : [{"blog_img":"20190124103846.png","media_type":1,"createTime":"2019-01-24 10:38:45","num":2,"blog_detail":"啊","recommend":0,"id":91,"time":"41分钟前","title":"测试2","media_url":"201901241038460.png,201901241038461.png"},{"blog_img":"20190124103659.png","media_type":2,"createTime":"2019-01-24 10:36:59","num":1,"blog_detail":"哈哈","recommend":0,"id":90,"time":"43分钟前","title":"薛阳","media_url":"20190124103659.mp4"},{"blog_img":"20190123163740.png","media_type":1,"createTime":"2019-01-23 16:37:40","num":2,"blog_detail":"哈哈哈","recommend":0,"id":86,"time":"18小时前","title":"哈哈哈","media_url":"201901231637400.png,201901231637401.png"},{"blog_img":"20190123160912.png","media_type":1,"createTime":"2019-01-23 16:09:11","num":7,"blog_detail":"测试","recommend":0,"id":85,"time":"19小时前","title":"哈哈","media_url":"201901231609122.png,201901231609120.png,201901231609121.png,201901231609123.png,201901231609125.png,201901231609126.png,201901231609127.png"},{"blog_img":"20190123160458.png","media_type":1,"createTime":"2019-01-23 16:04:58","num":8,"blog_detail":"酒窖","recommend":0,"id":84,"time":"19小时前","title":"测试一下","media_url":"null201901231604582.png,201901231604580.png,201901231604583.png,201901231604581.png,201901231604584.png,201901231604586.png,201901231604585.png,201901231604587.png"},{"blog_img":"20190123160055.png","media_type":1,"createTime":"2019-01-23 16:00:55","num":6,"blog_detail":"沆瀣一气","recommend":0,"id":83,"time":"19小时前","title":"茕茕孑立","media_url":"null201901231600554.png,201901231600550.png,201901231600551.png,201901231600552.png,201901231600553.png,201901231600557.png"},{"blog_img":"20190123155742.png","media_type":1,"createTime":"2019-01-23 15:57:42","num":1,"blog_detail":"哈哈","recommend":0,"id":82,"time":"19小时前","title":"旮旯","media_url":"201901231557433.png"},{"blog_img":"haha","media_type":1,"createTime":"2019-01-23 15:55:23","num":1,"blog_detail":"","recommend":0,"id":81,"time":"19小时前","title":"","media_url":"201901231555242.png"},{"media_type":3,"createTime":"2019-01-23 14:19:44","num":1,"blog_detail":"太急","recommend":0,"id":80,"time":"21小时前","title":"天津","media_url":"20190123141944.mp4"},{"media_type":3,"createTime":"2019-01-23 14:17:08","num":1,"blog_detail":"哈哈哈哈哈哈哈","recommend":0,"id":79,"time":"21小时前","title":"测试一下","media_url":"20190123141708.mp3"},{"media_type":3,"createTime":"2019-01-23 13:46:14","num":1,"blog_detail":"您你","recommend":0,"id":78,"time":"21小时前","title":"哦哦","media_url":"20190123134615.mp4"},{"media_type":3,"createTime":"2019-01-23 13:26:41","num":1,"blog_detail":"啦啦","recommend":0,"id":77,"time":"21小时前","title":"你好","media_url":"20190123132641.mp4"},{"media_type":3,"createTime":"2019-01-23 11:50:34","num":1,"blog_detail":"寂寞吗","recommend":0,"id":76,"time":"23小时前","title":"哈哈","media_url":"20190123115035.mp4"},{"media_type":3,"createTime":"2019-01-23 11:48:37","num":1,"blog_detail":"哈哈","recommend":0,"id":75,"time":"23小时前","title":"哈","media_url":"20190123114054.mp4"},{"media_type":3,"createTime":"2019-01-23 11:43:42","num":1,"blog_detail":"哈all","recommend":0,"id":74,"time":"23小时前","title":"哈哈","media_url":"20190123114343.mp4"}]
             * navigatePages : 8
             * navigatepageNums : [1,2]
             * nextPage : 2
             * pageNum : 1
             * pageSize : 15
             * pages : 2
             * prePage : 0
             * size : 15
             * startRow : 1
             * total : 23
             */

            private int endRow;
            private int firstPage;
            private boolean hasNextPage;
            private boolean hasPreviousPage;
            private boolean isFirstPage;
            private boolean isLastPage;
            private int lastPage;
            private int navigatePages;
            private int nextPage;
            private int pageNum;
            private int pageSize;
            private int pages;
            private int prePage;
            private int size;
            private int startRow;
            private int total;
            private List<ListBean> list;
            private List<Integer> navigatepageNums;

            public int getEndRow() {
                return endRow;
            }

            public void setEndRow(int endRow) {
                this.endRow = endRow;
            }

            public int getFirstPage() {
                return firstPage;
            }

            public void setFirstPage(int firstPage) {
                this.firstPage = firstPage;
            }

            public boolean isHasNextPage() {
                return hasNextPage;
            }

            public void setHasNextPage(boolean hasNextPage) {
                this.hasNextPage = hasNextPage;
            }

            public boolean isHasPreviousPage() {
                return hasPreviousPage;
            }

            public void setHasPreviousPage(boolean hasPreviousPage) {
                this.hasPreviousPage = hasPreviousPage;
            }

            public boolean isIsFirstPage() {
                return isFirstPage;
            }

            public void setIsFirstPage(boolean isFirstPage) {
                this.isFirstPage = isFirstPage;
            }

            public boolean isIsLastPage() {
                return isLastPage;
            }

            public void setIsLastPage(boolean isLastPage) {
                this.isLastPage = isLastPage;
            }

            public int getLastPage() {
                return lastPage;
            }

            public void setLastPage(int lastPage) {
                this.lastPage = lastPage;
            }

            public int getNavigatePages() {
                return navigatePages;
            }

            public void setNavigatePages(int navigatePages) {
                this.navigatePages = navigatePages;
            }

            public int getNextPage() {
                return nextPage;
            }

            public void setNextPage(int nextPage) {
                this.nextPage = nextPage;
            }

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getPrePage() {
                return prePage;
            }

            public void setPrePage(int prePage) {
                this.prePage = prePage;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getStartRow() {
                return startRow;
            }

            public void setStartRow(int startRow) {
                this.startRow = startRow;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public List<Integer> getNavigatepageNums() {
                return navigatepageNums;
            }

            public void setNavigatepageNums(List<Integer> navigatepageNums) {
                this.navigatepageNums = navigatepageNums;
            }

            public static class ListBean {
                /**
                 * blog_img : 20190124103846.png
                 * media_type : 1
                 * createTime : 2019-01-24 10:38:45
                 * num : 2
                 * blog_detail : 啊
                 * recommend : 0
                 * id : 91
                 * time : 41分钟前
                 * title : 测试2
                 * media_url : 201901241038460.png,201901241038461.png
                 */

                private String blog_img;
                private int media_type;
                private String createTime;
                private int num;
                private String blog_detail;
                private int recommend;
                private int id;
                private String time;
                private String title;
                private String media_url;

                public String getBlog_img() {
                    return blog_img;
                }

                public void setBlog_img(String blog_img) {
                    this.blog_img = blog_img;
                }

                public int getMedia_type() {
                    return media_type;
                }

                public void setMedia_type(int media_type) {
                    this.media_type = media_type;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public String getBlog_detail() {
                    return blog_detail;
                }

                public void setBlog_detail(String blog_detail) {
                    this.blog_detail = blog_detail;
                }

                public int getRecommend() {
                    return recommend;
                }

                public void setRecommend(int recommend) {
                    this.recommend = recommend;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
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
            }
        }
    }
}
