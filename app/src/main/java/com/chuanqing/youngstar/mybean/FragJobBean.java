package com.chuanqing.youngstar.mybean;

import java.util.List;

public class FragJobBean {

    /**
     * data : {"pageInfo":{"endRow":2,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"img":"20181203135201.png,","createTime":"2018-12-06 11:16:26","attend_num":3,"employ_code":"885098365123","time":"未通过","title":"招聘","status":3},{"img":"20181203135201.png,","createTime":"2018-12-06 11:16:26","attend_num":4,"employ_code":"123123","time":"2018-12-06","title":"招聘","status":2}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":15,"pages":1,"prePage":0,"size":2,"startRow":1,"total":2}}
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
         * pageInfo : {"endRow":2,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"img":"20181203135201.png,","createTime":"2018-12-06 11:16:26","attend_num":3,"employ_code":"885098365123","time":"未通过","title":"招聘","status":3},{"img":"20181203135201.png,","createTime":"2018-12-06 11:16:26","attend_num":4,"employ_code":"123123","time":"2018-12-06","title":"招聘","status":2}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":15,"pages":1,"prePage":0,"size":2,"startRow":1,"total":2}
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
             * list : [{"img":"20181203135201.png,","createTime":"2018-12-06 11:16:26","attend_num":3,"employ_code":"885098365123","time":"未通过","title":"招聘","status":3},{"img":"20181203135201.png,","createTime":"2018-12-06 11:16:26","attend_num":4,"employ_code":"123123","time":"2018-12-06","title":"招聘","status":2}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 15
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
                 * img : 20181203135201.png,
                 * createTime : 2018-12-06 11:16:26
                 * attend_num : 3
                 * employ_code : 885098365123
                 * time : 未通过
                 * title : 招聘
                 * status : 3
                 */

                private String img;
                private String createTime;
                private int attend_num;
                private String employ_code;
                private String time;
                private String title;
                private int status;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public int getAttend_num() {
                    return attend_num;
                }

                public void setAttend_num(int attend_num) {
                    this.attend_num = attend_num;
                }

                public String getEmploy_code() {
                    return employ_code;
                }

                public void setEmploy_code(String employ_code) {
                    this.employ_code = employ_code;
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
