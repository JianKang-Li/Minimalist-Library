package com.example.finallywork.fliter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//界面过滤器
//@WebFilter(filterName = "filter",urlPatterns ={ "/user.html","/admin.html"})
public class filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("开始过滤");//标记，监视过滤器运行
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        HttpSession session=request.getSession();
        String uri=request.getRequestURI();//获取请求路径的uri
//        System.out.println(uri);
        String username=(String) session.getAttribute("username");//获取session中的用户名
        String adminname=(String) session.getAttribute("adminname");//获取session中的管理员名
//        System.out.println("username="+username);
//        System.out.println("adminname="+adminname);
        if((username!=null&&uri.equals("/finallywork/user.html"))||(adminname!=null&&uri.equals("/finallywork/admin.html"))){//判断是否为非法访问
//            System.out.println(username+"  "+adminname);
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            response.sendRedirect("login.html");//重定向到登录界面
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
