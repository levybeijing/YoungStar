package com.chuanqing.youngstar;

import android.os.Environment;

import java.io.File;

public class Urls {
    //    音频存储路径
    public static final String AUDIOPATH = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"star/audio/";
    //    视频存储路径
    public static final String VIDEOPATH = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"star/video/";
    //    图片存储路径
    public static final String IMGPATH = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"star/img/";
    //    OSS 阿里上传路径  图片命名规范:2483989924/20190107 1354459140.png
    public static final String IMAGEURL = "https://star-1.oss-cn-beijing.aliyuncs.com/";

    public static final String HOST = "http://39.107.70.80:8080/";
    //    获取验证码
    public static final String getSms = HOST + "Star/user/getSms";
    //    验证验证码
    public static final String checkSms = HOST + "Star/user/checkSms";
    //    用户注册
    public static final String addUser = HOST + "Star/user/addUser";
    //    密码登录
    public static final String logen = HOST + "Star/user/logen";
    //    选择学生身份
    public static final String addStudent = HOST + "Star/user/addStudent";
    //    选择公司身份
    public static final String addCompany = HOST + "Star/user/addCompany";
    //    选择投资人身份
    public static final String addInvestor = HOST + "Star/user/addInvestor";
    //    查询身份标签
    public static final String getLabel = HOST + "Star/user/getLabel";
    //    我的个人页信息
    public static final String getUserByCode = HOST + "Star/user/getUserByCode";
    //    我的动态列表
    public static final String getUserBlog = HOST + "Star/user/getUserBlog";
    //    我的作品集列表
    public static final String getUserCollection = HOST + "Star/user/getUserCollection";
    //     我的公司公告
    public static final String getCompanyBlog = HOST + "Star/user/getCompanyBlog";
    //    我的公司招聘
    public static final String getEmploy = HOST + "Star/user/getEmploy";
    //   粉丝投资人我的
    public static final String getBlogsINMy = HOST + "Star/user/getBlogsINMy";
    //   官方消息
    public static final String getUserSystemInfo = HOST + "Star/user/getUserSystemInfo";
    //   官方消息详情
    public static final String getUserSystemInfoDetails = HOST + "Star/user/getUserSystemInfoDetails";
    //   删除官方消息
    public static final String deleteSystemINFO = HOST + "Star/user/deleteSystemINFO";

    //    我的钱包信息
    public static final String getUserWallet = HOST + "Star/user/getUserWallet";
    //    学生参加的活动
    public static final String getActivityUser = HOST + "Star/user/getActivityUser";
    //    我关注的学生
    public static final String getUserConcernStudent = HOST + "Star/user/getUserConcernStudent";
    //    学生关注的公司
    public static final String getUserConcernCompany = HOST + "Star/user/getUserConcernCompany";

}
