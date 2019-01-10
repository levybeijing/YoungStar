package com.chuanqing.youngstar.login._student;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private String imgpath;
    private String sex;

    private String name;
    private String phone;
    private String school;
    private String major;
    private String studentNo;
    private String cardId;
    private String email;

    public Student(){

    }

    protected Student(Parcel in) {
        imgpath = in.readString();
        sex = in.readString();
        name = in.readString();
        phone = in.readString();
        school = in.readString();
        major = in.readString();
        studentNo = in.readString();
        cardId = in.readString();
        email=in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imgpath);
        dest.writeString(sex);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(school);
        dest.writeString(major);
        dest.writeString(studentNo);
        dest.writeString(cardId);
    }
}
