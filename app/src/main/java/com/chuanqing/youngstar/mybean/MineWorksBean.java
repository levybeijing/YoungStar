package com.chuanqing.youngstar.mybean;

import java.util.List;

public class MineWorksBean {

    /**
     * data : {"pageInfo":{"endRow":15,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":1,"list":[{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":2,"pageNum":1,"pageSize":15,"pages":54,"prePage":0,"size":15,"startRow":1,"total":807}}
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
         * pageInfo : {"endRow":15,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":1,"list":[{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":2,"pageNum":1,"pageSize":15,"pages":54,"prePage":0,"size":15,"startRow":1,"total":807}
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
             * list : [{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"},{"blog_img":"8572451327/20181212133026.jpg","media_type":2,"id":2,"media_url":"output-2018-12-21-13:34:43-567.mp4"}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 2
             * pageNum : 1
             * pageSize : 15
             * pages : 54
             * prePage : 0
             * size : 15
             * startRow : 1
             * total : 807
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
                 * blog_img : 8572451327/20181212133026.jpg
                 * media_type : 2
                 * id : 2
                 * media_url : output-2018-12-21-13:34:43-567.mp4
                 */

                private String blog_img;
                private int media_type;
                private int id;
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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
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
