package com.chuanqing.youngstar.mybean;

import java.util.List;

public class StarLeitaiBean {

    /**
     * data : {"pageInfo":{"endRow":3,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"currentTime":"报名截止时间:2020-01-01 00:00:00","activity_name":"活动1","start_time":"2019-01-01 00:00:00","num":0,"end_time":"2020-01-01 00:00:00","line_time":"2020-01-01 00:00:00","activity_code":"145495544327","list_img":"1546417764.jpg"},{"currentTime":"报名截止时间:2019-02-01 00:00:00","activity_name":"测试活动2","start_time":"2019-01-01 00:00:00","num":0,"end_time":"2019-02-01 00:00:00","line_time":"2019-02-01 00:00:00","activity_code":"312897437931","list_img":"1546417997.jpg"},{"currentTime":"报名截止时间:2019-03-01 00:00:00","activity_name":"活动3","start_time":"2019-01-02 00:00:00","num":0,"end_time":"2019-03-01 00:00:00","line_time":"2019-03-01 00:00:00","activity_code":"278970518419","list_img":"1546418065.jpg"}],"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":12,"pages":1,"prePage":0,"size":3,"startRow":1,"total":3}}
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
         * pageInfo : {"endRow":3,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"currentTime":"报名截止时间:2020-01-01 00:00:00","activity_name":"活动1","start_time":"2019-01-01 00:00:00","num":0,"end_time":"2020-01-01 00:00:00","line_time":"2020-01-01 00:00:00","activity_code":"145495544327","list_img":"1546417764.jpg"},{"currentTime":"报名截止时间:2019-02-01 00:00:00","activity_name":"测试活动2","start_time":"2019-01-01 00:00:00","num":0,"end_time":"2019-02-01 00:00:00","line_time":"2019-02-01 00:00:00","activity_code":"312897437931","list_img":"1546417997.jpg"},{"currentTime":"报名截止时间:2019-03-01 00:00:00","activity_name":"活动3","start_time":"2019-01-02 00:00:00","num":0,"end_time":"2019-03-01 00:00:00","line_time":"2019-03-01 00:00:00","activity_code":"278970518419","list_img":"1546418065.jpg"}],"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":12,"pages":1,"prePage":0,"size":3,"startRow":1,"total":3}
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
             * endRow : 3
             * firstPage : 1
             * hasNextPage : false
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : true
             * lastPage : 1
             * list : [{"currentTime":"报名截止时间:2020-01-01 00:00:00","activity_name":"活动1","start_time":"2019-01-01 00:00:00","num":0,"end_time":"2020-01-01 00:00:00","line_time":"2020-01-01 00:00:00","activity_code":"145495544327","list_img":"1546417764.jpg"},{"currentTime":"报名截止时间:2019-02-01 00:00:00","activity_name":"测试活动2","start_time":"2019-01-01 00:00:00","num":0,"end_time":"2019-02-01 00:00:00","line_time":"2019-02-01 00:00:00","activity_code":"312897437931","list_img":"1546417997.jpg"},{"currentTime":"报名截止时间:2019-03-01 00:00:00","activity_name":"活动3","start_time":"2019-01-02 00:00:00","num":0,"end_time":"2019-03-01 00:00:00","line_time":"2019-03-01 00:00:00","activity_code":"278970518419","list_img":"1546418065.jpg"}]
             * navigatePages : 8
             * navigatepageNums : [1]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 12
             * pages : 1
             * prePage : 0
             * size : 3
             * startRow : 1
             * total : 3
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
                 * currentTime : 报名截止时间:2020-01-01 00:00:00
                 * activity_name : 活动1
                 * start_time : 2019-01-01 00:00:00
                 * num : 0
                 * end_time : 2020-01-01 00:00:00
                 * line_time : 2020-01-01 00:00:00
                 * activity_code : 145495544327
                 * list_img : 1546417764.jpg
                 */

                private String currentTime;
                private String activity_name;
                private String start_time;
                private String num;
                private String end_time;
                private String line_time;
                private String activity_code;
                private String list_img;

                public String getCurrentTime() {
                    return currentTime;
                }

                public void setCurrentTime(String currentTime) {
                    this.currentTime = currentTime;
                }

                public String getActivity_name() {
                    return activity_name;
                }

                public void setActivity_name(String activity_name) {
                    this.activity_name = activity_name;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }

                public String getLine_time() {
                    return line_time;
                }

                public void setLine_time(String line_time) {
                    this.line_time = line_time;
                }

                public String getActivity_code() {
                    return activity_code;
                }

                public void setActivity_code(String activity_code) {
                    this.activity_code = activity_code;
                }

                public String getList_img() {
                    return list_img;
                }

                public void setList_img(String list_img) {
                    this.list_img = list_img;
                }
            }
        }
    }
}
