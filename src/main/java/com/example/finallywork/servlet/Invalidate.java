package com.example.finallywork.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//用于退出登录状态，重定向到index.html
@WebServlet(name = "Invalidate",urlPatterns = "/Invalidate")
public class Invalidate extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        session.invalidate();//注销session
        resp.sendRedirect("index.html");
        System.out.println("已重定向至index.html!");
    }
}
