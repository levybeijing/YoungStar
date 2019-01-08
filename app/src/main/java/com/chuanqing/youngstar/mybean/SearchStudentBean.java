package com.chuanqing.youngstar.mybean;

import java.util.List;

public class SearchStudentBean {

    /**
     * data : {"pageInfo":{"endRow":10,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":2,"list":[{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":781},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":779},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":777},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":767},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":765},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":763},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":767},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":765},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":763},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":767}],"navigatePages":8,"navigatepageNums":[1,2],"nextPage":2,"pageNum":1,"pageSize":10,"pages":2,"prePage":0,"size":10,"startRow":1,"total":13}}
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
         * pageInfo : {"endRow":10,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":2,"list":[{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":781},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":779},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":777},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":767},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":765},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":763},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":767},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":765},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":763},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":767}],"navigatePages":8,"navigatepageNums":[1,2],"nextPage":2,"pageNum":1,"pageSize":10,"pages":2,"prePage":0,"size":10,"startRow":1,"total":13}
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
             * endRow : 10
             * firstPage : 1
             * hasNextPage : true
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : false
             * lastPage : 2
             * list : [{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":781},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":779},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":777},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":767},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":765},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":763},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":767},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":765},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":763},{"code":"8572451327","flag":0,"user_code":"5262634081","sex":"男","label":"模特 | 演员","user_img":"qweqw","recommendTotal":767}]
             * navigatePages : 8
             * navigatepageNums : [1,2]
             * nextPage : 2
             * pageNum : 1
             * pageSize : 10
             * pages : 2
             * prePage : 0
             * size : 10
             * startRow : 1
             * total : 13
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
                 * code : 8572451327
                 * flag : 0
                 * user_code : 5262634081
                 * sex : 男
                 * label : 模特 | 演员
                 * user_img : qweqw
                 * recommendTotal : 781
                 */

                private String code;
                private int flag;
                private String user_code;
                private String sex;
                private String label;
                private String user_img;
                private int recommendTotal;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public int getFlag() {
                    return flag;
                }

                public void setFlag(int flag) {
                    this.flag = flag;
                }

                public String getUser_code() {
                    return user_code;
                }

                public void setUser_code(String user_code) {
                    this.user_code = user_code;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getUser_img() {
                    return user_img;
                }

                public void setUser_img(String user_img) {
                    this.user_img = user_img;
                }

                public int getRecommendTotal() {
                    return recommendTotal;
                }

                public void setRecommendTotal(int recommendTotal) {
                    this.recommendTotal = recommendTotal;
                }
            }
        }
    }
}
