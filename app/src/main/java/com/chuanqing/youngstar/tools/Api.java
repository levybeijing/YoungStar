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
    /**
     * 广场
     */
    //广场星秀
    public static String square_starshow = apiurl+"/Star/user/getUserBlogs";
    //关注
    public static String square_guanzhu = apiurl+"/Star/square/getFocusOnBlog";
    /**
     * 星活动
     */
    //擂台
    public static String star_leitai = apiurl+"/Star/square/getBattleActivity";
    //星榜
    public static String star_xingbang = apiurl+"/Star/square/getBattleActivityOver";
}
