package com.chuanqing.youngstar.mybean;

import java.util.List;

public class StarbangBean {

    /**
     * data : {"pageInfo":{"endRow":2,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"activity_name":"1111","start_time":"2018-12-03 00:00:00","student":[{"user_code":"5262634081","user_img":"qweqw"},{"user_code":"0723499476","user_img":"123"},null],"end_time":"2018-12-23 00:00:00","line_time":"2018-12-23 00:00:00","activity_code":"406193442487","list_img":"1545728523.jpg"},{"activity_name":"qqqq","start_time":"2018-12-11 00:00:00","student":[{"user_code":"5262634081","user_img":"qweqw"},{"user_code":"7976753974","user_img":"123"},{"user_code":"8219127408","user_img":"20181203135201.png"}],"end_time":"2018-12-24 00:00:00","line_time":"2018-12-24 00:00:00","activity_code":"885022867934","list_img":"1545730639.jpg"}],"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":12,"pages":1,"prePage":0,"size":2,"startRow":1,"total":2}}
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
         * pageInfo : {"endRow":2,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"activity_name":"1111","start_time":"2018-12-03 00:00:00","student":[{"user_code":"5262634081","user_img":"qweqw"},{"user_code":"0723499476","user_img":"123"},null],"end_time":"2018-12-23 00:00:00","line_time":"2018-12-23 00:00:00","activity_code":"406193442487","list_img":"1545728523.jpg"},{"activity_name":"qqqq","start_time":"2018-12-11 00:00:00","student":[{"user_code":"5262634081","user_img":"qweqw"},{"user_code":"7976753974","user_img":"123"},{"user_code":"8219127408","user_img":"20181203135201.png"}],"end_time":"2018-12-24 00:00:00","line_time":"2018-12-24 00:00:00","activity_code":"885022867934","list_img":"1545730639.jpg"}],"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":12,"pages":1,"prePage":0,"size":2,"startRow":1,"total":2}
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
             * endRow : 2
             * firstPage : 1
             * hasNextPage : false
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : true
             * lastPage : 1
             * list : [{"activity_name":"1111","start_time":"2018-12-03 00:00:00","student":[{"user_code":"5262634081","user_img":"qweqw"},{"user_code":"0723499476","user_img":"123"},null],"end_time":"2018-12-23 00:00:00","line_time":"2018-12-23 00:00:00","activity_code":"406193442487","list_img":"1545728523.jpg"},{"activity_name":"qqqq","start_time":"2018-12-11 00:00:00","student":[{"user_code":"5262634081","user_img":"qweqw"},{"user_code":"7976753974","user_img":"123"},{"user_code":"8219127408","user_img":"20181203135201.png"}],"end_time":"2018-12-24 00:00:00","line_time":"2018-12-24 00:00:00","activity_code":"885022867934","list_img":"1545730639.jpg"}]
             * navigatePages : 8
             * navigatepageNums : [1]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 12
             * pages : 1
             * prePage : 0
             * size : 2
             * startRow : 1
             * total : 2
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
                 * activity_name : 1111
                 * start_time : 2018-12-03 00:00:00
                 * student : [{"user_code":"5262634081","user_img":"qweqw"},{"user_code":"0723499476","user_img":"123"},null]
                 * end_time : 2018-12-23 00:00:00
                 * line_time : 2018-12-23 00:00:00
                 * activity_code : 406193442487
                 * list_img : 1545728523.jpg
                 */

                private String activity_name;
                private String start_time;
                private String end_time;
                private String line_time;
                private String activity_code;
                private String list_img;
                private List<StudentBean> student;

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

                public List<StudentBean> getStudent() {
                    return student;
                }

                public void setStudent(List<StudentBean> student) {
                    this.student = student;
                }

                public static class StudentBean {
                    /**
                     * user_code : 5262634081
                     * user_img : qweqw
                     */

                    private String user_code;
                    private String user_img;

                    public String getUser_code() {
                        return user_code;
                    }

                    public void setUser_code(String user_code) {
                        this.user_code = user_code;
                    }

                    public String getUser_img() {
                        return user_img;
                    }

                    public void setUser_img(String user_img) {
                        this.user_img = user_img;
                    }
                }
            }
        }
    }
}
