package com.example.finallywork.entity;
//这是user的JavaBean类
public class user {
    private String uname;
    private String utel;
    private String uemail;
    private String pswd;
    private String usex;
    private String uah;
    private String about;
    private String signature;
    private String school;
    public user(){}

    public String getUname(){
        return uname;
    }//获取用户名

    public void setUname(String uname) {
        this.uname = uname;
    }//设置用户名

    public String getUtel() {
        return utel;
    }//获取用户电话

    public void setUtel(String utel) {
        this.utel = utel;
    }//设置电话

    public String getUemail() {
        return uemail;
    }//获取邮箱

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }//设置邮箱

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }//设置密码

    public String getPswd() {
        return pswd;
    }//获取密码

    public void setAbout(String about) {
        this.about = about;
    }//设置简介

    public String getAbout() {
        return about;
    }//获取简介

    public String getSchool() {
        return school;
    }//获取学校

    public String getSignature() {
        return signature;
    }//获取个性签名

    public String getUah() {
        return uah;
    }//获取爱好

    public String getUsex() {
        return usex;
    }//获取性别

    public void setSchool(String school) {
        this.school = school;
    }//设置学校

    public void setSignature(String signature) {
        this.signature = signature;
    }//设置个性签名

    public void setUah(String uah) {
        this.uah = uah;
    }//设置爱好

    public void setUsex(String usex) {
        this.usex = usex;
    }//设置性别
}
