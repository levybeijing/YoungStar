package com.chuanqing.youngstar.mybean;

import java.util.List;

public class OfficeMsgBean {

    /**
     * data : {"pageInfo":{"endRow":12,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"createTime":"2018-12-17 16:23:35","genre":1,"id":13,"time":"2018-12-17","title":"站内消息标题1","url":"www.baidu.com","content":"站内消息msg1","status":2},{"createTime":"2018-12-25 11:06:26","genre":1,"id":25,"time":"2018-12-25","title":"测试111","url":"111","content":"测试11111111","status":2},{"createTime":"2018-12-25 11:08:45","genre":1,"id":26,"time":"2018-12-25","title":"1111111","url":"1111111","content":"1111111","status":2},{"createTime":"2018-12-26 10:47:59","genre":1,"id":27,"time":"2018-12-26","title":"123","url":"http://www.com","content":"123","status":1},{"createTime":"2018-12-28 15:32:19","genre":1,"id":37,"time":"2018-12-28","title":"12313","url":"2131","content":"31313","status":2},{"createTime":"2018-12-28 15:33:21","genre":1,"id":39,"time":"2018-12-28","title":"1231","url":"3131","content":"2313","status":1},{"createTime":"2018-12-28 15:36:01","genre":1,"id":40,"time":"2018-12-28","title":"23","url":"234","content":"24234","status":1},{"createTime":"2018-12-28 15:37:13","genre":1,"id":41,"time":"2018-12-28","title":"211212","url":"233232","content":"23424","status":1},{"createTime":"2018-12-28 15:37:18","genre":1,"id":42,"time":"2018-12-28","title":"234","url":"24","content":"234","status":1},{"createTime":"2018-12-28 15:37:29","genre":1,"id":44,"time":"2018-12-28","title":"234","url":"234","content":"23424","status":1},{"createTime":"2018-12-29 14:43:17","genre":1,"id":52,"time":"2018-12-29","title":"的萨格","url":"www.dsas.com","content":"sdgas","status":2},{"createTime":"2019-01-02 10:03:42","genre":1,"id":53,"time":"2019-01-02","title":"问问","url":"www.baidu.com","content":"问问","status":1}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":15,"pages":1,"prePage":0,"size":12,"startRow":1,"total":12}}
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
         * pageInfo : {"endRow":12,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"createTime":"2018-12-17 16:23:35","genre":1,"id":13,"time":"2018-12-17","title":"站内消息标题1","url":"www.baidu.com","content":"站内消息msg1","status":2},{"createTime":"2018-12-25 11:06:26","genre":1,"id":25,"time":"2018-12-25","title":"测试111","url":"111","content":"测试11111111","status":2},{"createTime":"2018-12-25 11:08:45","genre":1,"id":26,"time":"2018-12-25","title":"1111111","url":"1111111","content":"1111111","status":2},{"createTime":"2018-12-26 10:47:59","genre":1,"id":27,"time":"2018-12-26","title":"123","url":"http://www.com","content":"123","status":1},{"createTime":"2018-12-28 15:32:19","genre":1,"id":37,"time":"2018-12-28","title":"12313","url":"2131","content":"31313","status":2},{"createTime":"2018-12-28 15:33:21","genre":1,"id":39,"time":"2018-12-28","title":"1231","url":"3131","content":"2313","status":1},{"createTime":"2018-12-28 15:36:01","genre":1,"id":40,"time":"2018-12-28","title":"23","url":"234","content":"24234","status":1},{"createTime":"2018-12-28 15:37:13","genre":1,"id":41,"time":"2018-12-28","title":"211212","url":"233232","content":"23424","status":1},{"createTime":"2018-12-28 15:37:18","genre":1,"id":42,"time":"2018-12-28","title":"234","url":"24","content":"234","status":1},{"createTime":"2018-12-28 15:37:29","genre":1,"id":44,"time":"2018-12-28","title":"234","url":"234","content":"23424","status":1},{"createTime":"2018-12-29 14:43:17","genre":1,"id":52,"time":"2018-12-29","title":"的萨格","url":"www.dsas.com","content":"sdgas","status":2},{"createTime":"2019-01-02 10:03:42","genre":1,"id":53,"time":"2019-01-02","title":"问问","url":"www.baidu.com","content":"问问","status":1}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":15,"pages":1,"prePage":0,"size":12,"startRow":1,"total":12}
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
             * endRow : 12
             * firstPage : 1
             * hasNextPage : false
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : true
             * lastPage : 1
             * list : [{"createTime":"2018-12-17 16:23:35","genre":1,"id":13,"time":"2018-12-17","title":"站内消息标题1","url":"www.baidu.com","content":"站内消息msg1","status":2},{"createTime":"2018-12-25 11:06:26","genre":1,"id":25,"time":"2018-12-25","title":"测试111","url":"111","content":"测试11111111","status":2},{"createTime":"2018-12-25 11:08:45","genre":1,"id":26,"time":"2018-12-25","title":"1111111","url":"1111111","content":"1111111","status":2},{"createTime":"2018-12-26 10:47:59","genre":1,"id":27,"time":"2018-12-26","title":"123","url":"http://www.com","content":"123","status":1},{"createTime":"2018-12-28 15:32:19","genre":1,"id":37,"time":"2018-12-28","title":"12313","url":"2131","content":"31313","status":2},{"createTime":"2018-12-28 15:33:21","genre":1,"id":39,"time":"2018-12-28","title":"1231","url":"3131","content":"2313","status":1},{"createTime":"2018-12-28 15:36:01","genre":1,"id":40,"time":"2018-12-28","title":"23","url":"234","content":"24234","status":1},{"createTime":"2018-12-28 15:37:13","genre":1,"id":41,"time":"2018-12-28","title":"211212","url":"233232","content":"23424","status":1},{"createTime":"2018-12-28 15:37:18","genre":1,"id":42,"time":"2018-12-28","title":"234","url":"24","content":"234","status":1},{"createTime":"2018-12-28 15:37:29","genre":1,"id":44,"time":"2018-12-28","title":"234","url":"234","content":"23424","status":1},{"createTime":"2018-12-29 14:43:17","genre":1,"id":52,"time":"2018-12-29","title":"的萨格","url":"www.dsas.com","content":"sdgas","status":2},{"createTime":"2019-01-02 10:03:42","genre":1,"id":53,"time":"2019-01-02","title":"问问","url":"www.baidu.com","content":"问问","status":1}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 15
             * pages : 1
             * prePage : 0
             * size : 12
             * startRow : 1
             * total : 12
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
                 * createTime : 2018-12-17 16:23:35
                 * genre : 1
                 * id : 13
                 * time : 2018-12-17
                 * title : 站内消息标题1
                 * url : www.baidu.com
                 * content : 站内消息msg1
                 * status : 2
                 */

                private String createTime;
                private int genre;
                private int id;
                private String time;
                private String title;
                private String url;
                private String content;
                private int status;

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public int getGenre() {
                    return genre;
                }

                public void setGenre(int genre) {
                    this.genre = genre;
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

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
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
