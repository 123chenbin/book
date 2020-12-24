package com.web;
/**
 * 管理用户模块中，修改密码和邮箱的页面跳转
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SkipCheck extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * 目的：让所有的用户模块的跳转到本界面，根据判断再跳转回相应界面
	 * 实验：让前台用户，修改自己的密码和账户先跳转到本界面
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		//1、判断接受的是修改密码的请求还是修改邮箱的请求，然后跳到对应页面
		String method = request.getParameter("method");
		//从usermanage获得method请求
		//判断接受的是修改密码的请求还是修改邮箱的请求
			try {
				if(method.equals("modupwdjsp")){
					response.sendRedirect("usermanage/modpwd.jsp");
				} 
				if(method.equals("modemailjsp")){
					response.sendRedirect("usermanage/modemail.jsp");
				}
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		out.flush();
		out.close();
	}

}
