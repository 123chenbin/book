package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BookDao;
import com.dao.ShopCartDao;
import com.entity.Book;
import com.entity.ShopCart;
import com.entity.User;

public class AddShopCartAction extends HttpServlet {

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
		ShopCartDao sdao=new ShopCartDao();
		request.setCharacterEncoding("utf-8");
		BookDao bdao=new BookDao();
		List<ShopCart> list=new ArrayList<ShopCart>();
		String[] arr=request.getParameterValues("bookId");
		User us=(User)request.getSession().getAttribute("currentuser");
		for(String s:arr){
			
			//根据添加书的id减少库存
		//	bdao.upBookNum(Integer.parseInt(s));
			
			Book b=new Book();
			b=bdao.getProById(Integer.parseInt(s));
			ShopCart sc=new ShopCart(); 
			sc.setBname(b.getBname());
			sc.setPrice(b.getPrice());
			sc.setNum(1);
			sc.setImg(b.getImg());
			sc.setUsername(us.getUname());
			list.add(sc);
		}
		
		//加入购物车数据库
		for(ShopCart st:list){
			
			sdao.addShopCart(st);
		}
		response.sendRedirect("shopping.jsp");
		
		out.flush();
		out.close();
	}

}
