package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BookDao;
import com.dao.ShopCartDao;
import com.entity.Book;
import com.entity.ShopCart;

public class UpBookNumAction extends HttpServlet {

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String num=request.getParameter("num");
		String id=request.getParameter("id");
		
		
		
		ShopCartDao sdao=new ShopCartDao();
		//获取修改的书
		ShopCart sc=sdao.getShopCartById(Integer.parseInt(id));
		String name=sc.getBname();
		//改变的数量的差值为1-修改后的数量（此处有bug）
		//int sum=1-Integer.parseInt(num);
		BookDao bd=new BookDao();
		
		//通过名字查找，并判断修改数量是否大于库存
		Book b=bd.getProByName(name);
		if(Integer.parseInt(num)<=b.getStore()){
			//更新图书库存
			//bd.upBookNumByName(name, sum);
			//修改后加入购物车数据库
			sdao.upBookNum(Integer.parseInt(id), Integer.parseInt(num));
			
		}else{
			out.print("数量大于库存！");
		}
		
		
		
		out.flush();
		out.close();
	
	}

}
