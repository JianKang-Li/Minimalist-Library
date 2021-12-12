package com.example.finallywork.entity;
//借阅信息的javaBean
public class lend {
    private String bname;
    private String uname;
    private String date;
    private String backdate;

    public lend(){}

    public void setUname(String uname) {
        this.uname = uname;
    }//设置用户名

    public String getBname() {
        return bname;
    }//获取书名

    public String getDate() {
        return date;
    }//获取借阅日期

    public String getUname() {
        return uname;
    }//获取用户名

    public void setBname(String bname) {
        this.bname = bname;
    }//设置书名

    public void setDate(String date) {
        this.date = date;
    }//设置借阅日期

    public String getBackdate() {
        return backdate;
    }//获取还书日期

    public void setBackdate(String backdate) {
        this.backdate = backdate;
    }//设置还书日期
}
