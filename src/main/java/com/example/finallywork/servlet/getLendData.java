package com.example.finallywork.servlet;

import com.example.finallywork.dao.lend_dao;
import com.example.finallywork.entity.lend;
import net.sf.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
//获取全部借阅信息
@WebServlet(name = "getLendData",urlPatterns = "/getLendData")
public class getLendData  extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        int page=Integer.parseInt(request.getParameter("page"));
        PrintWriter out=response.getWriter();
        lend_dao  lend=new lend_dao();//创建借阅信息对象
        List<lend> list=lend.getLends(page);//调用借阅信息获取方法
        JSONArray jsonData = JSONArray.fromObject(list);//封装数据
        out.print(jsonData);
    }
}
