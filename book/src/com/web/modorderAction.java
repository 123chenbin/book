package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.OrderDao;
import com.entity.Order;

public class modorderAction extends HttpServlet {

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
		String orderno=request.getParameter("orderno");
		String uname=request.getParameter("uname");
		String tel=request.getParameter("tel");
		String address=request.getParameter("adress");
		String ostate=request.getParameter("ostate");
		OrderDao dao=new OrderDao();
		Order ord=new Order();
		ord.setName(uname);
		ord.setTel(tel);
		ord.setAdress(address);
		ord.setState(ostate);
		int n=dao.modOrder(ord,Integer.parseInt(orderno));
		if(n>0){
			response.sendRedirect("back/bkorderlist.jsp");
		}
		out.flush();
		out.close();
	}

}
