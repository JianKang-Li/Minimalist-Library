package com.example.finallywork.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
//用于支撑获取登录用户或登录管理员姓名
@WebServlet(name = "getName",urlPatterns = "/getName")
public class getName extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html,charset=utf-8");
        String type=req.getParameter("type");//获取类型
        PrintWriter out=resp.getWriter();
        HttpSession session=req.getSession();
        if(type.equals("admin")){//获取管理员名字
            String name=(String)session.getAttribute("adminname");//从session中获取管理员名字
            if(name!=null){//返回数据
                out.write(name);
            }else{
                out.write("null");
            }

        }else if(type.equals("user")){//获取用户名字
            String name=(String)session.getAttribute("username");//从session中获取用户名字
            if(name!=null){//返回数据
                out.write(name);
            }else{
                out.write("null");
            }
        }

    }
}
