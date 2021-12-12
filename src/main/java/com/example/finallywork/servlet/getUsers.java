package com.example.finallywork.servlet;

import com.example.finallywork.dao.user_dao;
import com.example.finallywork.entity.user;
import net.sf.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//获取全部用户信息
@WebServlet(name = "getUsers",urlPatterns = "/getUsers")
public class getUsers  extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json,charset=utf-8");
        int page=Integer.parseInt(request.getParameter("page"));
        PrintWriter out=response.getWriter();
        user_dao user=new user_dao();//创建操作对象
        List<user> list =user.getUsers(page);//调用获取方法
        JSONArray jsonData = JSONArray.fromObject(list);//封装数据
        out.print(jsonData);
    }
}
