package com.example.finallywork.servlet;

import com.example.finallywork.dao.lend_dao;
import com.example.finallywork.dao.user_dao;
import com.example.finallywork.entity.lend;
import com.example.finallywork.entity.user;
import net.sf.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//获取个人信息或借阅信息
@WebServlet(name = "getUserMessage",urlPatterns = "/getUserMessage")
public class getUserMessage extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        HttpSession session=request.getSession();
        String username=(String)session.getAttribute("username");//获取用户名
        String type=request.getParameter("type");//获取类型
        PrintWriter out=response.getWriter();
        if(type.equals("1")) {//获取个人信息
            user_dao userDao=new user_dao();
            user user=new user();
            user=userDao.getUser(username);
            JSONArray jsonData = JSONArray.fromObject(user);//封装数据
            out.print(jsonData);
        }else if(type.equals("2")){//获取个人借阅信息
            lend_dao lendDao=new lend_dao();
            List<lend> list= lendDao.getUserLends(username);
            JSONArray jsonData = JSONArray.fromObject(list);//封装数据
            out.print(jsonData);
        }

    }
}
