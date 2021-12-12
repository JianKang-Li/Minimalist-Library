package com.example.finallywork.entity;
//这是book的JavaBean类
public class book {
    private String bname;
    private String bauthor;
    private int bnumber;
    private String babout;
    private String bposition;
    private String bclass;
    private float bprice;
    private String bpress;
    private String bpresstime;
    public book(){}



    public void setBname(String uname) {
        this.bname = uname;
    }//设置书名

    public void setBauthor(String bauthor) {
        this.bauthor= bauthor;
    }//设置作者

    public void setBnumber(int bnumber) {
        this.bnumber = bnumber;
    }//设置数量

    public void setBabout(String babout) {
        this.babout = babout;
    }//设置简介

    public void setBclass(String bclass) {
        this.bclass = bclass;
    }//设置分类

    public void setBposition(String bposition) {
        this.bposition = bposition;
    }//设置位置

    public void setBpress(String bpress) {
        this.bpress = bpress;
    }//设置出版商

    public void setBpresstime(String bpresstime) {
        this.bpresstime = bpresstime;
    }//设置出版时间

    public void setBprice(float bprice) {
        this.bprice = bprice;
    }//设置价格

    public String getBauthor() {
        return bauthor;
    }//获取书名

    public int getBnumber() {
        return bnumber;
    }//获取数量

    public String getBname(){
        return bname;
    }//获取书名

    public float getBprice() {
        return bprice;
    }//获取价格

    public String getBabout() {
        return babout;
    }//获取简介

    public String getBclass() {
        return bclass;
    }//获取分类

    public String getBposition() {
        return bposition;
    }//获取位置

    public String getBpress() {
        return bpress;
    }//获取出版商

    public String getBpresstime() {
        return this.bpresstime;
    }//获取出版时间


}
