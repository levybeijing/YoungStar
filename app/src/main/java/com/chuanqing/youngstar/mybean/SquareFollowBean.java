package com.chuanqing.youngstar.mybean;

import java.util.List;

public class SquareFollowBean {
    /**
     * data : {"pageInfo":{"endRow":4,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"flag":0,"create_time":"2018-12-24 11:50:25","blog_detail":"T","recommend":0,"label":"歌手 | 配音","title":"u","type":1,"media_url":"8572451327/201812241150241018.png","user_img":"20181203135201.png","userCode":"8572451327","blog_img":"8572451327/201812241150159357.png","user_code":"8572451327","timeSpan":"2018-12-24","id":21},{"flag":0,"create_time":"2018-12-24 11:47:16","blog_detail":"E","recommend":0,"label":"歌手 | 配音","title":"e","type":1,"media_url":"8572451327/201812241147158218.png,8572451327/201812241147165417.png","user_img":"20181203135201.png","userCode":"8572451327","blog_img":"8572451327/201812241146413840.png","user_code":"8572451327","timeSpan":"2018-12-24","id":20},{"flag":0,"create_time":"2018-12-24 11:46:50","blog_detail":"E","recommend":0,"label":"歌手 | 配音","title":"e","type":1,"media_url":"8572451327/201812241146499547.png","user_img":"20181203135201.png","userCode":"8572451327","blog_img":"8572451327/201812241146413840.png","user_code":"8572451327","timeSpan":"2018-12-24","id":19},{"flag":0,"create_time":"2018-12-16 13:33:40","blog_detail":"afaxcasad","recommend":0,"label":"歌手 | 配音","title":"ceshi","type":2,"media_url":"8572451327/20181212133026.jpg","user_img":"20181203135201.png","userCode":"8572451327","blog_img":"8572451327/20181212133026.jpg","user_code":"8572451327","timeSpan":"2018-12-16","id":5}],"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":4,"startRow":1,"total":4}}
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
         * pageInfo : {"endRow":4,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"flag":0,"create_time":"2018-12-24 11:50:25","blog_detail":"T","recommend":0,"label":"歌手 | 配音","title":"u","type":1,"media_url":"8572451327/201812241150241018.png","user_img":"20181203135201.png","userCode":"8572451327","blog_img":"8572451327/201812241150159357.png","user_code":"8572451327","timeSpan":"2018-12-24","id":21},{"flag":0,"create_time":"2018-12-24 11:47:16","blog_detail":"E","recommend":0,"label":"歌手 | 配音","title":"e","type":1,"media_url":"8572451327/201812241147158218.png,8572451327/201812241147165417.png","user_img":"20181203135201.png","userCode":"8572451327","blog_img":"8572451327/201812241146413840.png","user_code":"8572451327","timeSpan":"2018-12-24","id":20},{"flag":0,"create_time":"2018-12-24 11:46:50","blog_detail":"E","recommend":0,"label":"歌手 | 配音","title":"e","type":1,"media_url":"8572451327/201812241146499547.png","user_img":"20181203135201.png","userCode":"8572451327","blog_img":"8572451327/201812241146413840.png","user_code":"8572451327","timeSpan":"2018-12-24","id":19},{"flag":0,"create_time":"2018-12-16 13:33:40","blog_detail":"afaxcasad","recommend":0,"label":"歌手 | 配音","title":"ceshi","type":2,"media_url":"8572451327/20181212133026.jpg","user_img":"20181203135201.png","userCode":"8572451327","blog_img":"8572451327/20181212133026.jpg","user_code":"8572451327","timeSpan":"2018-12-16","id":5}],"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":4,"startRow":1,"total":4}
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
             * endRow : 4
             * firstPage : 1
             * hasNextPage : false
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : true
             * lastPage : 1
             * list : [{"flag":0,"create_time":"2018-12-24 11:50:25","blog_detail":"T","recommend":0,"label":"歌手 | 配音","title":"u","type":1,"media_url":"8572451327/201812241150241018.png","user_img":"20181203135201.png","userCode":"8572451327","blog_img":"8572451327/201812241150159357.png","user_code":"8572451327","timeSpan":"2018-12-24","id":21},{"flag":0,"create_time":"2018-12-24 11:47:16","blog_detail":"E","recommend":0,"label":"歌手 | 配音","title":"e","type":1,"media_url":"8572451327/201812241147158218.png,8572451327/201812241147165417.png","user_img":"20181203135201.png","userCode":"8572451327","blog_img":"8572451327/201812241146413840.png","user_code":"8572451327","timeSpan":"2018-12-24","id":20},{"flag":0,"create_time":"2018-12-24 11:46:50","blog_detail":"E","recommend":0,"label":"歌手 | 配音","title":"e","type":1,"media_url":"8572451327/201812241146499547.png","user_img":"20181203135201.png","userCode":"8572451327","blog_img":"8572451327/201812241146413840.png","user_code":"8572451327","timeSpan":"2018-12-24","id":19},{"flag":0,"create_time":"2018-12-16 13:33:40","blog_detail":"afaxcasad","recommend":0,"label":"歌手 | 配音","title":"ceshi","type":2,"media_url":"8572451327/20181212133026.jpg","user_img":"20181203135201.png","userCode":"8572451327","blog_img":"8572451327/20181212133026.jpg","user_code":"8572451327","timeSpan":"2018-12-16","id":5}]
             * navigatePages : 8
             * navigatepageNums : [1]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 10
             * pages : 1
             * prePage : 0
             * size : 4
             * startRow : 1
             * total : 4
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
                 * flag : 0
                 * create_time : 2018-12-24 11:50:25
                 * blog_detail : T
                 * recommend : 0
                 * label : 歌手 | 配音
                 * title : u
                 * type : 1
                 * media_url : 8572451327/201812241150241018.png
                 * user_img : 20181203135201.png
                 * userCode : 8572451327
                 * blog_img : 8572451327/201812241150159357.png
                 * user_code : 8572451327
                 * timeSpan : 2018-12-24
                 * id : 21
                 */

                private int flag;
                private String create_time;
                private String blog_detail;
                private int recommend;
                private String label;
                private String title;
                private int type;
                private String media_url;
                private String user_img;
                private String userCode;
                private String blog_img;
                private String user_code;
                private String timeSpan;
                private int id;
                private String media_type;
                private String num;

                public String getMedia_type() {
                    return media_type;
                }

                public void setMedia_type(String media_type) {
                    this.media_type = media_type;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }

                public int getFlag() {
                    return flag;
                }

                public void setFlag(int flag) {
                    this.flag = flag;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
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

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
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

                public String getUser_img() {
                    return user_img;
                }

                public void setUser_img(String user_img) {
                    this.user_img = user_img;
                }

                public String getUserCode() {
                    return userCode;
                }

                public void setUserCode(String userCode) {
                    this.userCode = userCode;
                }

                public String getBlog_img() {
                    return blog_img;
                }

                public void setBlog_img(String blog_img) {
                    this.blog_img = blog_img;
                }

                public String getUser_code() {
                    return user_code;
                }

                public void setUser_code(String user_code) {
                    this.user_code = user_code;
                }

                public String getTimeSpan() {
                    return timeSpan;
                }

                public void setTimeSpan(String timeSpan) {
                    this.timeSpan = timeSpan;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }
    }
}
