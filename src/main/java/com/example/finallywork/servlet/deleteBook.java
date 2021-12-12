package com.example.finallywork.servlet;

import com.example.finallywork.dao.book_dao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//用于删除书籍信息
@WebServlet(name = "deleteBook",urlPatterns = "/deleteBook")
public class deleteBook extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        PrintWriter out=response.getWriter();
        String bname=request.getParameter("bname");//获取书名
        book_dao book=new book_dao();//创建图书操作对象
        int s=book.deletBook(bname);//调用删除图书方法
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
