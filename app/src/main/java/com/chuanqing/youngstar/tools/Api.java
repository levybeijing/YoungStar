package com.chuanqing.youngstar.tools;

public class Api {
    public static String apiurl = "http://39.107.70.80:8080";
    public static String ossurl = "https://star-1.oss-cn-beijing.aliyuncs.com/";
    /**
     * 首页
     */
    //首页轮播
    public static String home_lunbo = apiurl + "/Star/firstPage/getAD";
    //首页活动
    public static String home_activity = apiurl + "/Star/firstPage/getFirstPageActivity";
    //首页招聘（演绎专区）
    public static String home_zhaopin = apiurl+"/Star/firstPage/getFirstPageEmploy";
    //搜索学生
    public static String home_search_student = apiurl+"/Star/firstPage/getFirstPageSelectStudent";
    //搜索活动
    public static String home_search_activity = apiurl+"/Star/firstPage/getFirstPageSelectActivity";
    //搜索职场
    public static String home_search_zhichang = apiurl+"/Star/firstPage/getFirstPageSelectEmploy";
    //首页分类
    public static String home_fenlei = apiurl+"/Star/firstPage/getGroup";
    //分类搜索身份标签
    public static String home_shenfen = apiurl+"/Star/firstPage/getGroupName";
    //分类搜索
    public static String home_shenfen_search = apiurl+"/Star/firstPage/getGroupListSelect";
    /**
     * 广场
     */
    //广场星秀
    public static String square_starshow = apiurl+"/Star/user/getUserBlogs";
    //星秀详情
    public static String square_starshow_more = apiurl+"/Star/user/getUserBlogDetails";
    //关注
    public static String square_guanzhu = apiurl+"/Star/square/getFocusOnBlog";
    //星职场
    public static String square_zhichang = apiurl+"/Star/user/getUserBlogs";
    /**
     * 星活动
     */
    //擂台
    public static String star_leitai = apiurl+"/Star/square/getBattleActivity";
    //星榜
    public static String star_xingbang = apiurl+"/Star/square/getBattleActivityOver";
    //星榜详情
    public static String star_xingbang_more = apiurl+"/Star/square/getOverActivityDetail";

    //星招聘
    public static String star_zhaopin = apiurl+"/Star/square/getBattleEmploy";
    //星榜详情
    public static String bang_more = apiurl+"/Star/square/getOverActivityDetail";

    //活动详情
    public static String activity_more = apiurl+"/Star/firstPage/getActivityDetail";
    //参与人列表
    public static String canyu_people = apiurl+"/Star/firstPage/getActivityUser";
    //招聘详情
    public static String zhaoping_more = apiurl+"/Star/firstPage/getEmployDetail";
    //动态点赞
    public static String dongtaizan = apiurl+"/Star/firstPage/addBlogRecommend";
    //关注公司或者学生
    public static String guanzhu = apiurl+"/Star/firstPage/addfocusOn";

    //发布动态
    public static String updongtai = apiurl+"/Star/user/addUserBlog";

    /**
     * 1.获取作品集编码
     * 2.添加多媒体资料
     * 3.删除作品集资料
     * 4.上传作品集
     */
    public static String getcode = apiurl+"/Star/user/addUserCollection";
    public static String addzuopin = apiurl+"/Star/user/addUserMedia";
    public static String deletezuopin = apiurl+"/Star/user/deleteUserMedia";
    public static String upzuopin = apiurl+"/Star/user/updateUserCollection";
    /**
     * 招聘参数
     * 1 招聘人数
     * 2 薪资
     * 3 福利待遇
     */
    public static String zhaopininfo = apiurl+"/Star/user/getEmployTerm";
    /**上传招聘信息*/
    public static String upzhaopininfo = apiurl+"/Star/user/addEmploy";

}
