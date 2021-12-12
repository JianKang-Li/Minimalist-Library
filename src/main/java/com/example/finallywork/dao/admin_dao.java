package com.example.finallywork.dao;

import com.example.finallywork.entity.admin;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//用于管理管理员信息
public class admin_dao {

    public int checkAdmin(String aname){//检查管理员是否存在
        List<admin> list=getAdmins();//获取所有管理员名字
        for (admin as:list) {//循环比较管理员名字
            if(as.getAname().equals(aname)){
                return 1;//有重名的
            }
        }
        return 0;//没有重名的
    }

    public List<admin> getAdmins() {//获取所有管理员信息
        List<admin> list = new ArrayList<admin>();//用以存储管理员名字
        try {
            String DSNAME = "java:comp/env/jdbc/booksDB";//连接数据库查询管理员数据
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            String sql = "select * from admins";//查询所有管理员信息
            PreparedStatement ps = conn.prepareStatement(sql);//预处理
            ResultSet rs = ps.executeQuery();//执行sql语句
            while (rs.next()) {//循环像列表中添加管理员对象
                admin admin=new admin();//创建管理员对象
                admin.setAname(rs.getString("aname"));//设置名字
                admin.setPswd(rs.getString("apswd"));//设置密码
                list.add(admin);//添加对象
            }
            rs.close();//关闭结果
            ps.close();//关闭对象
            conn.close();//关闭连接
            return list;//返回列表
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
