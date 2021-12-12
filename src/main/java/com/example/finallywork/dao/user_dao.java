package com.example.finallywork.dao;

import com.example.finallywork.entity.user;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class user_dao {

    public int checkUser(String uname){//检查用户名是否重复
        List<user> list=getUsers();//存储用户信息
        for (user us:list) {//循环比较用户名
            if(us.getUname().equals(uname)){
                return 1;//有重名的
            }
        }
        return 0;//没有重名的
    }

    public List<user> getUsers() {//获取所有用户信息
        List<user> list = new ArrayList<user>();
        try {
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            String sql = "select * from users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();//获取结果
            while (rs.next()) {
                user user = new user();//创建用户对象
                user.setUname(rs.getString("uname"));//用户名
                user.setUtel(rs.getString("utel"));//用户电话
                user.setUemail(rs.getString("uemail"));//邮箱
                user.setUsex(rs.getString("usex"));//性别
                user.setAbout(rs.getString("about"));//简介
                user.setUah(rs.getString("uah"));//爱好
                user.setPswd(rs.getString("upswd"));
                user.setSignature(rs.getString("signature"));//个性签名
                user.setSchool(rs.getString("school"));//学校
                list.add(user);//添加用户信息
            }
            rs.close();
            ps.close();
            conn.close();//关闭连接
            return list;//返回用户信息
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<user> getUsers(int page) {//根据页数获取所有用户信息
        List<user> list = new ArrayList<user>();
        try {
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            String sql = "select * from users limit ?,8";
            PreparedStatement ps = conn.prepareStatement(sql);
            int start=(page-1)*8;
            ps.setInt(1,start);
            ResultSet rs = ps.executeQuery();//获取结果
            while (rs.next()) {
                user user = new user();//创建用户对象
                user.setUname(rs.getString("uname"));//用户名
                user.setUtel(rs.getString("utel"));//用户电话
                user.setUemail(rs.getString("uemail"));//邮箱
                user.setUsex(rs.getString("usex"));//性别
                user.setAbout(rs.getString("about"));//简介
                user.setUah(rs.getString("uah"));//爱好
                user.setSignature(rs.getString("signature"));//个性签名
                user.setSchool(rs.getString("school"));//学校
                list.add(user);//添加用户信息
            }
            rs.close();
            ps.close();
            conn.close();//关闭连接
            return list;//返回用户信息
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public user getUser(String uname){//获取某一用户信息
        user user = new user();//创建用户对象
        try{
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            String sql = "select * from users where uname=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,uname);
            ResultSet rs = ps.executeQuery();//获取结果
            while (rs.next()) {
                user.setUname(rs.getString("uname"));//用户名
                user.setUtel(rs.getString("utel"));//电话
                user.setUemail(rs.getString("uemail"));//邮箱
                user.setUsex(rs.getString("usex"));//性别
                user.setAbout(rs.getString("about"));//简介
                user.setPswd(rs.getString("upswd"));
                user.setUah(rs.getString("uah"));//爱好
                user.setSignature(rs.getString("signature"));//个性签名
                user.setSchool(rs.getString("school"));//学校
            }
            rs.close();
            ps.close();
            conn.close();//关闭连接
            return user;//返回对象
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public int addUser(String uname,String utel,String pswd,String uemail){//注册
        try {
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            if(checkUser(uname)==0){//没有重名
                String sql = "insert into users values (?,null,?,?,?,null,null,null,null)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,uname);
                ps.setString(2,pswd);
                ps.setString(3,utel);
                ps.setString(4,uemail);//设置参数
                ps.executeUpdate();//插入用户信息
                ps.close();
                conn.close();//关闭连接
                return 1;//注册成功
            }
            else{
                conn.close();
                return 0;//失败
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int changUser(String olduname,String uname,String usex,String utel,String pswd,String uemail,String uah,String about,String signature,String school){//修改用户信息
        try {
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            String sql = "UPDATE users SET uname=?,usex=?,upswd=?,utel=?,uemail=?,uah=?,about=?,signature=?,school=? WHERE uname=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,uname);
            ps.setString(2,usex);
            ps.setString(3,pswd);
            ps.setString(4,utel);
            ps.setString(5,uemail);
            ps.setString(6,uah);
            ps.setString(7,about);
            ps.setString(8,signature);
            ps.setString(9,school);
            ps.setString(10,olduname);//设置参数
            ps.executeUpdate();//执行语句
            ps.close();
            conn.close();//关闭连接
            return 1;//更新成功
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    public List<user> search(String keywords,int page){
        try {
            List<user> list = new ArrayList<user>();
            int start=(page-1)*8;
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            String sql = "SELECT * FROM users WHERE uname LIKE ? OR usex LIKE ? LIMIT ?,8";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+keywords+"%");
            ps.setString(2,"%"+keywords+"%");
            ps.setInt(3,start);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                user user=new user();
                user.setUname(rs.getString("uname"));//用户名
                user.setUtel(rs.getString("utel"));//电话
                user.setUemail(rs.getString("uemail"));//邮箱
                user.setUsex(rs.getString("usex"));//性别
                user.setAbout(rs.getString("about"));//简介
                user.setUah(rs.getString("uah"));//爱好
                user.setSignature(rs.getString("signature"));//个性签名
                user.setSchool(rs.getString("school"));//学校
                list.add(user);
            }
            ps.close();
            conn.close();//关闭连接
            return list;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}
