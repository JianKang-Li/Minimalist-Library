package com.example.finallywork.servlet;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
//此servlet用于生成验证码
@WebServlet(name = "GenerateCodeServlet",urlPatterns = "/GenerateCodeServlet")
public class GenerateCodeServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session= request.getSession(true);
        int width=100,height=30,code_len=4;//设置长宽，字符数
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);//创建画布
        Graphics g=image.createGraphics();
        g.setColor(new Color(200,200,200));
        g.fillRect(0,0,width,height);
        char[] codeChar="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();//创建字符数组
        String temp = "";
        Random random = new Random();//随机生成验证码
        for (int i=0;i<code_len;i++){
            int index=random.nextInt(codeChar.length);
            g.setColor(Color.black);
            g.setFont(new Font("Times New Roman",Font.PLAIN,25));
            temp+=codeChar[index];
            g.drawString(temp,22,22);

        }
        System.out.println(temp);
        session.setAttribute("randStr",temp);//存储验证字符串
        ImageIO.write(image,"jpg",response.getOutputStream());

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      doGet(request,response);
    }
}
