package com.example.finallywork.servlet;

import com.example.finallywork.dao.book_dao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//此servlet用于检测书名是否存在
@WebServlet(name = "checkBook",urlPatterns = "/checkBook")
public class checkBook extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        PrintWriter out = response.getWriter();
        String bookname= request.getParameter("bname");//获取书名
        book_dao bs=new book_dao();
        int i=bs.checkBook(bookname);//调用查书方法
        if(i==0){//返回结果
            out.write("0");
        }else{
            out.write("1");
        }
    }

    public void destroy() {
    }

}
