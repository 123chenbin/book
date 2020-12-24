package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.entity.User;
/**
 * 检查登录名，密码是否正确
 * @author zhendejiade
 *
 */
public class CheckAction extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * 检查登录名、登录密码，如果正确不进行提示，如果不正确给出相应提示
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置编码格式
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//获得请求
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
		String method = request.getParameter("method");
		
		System.out.println(name);
		System.out.println(pwd);
		System.out.println(method);
		
		UserDao dao = new UserDao();
		User user = new User();
		//处理业务
		//判断传来的method是账户还是密码
		if(method.equals("user")){
			boolean flag = dao.selectUname(name);
			if(!flag){
				out.print("当前用户不存在！");
			}
		}else{
			user = dao.vallogin(name, pwd);
			if(user == null){
				out.print("密码不正确！");
			}
		}
		out.flush();
		out.close();
	}

}
