package com.example.finallywork.servlet;

import com.example.finallywork.dao.user_dao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//用于更改用户信息
@WebServlet(name = "changeUser",urlPatterns = "/changeUser")
public class changeUser extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        PrintWriter out=response.getWriter();
        String olduname=request.getParameter("olduname");//旧用户名
        String uname=request.getParameter("uname");//新用户名
        String usex=request.getParameter("usex");//性别
        String school= request.getParameter("school");//学校
        String about=request.getParameter("about");//简介
        String uah=request.getParameter("uah");//爱好
        String utel=request.getParameter("utel");//电话
        String pswd= request.getParameter("pswd");//密码
        String uemail=request.getParameter("uemail");//邮箱
        String signature=request.getParameter("signature");//个性签名
        user_dao user=new user_dao();//创建用户操作对象
        int s=user.changUser(olduname,uname,usex,utel,pswd,uemail,uah,about,signature,school);//调用修改信息方法
        if(s==1){//返回结果
            out.write("1");
        }else{
            out.write("0");
        }


    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request,response);
    }

    public void destroy() {
    }
}
