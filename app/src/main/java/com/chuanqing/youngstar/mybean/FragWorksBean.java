package com.chuanqing.youngstar.mybean;

import java.util.List;

public class FragWorksBean {

    /**
     * data : {"pageInfo":{"endRow":15,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":1,"list":[{"collection_code":"3859694650","user_code":"8572451327","createTime":"2019-01-10 16:31:00","first_img":"8572451327/201901101630513452.png","recommend":0,"time":"2019-01-10","title":"融汇大厦附近"},{"collection_code":"68232281173","user_code":"8572451327","createTime":"2019-01-10 16:29:01","first_img":"8572451327/201901101628534710.png","recommend":0,"time":"2019-01-10","title":"融汇"},{"collection_code":"773146645218","user_code":"8572451327","createTime":"2019-01-10 15:59:22","first_img":"8572451327/201901101559127427.png","recommend":0,"time":"2019-01-10","title":"融汇"},{"collection_code":"377379725340","user_code":"8572451327","createTime":"2019-01-10 15:00:46","first_img":"8572451327/201901101500361189.png","recommend":0,"time":"2019-01-10","title":"融汇"},{"collection_code":"101105889300","user_code":"8572451327","createTime":"2019-01-10 14:55:44","first_img":"8572451327/201901101455396495.png","recommend":0,"time":"2019-01-10","title":"融汇大厦"},{"collection_code":"960097946012","user_code":"8572451327","createTime":"2019-01-10 14:52:49","first_img":"8572451327/201901101452158789.png","recommend":0,"time":"2019-01-10","title":"天津市政府"},{"collection_code":"607994071379","user_code":"8572451327","createTime":"2019-01-10 14:34:24","first_img":"8572451327/201901101434565043.png","recommend":0,"time":"2019-01-10","title":"天津市政府"},{"collection_code":"822292451630","user_code":"8572451327","createTime":"2019-01-04 13:57:44","first_img":"8572451327/201901041357378935.png","recommend":0,"time":"2019-01-04","title":"你的时候"},{"collection_code":"37296638738","user_code":"8572451327","createTime":"2019-01-04 11:25:26","recommend":0,"time":"2019-01-04"},{"collection_code":"327658905197","user_code":"8572451327","createTime":"2019-01-03 17:40:47","recommend":0,"time":"2019-01-03"},{"collection_code":"18498849125","user_code":"8572451327","createTime":"2019-01-03 17:40:45","recommend":0,"time":"2019-01-03"},{"collection_code":"429007073742","user_code":"8572451327","createTime":"2019-01-03 17:37:10","recommend":0,"time":"2019-01-03"},{"collection_code":"104918236123","user_code":"8572451327","createTime":"2019-01-03 17:30:54","recommend":0,"time":"2019-01-03"},{"collection_code":"446188401518","user_code":"8572451327","createTime":"2019-01-03 16:36:01","recommend":0,"time":"2019-01-03"},{"collection_code":"647179140504","user_code":"8572451327","createTime":"2019-01-03 16:34:53","recommend":0,"time":"2019-01-03"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":2,"pageNum":1,"pageSize":15,"pages":5,"prePage":0,"size":15,"startRow":1,"total":67}}
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
         * pageInfo : {"endRow":15,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":1,"list":[{"collection_code":"3859694650","user_code":"8572451327","createTime":"2019-01-10 16:31:00","first_img":"8572451327/201901101630513452.png","recommend":0,"time":"2019-01-10","title":"融汇大厦附近"},{"collection_code":"68232281173","user_code":"8572451327","createTime":"2019-01-10 16:29:01","first_img":"8572451327/201901101628534710.png","recommend":0,"time":"2019-01-10","title":"融汇"},{"collection_code":"773146645218","user_code":"8572451327","createTime":"2019-01-10 15:59:22","first_img":"8572451327/201901101559127427.png","recommend":0,"time":"2019-01-10","title":"融汇"},{"collection_code":"377379725340","user_code":"8572451327","createTime":"2019-01-10 15:00:46","first_img":"8572451327/201901101500361189.png","recommend":0,"time":"2019-01-10","title":"融汇"},{"collection_code":"101105889300","user_code":"8572451327","createTime":"2019-01-10 14:55:44","first_img":"8572451327/201901101455396495.png","recommend":0,"time":"2019-01-10","title":"融汇大厦"},{"collection_code":"960097946012","user_code":"8572451327","createTime":"2019-01-10 14:52:49","first_img":"8572451327/201901101452158789.png","recommend":0,"time":"2019-01-10","title":"天津市政府"},{"collection_code":"607994071379","user_code":"8572451327","createTime":"2019-01-10 14:34:24","first_img":"8572451327/201901101434565043.png","recommend":0,"time":"2019-01-10","title":"天津市政府"},{"collection_code":"822292451630","user_code":"8572451327","createTime":"2019-01-04 13:57:44","first_img":"8572451327/201901041357378935.png","recommend":0,"time":"2019-01-04","title":"你的时候"},{"collection_code":"37296638738","user_code":"8572451327","createTime":"2019-01-04 11:25:26","recommend":0,"time":"2019-01-04"},{"collection_code":"327658905197","user_code":"8572451327","createTime":"2019-01-03 17:40:47","recommend":0,"time":"2019-01-03"},{"collection_code":"18498849125","user_code":"8572451327","createTime":"2019-01-03 17:40:45","recommend":0,"time":"2019-01-03"},{"collection_code":"429007073742","user_code":"8572451327","createTime":"2019-01-03 17:37:10","recommend":0,"time":"2019-01-03"},{"collection_code":"104918236123","user_code":"8572451327","createTime":"2019-01-03 17:30:54","recommend":0,"time":"2019-01-03"},{"collection_code":"446188401518","user_code":"8572451327","createTime":"2019-01-03 16:36:01","recommend":0,"time":"2019-01-03"},{"collection_code":"647179140504","user_code":"8572451327","createTime":"2019-01-03 16:34:53","recommend":0,"time":"2019-01-03"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":2,"pageNum":1,"pageSize":15,"pages":5,"prePage":0,"size":15,"startRow":1,"total":67}
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
             * lastPage : 1
             * list : [{"collection_code":"3859694650","user_code":"8572451327","createTime":"2019-01-10 16:31:00","first_img":"8572451327/201901101630513452.png","recommend":0,"time":"2019-01-10","title":"融汇大厦附近"},{"collection_code":"68232281173","user_code":"8572451327","createTime":"2019-01-10 16:29:01","first_img":"8572451327/201901101628534710.png","recommend":0,"time":"2019-01-10","title":"融汇"},{"collection_code":"773146645218","user_code":"8572451327","createTime":"2019-01-10 15:59:22","first_img":"8572451327/201901101559127427.png","recommend":0,"time":"2019-01-10","title":"融汇"},{"collection_code":"377379725340","user_code":"8572451327","createTime":"2019-01-10 15:00:46","first_img":"8572451327/201901101500361189.png","recommend":0,"time":"2019-01-10","title":"融汇"},{"collection_code":"101105889300","user_code":"8572451327","createTime":"2019-01-10 14:55:44","first_img":"8572451327/201901101455396495.png","recommend":0,"time":"2019-01-10","title":"融汇大厦"},{"collection_code":"960097946012","user_code":"8572451327","createTime":"2019-01-10 14:52:49","first_img":"8572451327/201901101452158789.png","recommend":0,"time":"2019-01-10","title":"天津市政府"},{"collection_code":"607994071379","user_code":"8572451327","createTime":"2019-01-10 14:34:24","first_img":"8572451327/201901101434565043.png","recommend":0,"time":"2019-01-10","title":"天津市政府"},{"collection_code":"822292451630","user_code":"8572451327","createTime":"2019-01-04 13:57:44","first_img":"8572451327/201901041357378935.png","recommend":0,"time":"2019-01-04","title":"你的时候"},{"collection_code":"37296638738","user_code":"8572451327","createTime":"2019-01-04 11:25:26","recommend":0,"time":"2019-01-04"},{"collection_code":"327658905197","user_code":"8572451327","createTime":"2019-01-03 17:40:47","recommend":0,"time":"2019-01-03"},{"collection_code":"18498849125","user_code":"8572451327","createTime":"2019-01-03 17:40:45","recommend":0,"time":"2019-01-03"},{"collection_code":"429007073742","user_code":"8572451327","createTime":"2019-01-03 17:37:10","recommend":0,"time":"2019-01-03"},{"collection_code":"104918236123","user_code":"8572451327","createTime":"2019-01-03 17:30:54","recommend":0,"time":"2019-01-03"},{"collection_code":"446188401518","user_code":"8572451327","createTime":"2019-01-03 16:36:01","recommend":0,"time":"2019-01-03"},{"collection_code":"647179140504","user_code":"8572451327","createTime":"2019-01-03 16:34:53","recommend":0,"time":"2019-01-03"}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 2
             * pageNum : 1
             * pageSize : 15
             * pages : 5
             * prePage : 0
             * size : 15
             * startRow : 1
             * total : 67
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
                 * collection_code : 3859694650
                 * user_code : 8572451327
                 * createTime : 2019-01-10 16:31:00
                 * first_img : 8572451327/201901101630513452.png
                 * recommend : 0
                 * time : 2019-01-10
                 * title : 融汇大厦附近
                 */

                private String collection_code;
                private String user_code;
                private String createTime;
                private String first_img;
                private int recommend;
                private String time;
                private String title;

                public String getCollection_code() {
                    return collection_code;
                }

                public void setCollection_code(String collection_code) {
                    this.collection_code = collection_code;
                }

                public String getUser_code() {
                    return user_code;
                }

                public void setUser_code(String user_code) {
                    this.user_code = user_code;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public String getFirst_img() {
                    return first_img;
                }

                public void setFirst_img(String first_img) {
                    this.first_img = first_img;
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

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }
    }
}
