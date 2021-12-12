package com.example.finallywork.dao;

import com.example.finallywork.entity.lend;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class lend_dao {
    public List<lend> getLends(int page){//根据页数获取所有借阅信息
        List<lend> list = new ArrayList<lend>();//存储所有借阅信息
        try {
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            String sql = "select * from borrow limit ?,8";
            PreparedStatement ps = conn.prepareStatement(sql);
            int start=(page-1)*8;
            ps.setInt(1,start);
            ResultSet rs = ps.executeQuery();//返回结果
            while(rs.next()){//循环向列表添加lend对象
                lend lend=new lend();//创建lend对象
                lend.setBname(rs.getString("bname"));//书名
                lend.setUname(rs.getString("uname"));//用户名
                lend.setDate(rs.getString("happentime"));//借书时间
                lend.setBackdate(rs.getString("backtime"));//应归还时间
                list.add(lend);//添加对象
            }
            rs.close();
            ps.close();
            conn.close();//关闭连接
            return list;//返回列表
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<lend> getUserLends(String uname){//获取某一用户的借阅信息
        List<lend> list = new ArrayList<lend>();//存储借阅信息
        try {
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            String sql = "select * from borrow where uname=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,uname);
            ResultSet rs = ps.executeQuery();//获取结果
            while(rs.next()){//循环存储信息
                lend lend=new lend();//创建lend对象
                lend.setBname(rs.getString("bname"));//书名
                lend.setUname(rs.getString("uname"));//用户名
                lend.setDate(rs.getString("happentime"));//借书时间
                lend.setBackdate(rs.getString("backtime"));//应还书时间
                list.add(lend);//添加数据
            }
            rs.close();
            ps.close();
            conn.close();//关闭连接
            return list;//返回数据
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deletLend(String uname,String bname,String date){//用于还书
        try {
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            String sql = "DELETE FROM borrow WHERE uname=? AND bname=? AND happentime=? ";//删除借书记录
            String sql1="UPDATE books SET bnumber=bnumber+1 WHERE bname=?";//图书数量+1
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ps1.setString(1,bname);
            ps.setString(1, uname);
            ps.setString(2, bname);
            ps.setString(3,date);
            ps.executeUpdate();
            ps1.executeUpdate();//执行sql语句
            ps1.close();
            ps.close();
            conn.close();//关闭连接
            return 1;//删除成功
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    public int addLend(String uname,String bname,String date,String backdate){//借书
        try {
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            String sql = "insert into borrow values(?,?,?,?) ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, uname);
            ps.setString(2, bname);
            ps.setString(3,date);
            ps.setString(4,backdate);
            ps.executeUpdate();//插入借阅信息
            ps.close();
            String sql1="UPDATE books SET bnumber=bnumber-1 WHERE bname=?";//图书剩余数量-1
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ps1.setString(1,bname);
            ps1.executeUpdate();//执行sql1
            ps1.close();
            conn.close();//关闭连接
            return 1;//添加成功
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public List<lend> search(String keywords, int page){
        try {
            List<lend> list = new ArrayList<lend>();
            int start=(page-1)*8;
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            String sql = "SELECT * FROM borrow WHERE bname LIKE ? OR uname LIKE ? OR happentime LIKE ? OR backtime LIKE ? LIMIT ?,8";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+keywords+"%");
            ps.setString(2,"%"+keywords+"%");
            ps.setString(3,"%"+keywords+"%");
            ps.setString(4,"%"+keywords+"%");
            ps.setInt(5,start);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                lend lend=new lend();
                lend.setBname(rs.getString("bname"));//书名
                lend.setUname(rs.getString("uname"));//用户名
                lend.setDate(rs.getString("happentime"));//借书时间
                lend.setBackdate(rs.getString("backtime"));//应还书时间
                list.add(lend);
            }
            ps.close();
            conn.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
