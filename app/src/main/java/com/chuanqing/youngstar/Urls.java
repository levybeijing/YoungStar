package com.chuanqing.youngstar;

public class Urls {

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
    //    我的钱包信息
    public static final String getUserWallet = HOST + "Star/user/getUserWallet";
    //

}
