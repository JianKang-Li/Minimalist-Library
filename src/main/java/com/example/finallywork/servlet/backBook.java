package com.example.finallywork.servlet;

import com.example.finallywork.dao.lend_dao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//用于还书
@WebServlet(name = "backBook",urlPatterns = "/backBook")
public class backBook extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        PrintWriter out=response.getWriter();
        String bname=request.getParameter("bname");//获取图书名
        String uname=request.getParameter("uname");//获取用户名
        String happentime=request.getParameter("happentime");//获取借阅时间
        lend_dao lendDao=new lend_dao();//借阅对象
        int s=lendDao.deletLend(uname,bname,happentime);//调用删除借阅信息方法
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
