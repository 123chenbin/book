 package com.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/imgcode")
public class ValideCodeAction extends HttpServlet {

    private static String codeChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // 获得验证码集合的长度
        int charsLength = codeChars.length();//46
        // 下面3条记录是关闭客户端浏览器的缓冲区

        // 这3条语句都可以关闭浏览器的缓冲区，但是由于浏览器的版本不同，对这3条语句的支持也不同

        // 因此，为了保险起见，同时使用这3条语句来关闭浏览器的缓冲区
        response.setHeader("ragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 设置图形验证码的长和宽
        int width = 90, height = 30;
        //画布
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        //画笔对象
        Graphics g = image.getGraphics();
        
        Random random = new Random();//util
        
        //设置颜色
        g.setColor(getRandomColor(180, 250));//180+30
        //背景颜色
        g.fillRect(0, 0, width, height);

        //设置字体
       g.setFont(new Font("Times New Roman", Font.ITALIC, height));
        // 画出字的颜色
        g.setColor(getRandomColor(120, 180));
        // 用户保存最后随机生成的验证码
        StringBuffer validationCode = new StringBuffer();
        // 验证码的随机字体
        String[] fontNames = { "Times New Roman", "Book antiqua", "Arial" };

        // 随机生成4个验证码
        for (int i = 0; i < 4; i++) {
            // 随机设置当前验证码的字符的字体
            g.setFont(new Font(fontNames[random.nextInt(3)], Font.ITALIC,
                    height));
            // 随机获得当前验证码的字符
            //String .charAt(7);
            char codeChar = codeChars.charAt(random.nextInt(charsLength));
            validationCode.append(codeChar);//6  a  r  x
            // 随机设置当前验证码字符的颜色
            g.setColor(getRandomColor(10, 100));
            // 在图形上输出验证码字符，x和y都是随机生成的
            //drawString("4",x,y)  2  23
            g.drawString(codeChar+"", 18 * i + random.nextInt(7),
                    height - random.nextInt(6));
        }

        // 获得HttpSession对象

        HttpSession session = request.getSession();

        // 设置session对象5分钟失效

        session.setMaxInactiveInterval(5 * 60);

        // 将验证码保存在session对象中,key为validation_code

        session.setAttribute("valcode", validationCode.toString());
         //关闭Graphics对象

        g.dispose();

        OutputStream outS = response.getOutputStream();

        ImageIO.write(image, "JPEG", outS);
        
        outS.close();
    }

    private Color getRandomColor(int minColor, int maxColor) {
          Random random = new Random();
            if(minColor > 255){
                minColor = 255;
            }
            if(maxColor > 255){
                maxColor = 255;
            }
            //获得r的随机颜色值
            //10+230
            //10+221
            //10+20
            int red = minColor+random.nextInt(maxColor-minColor);
            int green = minColor + random.nextInt(maxColor-minColor);
            int blue = minColor + random.nextInt(maxColor-minColor);
            return new Color(red,green,blue);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
