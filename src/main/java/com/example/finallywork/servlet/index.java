package com.example.finallywork.servlet;

import com.example.finallywork.dao.book_dao;
import com.example.finallywork.entity.book;
import net.sf.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//获取所有书籍信息
@WebServlet(name = "index",urlPatterns = "/index")
public class index extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        int page= Integer.parseInt(request.getParameter("page"));
        PrintWriter out=response.getWriter();
        book_dao book=new book_dao();//创建图书操作对象
        List<book> list=book.getBooks1(page);//调用获取全部图书信息方法
        JSONArray jsonData = JSONArray.fromObject(list);//封装数据
        out.print(jsonData);

    }
}
