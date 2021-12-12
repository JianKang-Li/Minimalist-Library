package com.example.finallywork.dao;

import com.example.finallywork.entity.book;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class book_dao {

    public int checkBook(String bname){//检查书名是否重复
        List<book> list=getBooks();//获取所有图书信息
        for (book bs:list) {//循环比较图书名字
            if(bs.getBname().equals(bname)){
                return 1;//有重名的
            }
        }
        return 0;//没有重名的
    }

    public List<book> getBooks1(int page) {//根据页数获取所有图书信息
        List<book> list = new ArrayList<book>();//存储图书信息
        try {
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();
            int start=(page-1)*8;
            String sql = "SELECT * FROM books LIMIT ?,8";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,start);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){//循环向列表中添加图书信息
                book book=new book();//创建图书对象
                book.setBname(rs.getString("bname"));//书名
                book.setBauthor(rs.getString("bauthor"));//作者
                book.setBnumber(rs.getInt("bnumber"));//数量
                book.setBabout(rs.getString("babout"));//简介
                book.setBclass(rs.getString("bclass"));//分类
                book.setBposition(rs.getString("bpostion"));//位置
                book.setBprice(rs.getFloat("bprice"));//价格
                book.setBpress(rs.getString("bpress"));//出版商
                book.setBpresstime(rs.getString("bpresstime"));//出版时间
                list.add(book);//向列表添加书籍
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

    public List<book> getBooks() {//获取所有图书信息
        List<book> list = new ArrayList<book>();//存储图书信息
        try {
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();
            String sql = "SELECT * FROM books";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){//循环向列表中添加图书信息
                book book=new book();//创建图书对象
                book.setBname(rs.getString("bname"));//书名
                book.setBauthor(rs.getString("bauthor"));//作者
                book.setBnumber(rs.getInt("bnumber"));//数量
                book.setBabout(rs.getString("babout"));//简介
                book.setBclass(rs.getString("bclass"));//分类
                book.setBposition(rs.getString("bpostion"));//位置
                book.setBprice(rs.getFloat("bprice"));//价格
                book.setBpress(rs.getString("bpress"));//出版商
                book.setBpresstime(rs.getString("bpresstime"));//出版时间
                list.add(book);//向列表添加书籍
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

    public book getBook(String bname){//获取某一本书的信息
        book book=new book();//创建对象存储信息
        try{
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();
            String sql = "select * from books where bname=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,bname);
            ResultSet rs = ps.executeQuery();//连接数据库查询数据
            while (rs.next()) {
                book.setBname(rs.getString("bname"));//书名
                book.setBauthor(rs.getString("bauthor"));//作者
                book.setBnumber(rs.getInt("bnumber"));//数量
                book.setBabout(rs.getString("babout"));//简介
                book.setBclass(rs.getString("bclass"));//分类
                book.setBposition(rs.getString("bpostion"));//位置
                book.setBprice(rs.getFloat("bprice"));//价格
                book.setBpress(rs.getString("bpress"));//出版商
                book.setBpresstime(rs.getString("bpresstime"));//出版日期
            }
            rs.close();
            ps.close();
            conn.close();//关闭连接
            return book;//返回图书信息
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public int addBook(String bname,String bauthor,int bnumber,String babout,String bclass,String bposition,float bprice,String bpress,String bpresstime){//添加图书
        try {
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            if(checkBook(bname)==0){//没有重复的图书
                String sql = "insert into books values (?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);//预处理
                ps.setString(1,bname);//书名
                ps.setString(2,bauthor);//作者
                ps.setInt(3,bnumber);//数量
                ps.setString(4,babout);//简介
                ps.setString(5,bclass);//分类
                ps.setString(6,bposition);//位置
                ps.setFloat(7,bprice);//价格
                ps.setString(8,bpress);//出版商
                ps.setString(9,bpresstime);//出版时间
                ps.executeUpdate();//添加图书
                ps.close();
                conn.close();//关闭连接
                return 1;//添加成功
            }else{
                conn.close();
                return 0;//有重复的书
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int changBook(String oldbname,String bname,String bauthor,int bnumber,String babout,String bclass,String bposition,float bprice,String bpress,String bpresstime){//修改图书信息
        try {
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            String sql = "UPDATE books SET bname=?,bauthor=?,bnumber=?,babout=?,bclass=?,bpostion=?,bprice=?,bpress=?,bpresstime=? WHERE bname=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,bname);//新书名
            ps.setString(2,bauthor);//新作者名
            ps.setInt(3,bnumber);//数量
            ps.setString(4,babout);//简介
            ps.setString(5,bclass);//分类
            ps.setString(6,bposition);//位置
            ps.setFloat(7,bprice);//价格
            ps.setString(8,bpress);//出版商
            ps.setString(9,bpresstime);//出版时间
            ps.setString(10,oldbname);//书籍原来的名字
            ps.execute();//更新书籍信息
            ps.close();
            conn.close();//关闭连接
            return 1;//更新成功
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int deletBook(String bname){//删除图书信息
        try {
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            String sql = "delete from books where bname=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bname);
            ps.executeUpdate();//删除书籍信息
            ps.close();
            conn.close();//关闭连接
            return 1;//更新成功
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public List<book> search(String keywords, int page){
        try {
            List<book> list = new ArrayList<book>();
            int start=(page-1)*8;
            String DSNAME = "java:comp/env/jdbc/booksDB";
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(DSNAME);
            Connection conn = ds.getConnection();//建立连接
            String sql = "SELECT * FROM books WHERE bname LIKE ? OR bauthor LIKE ? OR bclass LIKE ? LIMIT ?,8";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+keywords+"%");
            ps.setString(2,"%"+keywords+"%");
            ps.setString(3,"%"+keywords+"%");
            ps.setInt(4,start);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                book book=new book();
                book.setBname(rs.getString("bname"));//书名
                book.setBauthor(rs.getString("bauthor"));//作者
                book.setBnumber(rs.getInt("bnumber"));//数量
                book.setBabout(rs.getString("babout"));//简介
                book.setBclass(rs.getString("bclass"));//分类
                book.setBposition(rs.getString("bpostion"));//位置
                book.setBprice(rs.getFloat("bprice"));//价格
                book.setBpress(rs.getString("bpress"));//出版商
                book.setBpresstime(rs.getString("bpresstime"));//出版日期
                list.add(book);
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
