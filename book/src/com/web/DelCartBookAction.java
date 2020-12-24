package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BookDao;
import com.dao.ShopCartDao;
import com.entity.ShopCart;

public class DelCartBookAction extends HttpServlet {

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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		ShopCartDao sdao=new ShopCartDao();
//删除之前把库存改回去
//		ShopCart sc=sdao.getShopCartById(Integer.parseInt(id));
//		int num=sc.getNum();
//		String name=sc.getBname();
//		BookDao bd=new BookDao();
//		bd.upBookNumByName(name, num);
		
		//通过id删除购物车
		sdao.delCartBook(Integer.parseInt(id));
		response.sendRedirect("shopping.jsp");
		
		out.flush();
		out.close();
	}

}
