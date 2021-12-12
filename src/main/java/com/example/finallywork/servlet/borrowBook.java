package com.example.finallywork.servlet;

import com.example.finallywork.dao.lend_dao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

//用于借书
@WebServlet(name = "borrowBook",urlPatterns = "/borrowBook")
public class borrowBook extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        PrintWriter out=response.getWriter();
        String bname=request.getParameter("bname");//获取书名
        String uname=request.getParameter("uname");//获取用户名
        Date d = new Date();//创建借阅时间
        Date back = new Date();//创建还书时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        back.setMonth(d.getMonth()+1);
        String date=sdf.format(d);//格式化时间
        String backdate=sdf.format(back);
        lend_dao lendDao=new lend_dao();//创建借阅对象
        int s=lendDao.addLend(uname,bname,date,backdate);//调用借阅方法
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
