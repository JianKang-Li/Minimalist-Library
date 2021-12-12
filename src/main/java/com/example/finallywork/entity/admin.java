package com.example.finallywork.entity;
//这是user的JavaBean类
public class admin {
    private String aname;
    private String pswd;
    public admin(){}

    public String getAname(){
        return aname;
    }//获取名字

    public void setAname(String aname) {
        this.aname = aname;
    }//设置名字

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }//设置密码

    public String getPswd() {
        return pswd;
    }//获取密码
}
