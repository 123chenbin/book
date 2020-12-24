package com.web;
/**
 * 用户操作包括，登录，注册和退出
 * @author zhendejiade
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.entity.User;

public class UserAction extends HttpServlet {

	UserDao dao = new UserDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * 封装三个方法，使用隐藏域传过来的method，判断
	 * 是登录、注册、退出
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		//接收请求
		String method = request.getParameter("method");
		String val=request.getParameter("valcode");
		String xx=(String)request.getSession().getAttribute("valcode");
		//判断接受的method
			if(method.equals("login")){
				//跳到登录判断
				if(val.equals(xx)){
					login(request,response,out);
				}else{
					//response.sendRedirect("login.jsp");
					out.print("<script>alert('验证码输入错误！');location='login.jsp' </script>");

				}
			}else if(method.equals("register")){
				//跳到注册判断
				register(request,response,out);
			}else if(method.equals("cancel")){
				//摧毁会话，注销登录状态
				request.getSession().invalidate();
				out.print("注销成功");
				response.sendRedirect("login.jsp");
			}
		else{
			response.sendRedirect("login.jsp");
			
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 注册后，往数据库插入用户数据
	 * @param request
	 * @param response
	 * @param out
	 */
	private void register(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		
		//1获得请求
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		
		//获得正则表达式判断过的信息结果
		RegisterCheckAction regcheck = new RegisterCheckAction();
		boolean nameflag = regcheck.checkUname(response, request, out);
		boolean pwdflag = regcheck.checkPwd(response, request, out);
		boolean repassword1flag = regcheck.checkRepassword(response, request, out);
		boolean email1flag= regcheck.checkEmail(response, request, out);
		
		User user = new User();
		if(nameflag&&pwdflag&&repassword1flag&&email1flag){
			user.setUname(uname);
			user.setUpwd(upwd);
			user.setEmail(email);
			//2业务处理
			int row = dao.addUser(user);
			if(row!=0){
				try {
					//3跳转页面
					response.sendRedirect("register_success.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		out.print("<script>alert('请校验注册信息');location='back/addadmin.jsp'</script>");
	}

	/**
	 * 登录判断
	 * @param request
	 * @param response
	 * @param out
	 */
	private void login(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		//接收请求
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
		
		//处理业务
		User user  = dao.vallogin(name, pwd);
		if(user == null){
			 out.print("<script>alert('登录失败');location='login.jsp'</script>");
		
		}else{//登陆成功
			
			//把登陆成功的用户存到session里面
			HttpSession session = request.getSession();
			session.setAttribute("currentuser", user);
			System.out.println("管理员权限："+user.getIs_admin());
			try {
				//登录为管理员
				if(user.getIs_admin()>=1){
					response.sendRedirect("BackPagelist");
					//能执行这里面的内容，但是当获得的权限为2的时候，却没有跳转页面
				}else{
					//普通用户
					response.sendRedirect("orderlist.jsp");
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
