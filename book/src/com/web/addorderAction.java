package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BookDao;
import com.dao.OrderDao;
import com.dao.OrderDetailDao;
import com.dao.ShopCartDao;
import com.entity.OrderDetail;
import com.entity.ShopCart;
import com.entity.User;

public class addorderAction extends HttpServlet {

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
		String uname=request.getParameter("uname");
		String tel=request.getParameter("tel");
		String adress=request.getParameter("adress");
		
		
		ShopCartDao scdao=new ShopCartDao();
		ShopCart sp=new ShopCart();
		String[] strr=(String[]) request.getSession().getAttribute("strr");
		//计算订单总金额
		int num=0;
		double price=0;
		int sum=0;
		for(String ss:strr){
			sp=scdao.getShopCartById(Integer.parseInt(ss));
			num=sp.getNum();
			price=sp.getPrice();
			sum+=num*price;
		}
		User us=(User)request.getSession().getAttribute("currentuser");
		//将订单加入订单数据库
		OrderDao dao=new OrderDao();
		int n=dao.addOrder(uname, sum, us.getUname(), tel, adress);
		//加入订单详情数据库
		OrderDetailDao oddao=new OrderDetailDao();
		BookDao bookdao=new BookDao();
		for(String ss:strr){
			sp=scdao.getShopCartById(Integer.parseInt(ss));//通过id查找购物车里对应的信息
			oddao.addOrderDetail(dao.getMaxOrderno(), sp);//将查找到的信息增加到详情数据库里
			bookdao.upBookNumByName(sp.getBname(), -sp.getNum());//修改商品库存
			scdao.delCartBook(Integer.parseInt(ss));//将购物车里对应的id的信息删除
		}
		
		if(n>0){
			response.sendRedirect("orderlist.jsp");
		}
		out.flush();
		out.close();
	}

}
