package com.example.finallywork.servlet;

import com.example.finallywork.dao.book_dao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//用于更改书籍信息
@WebServlet(name = "changeBook",urlPatterns = "/changeBook")
public class changeBook extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        PrintWriter out=response.getWriter();
        String oldbname=request.getParameter("oldbname");//获取旧的书名
        String bname=request.getParameter("bname");//新的书名
        String bauthor=request.getParameter("bauthor");//作者
        Integer bnumber= Integer.valueOf(request.getParameter("bnumber"));//数量
        String babout=request.getParameter("babout");//简介
        String bclass=request.getParameter("bclass");//分类
        String bposition=request.getParameter("bposition");//位置
        Float bprice= Float.valueOf(request.getParameter("bprice"));//价格
        String bpress=request.getParameter("bpress");//出版商
        String bpresstime=request.getParameter("bpresstime");//出版时间
        book_dao book=new book_dao();//创建书籍对象
        int s=book.changBook(oldbname,bname,bauthor,bnumber,babout,bclass,bposition,bprice,bpress,bpresstime);//调用修改方法
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
