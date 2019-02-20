package com.chuanqing.youngstar.mybean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FragStarActSBean {

    /**
     * data : {"pageInfo":{"endRow":1,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"activity_name":"123","1":2,"start_time":"2018-12-24 00:00:00","user_code":"5262634081","num":0,"end_time":"2018-12-31","line_time":"2018-12-31 00:00:00","ranking":2,"activity_code":"764235447923","list_img":"1545727261.jpg","time":"12.24-12.31","userCode":"5262634081"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":1,"startRow":1,"total":1}}
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
         * pageInfo : {"endRow":1,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"activity_name":"123","1":2,"start_time":"2018-12-24 00:00:00","user_code":"5262634081","num":0,"end_time":"2018-12-31","line_time":"2018-12-31 00:00:00","ranking":2,"activity_code":"764235447923","list_img":"1545727261.jpg","time":"12.24-12.31","userCode":"5262634081"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":1,"startRow":1,"total":1}
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
             * endRow : 1
             * firstPage : 1
             * hasNextPage : false
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : true
             * lastPage : 1
             * list : [{"activity_name":"123","1":2,"start_time":"2018-12-24 00:00:00","user_code":"5262634081","num":0,"end_time":"2018-12-31","line_time":"2018-12-31 00:00:00","ranking":2,"activity_code":"764235447923","list_img":"1545727261.jpg","time":"12.24-12.31","userCode":"5262634081"}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 10
             * pages : 1
             * prePage : 0
             * size : 1
             * startRow : 1
             * total : 1
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
                 * activity_name : 123
                 * 1 : 2
                 * start_time : 2018-12-24 00:00:00
                 * user_code : 5262634081
                 * num : 0
                 * end_time : 2018-12-31
                 * line_time : 2018-12-31 00:00:00
                 * ranking : 2
                 * activity_code : 764235447923
                 * list_img : 1545727261.jpg
                 * time : 12.24-12.31
                 * userCode : 5262634081
                 */

                private String activity_name;
                @SerializedName("1")
                private int _$1;
                private String start_time;
                private String user_code;
                private int num;
                private String end_time;
                private String line_time;
                private int ranking;
                private String activity_code;
                private String list_img;
                private String time;
                private String userCode;

                public String getActivity_name() {
                    return activity_name;
                }

                public void setActivity_name(String activity_name) {
                    this.activity_name = activity_name;
                }

                public int get_$1() {
                    return _$1;
                }

                public void set_$1(int _$1) {
                    this._$1 = _$1;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getUser_code() {
                    return user_code;
                }

                public void setUser_code(String user_code) {
                    this.user_code = user_code;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
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

                public int getRanking() {
                    return ranking;
                }

                public void setRanking(int ranking) {
                    this.ranking = ranking;
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

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getUserCode() {
                    return userCode;
                }

                public void setUserCode(String userCode) {
                    this.userCode = userCode;
                }
            }
        }
    }
}
