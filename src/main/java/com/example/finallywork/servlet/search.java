package com.example.finallywork.servlet;


import com.example.finallywork.dao.book_dao;
import com.example.finallywork.dao.lend_dao;
import com.example.finallywork.dao.user_dao;
import com.example.finallywork.entity.book;
import com.example.finallywork.entity.lend;
import com.example.finallywork.entity.user;
import net.sf.json.JSONArray;

import java.io.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//此servlet用于用户搜索
@WebServlet(name = "search", urlPatterns = "/search")
public class search extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json,charset=utf-8");
        PrintWriter out = response.getWriter();
        String keyword=request.getParameter("keyword");//获取关键字
        int page=Integer.parseInt(request.getParameter("page"));
        String type=request.getParameter("type");
        if(type.equals("user")){
            user_dao users=new user_dao();
            List<user> list=users.search(keyword,page);
            JSONArray jsonData = JSONArray.fromObject(list);//封装数据
            out.print(jsonData);
        }else if(type.equals("lend")){
            lend_dao lends=new lend_dao();
            List<lend> list=lends.search(keyword,page);
            JSONArray jsonData = JSONArray.fromObject(list);//封装数据
            out.print(jsonData);
        }else if(type.equals("book")){
            book_dao books=new book_dao();
            List<book> list=books.search(keyword,page);
            JSONArray jsonData = JSONArray.fromObject(list);//封装数据
            out.print(jsonData);
        }

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request,response);

    }

    public void destroy() {
    }
}