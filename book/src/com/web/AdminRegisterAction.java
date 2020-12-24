package com.web;
/**
 * 管理员添加管理员注册信息用户
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CheckDao;
import com.dao.UserDao;
import com.entity.User;

public class AdminRegisterAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		//1获得请求
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("pwd");
		String repassword = request.getParameter("repassword");
		String email = request.getParameter("email");
		//从session里面获得当前用户的管理权限，
		User user1 = (User)request.getSession().getAttribute("currentuser");
		
		//获得正则表达式判断过的信息结果
		
		CheckDao checkdao = new CheckDao();
		boolean nameflag = checkdao.checkUname(uname);
		boolean pwdflag = checkdao.checkUpwd(upwd);
		boolean repassword1flag = checkdao.checkRepassword(upwd,repassword);
		boolean email1flag = checkdao.checkEmail(email);
		
		User user = new User();
		UserDao dao = new UserDao();
		if(nameflag&&pwdflag&&repassword1flag&&email1flag){
			user.setUname(uname);
			user.setUpwd(upwd);
			user.setEmail(email);
			//2业务处理
			int row = dao.addAdmin(user);
			if(row!=0){
				try {
					//3跳转页面
					response.sendRedirect("AdminAction?method=manageuser");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		out.print("<script>alert('请校验注册信息');location='back/addadmin.jsp'</script>");
		
		out.flush();
		out.close();
	}
	
}
