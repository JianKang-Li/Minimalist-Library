package com.example.finallywork.servlet;

import com.example.finallywork.dao.user_dao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//此servlet用于检测用户名是否存在
@WebServlet(name = "checkUser",urlPatterns = "/checkUser")
public class checkUser extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String usname=request.getParameter("uname");//获取用户名
//        System.out.println("usname="+usname);
        user_dao us=new user_dao();//创建用户操作对象
        int i=us.checkUser(usname);//获取结果
        if(i==0){//返回结果
            out.write("0");
        }else{
            out.write("1");
        }
    }

    public void destroy() {
    }

}
