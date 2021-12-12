package com.example.finallywork.servlet;

import com.example.finallywork.dao.user_dao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
//用于用户注册信息插入数据库
@WebServlet(name = "validateServlet",urlPatterns = "/validateServlet")
public class validateServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        HttpSession session= request.getSession();
        String code= (String) session.getAttribute("randStr");//获取验证码
        String usecode=request.getParameter("code");//获取输入的验证码
        String uname=request.getParameter("uname");//用户名
        String passwd=request.getParameter("pswd");//密码
        String email=request.getParameter("email");//邮箱
        String tel=request.getParameter("tel");//电话
        if(usecode.equals(code)){//比较验证码
            user_dao userDao=new user_dao();
            userDao.addUser(uname, tel, passwd,email);//调用添加方法
            out.write("1");//注册成功
        }else{
            out.println("验证码输入错误！");
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request,response);
    }

    public void destroy() {
    }
}
