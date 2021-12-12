package com.example.finallywork.servlet;

import com.example.finallywork.dao.admin_dao;
import com.example.finallywork.dao.user_dao;
import com.example.finallywork.entity.admin;
import com.example.finallywork.entity.user;

import java.io.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//此servlet用于用户验证登录信息和页面的跳转
@WebServlet(name = "login", urlPatterns = "/login")
public class login extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        PrintWriter out = response.getWriter();
        String usname=request.getParameter("uname");//获取名字
        String pswds=request.getParameter("pswd");//获取密码
        String type=request.getParameter("type");//获取类型
        user_dao users=new user_dao();
        List<user> list=users.getUsers();
        admin_dao admin=new admin_dao();
        List<com.example.finallywork.entity.admin> list1=admin.getAdmins();
//        System.out.println(type);
        if(type.equals("管理员")) {//管理员登录检测
            for(admin admin1:list1){
                if(admin1.getAname().equals(usname)){
                    if(admin1.getPswd().equals(pswds)){
                        HttpSession session=request.getSession(true);
                        session.setAttribute("adminname",usname);
                        out.write("1");//登录成功
                    }
                    else{
                        out.write("0");//密码错误
                    }
                    return;
                }
            }
            out.write("2");//管理员不存在
        } else if(type.equals("用户")){//用户登录检测
            for(user us:list){
                if(us.getUname().equals(usname)){
                    if(us.getPswd().equals(pswds)){
                        HttpSession session=request.getSession(true);
                        session.setAttribute("username",usname);
                        out.write("4");//登录成功
                    }
                    else{
                        out.write("0");//密码错误
                    }
                    return;
                }
            }
            out.write("5");//用户不存在
        }
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request,response);
    }

    public void destroy() {
    }
}