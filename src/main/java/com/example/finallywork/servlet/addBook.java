package com.example.finallywork.servlet;

import com.example.finallywork.dao.book_dao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//用于书籍信息插入数据库
@WebServlet(name = "addBook",urlPatterns = "/addBook")
public class addBook extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        PrintWriter out=response.getWriter();
        String bname=request.getParameter("bname");//获取书名
        String bauthor=request.getParameter("bauthor");//获取作者
        Integer bnumber= Integer.valueOf(request.getParameter("bnumber"));//获取数量
        String babout=request.getParameter("babout");//获取简介
        String bclass=request.getParameter("bclass");//获取分类
        String bposition=request.getParameter("bposition");//获取位置
        Float bprice= Float.valueOf(request.getParameter("bprice"));//获取价格
        String bpress=request.getParameter("bpress");//获取出版商
        String bpresstime=request.getParameter("bpresstime");//获取出版时间
        book_dao book=new book_dao();//创建数据库对象
        int s=book.addBook(bname,bauthor,bnumber,babout,bclass,bposition,bprice,bpress,bpresstime);//调用添加图书方法
        if(s==1){//向前端返回结果
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
