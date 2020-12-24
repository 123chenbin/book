package com.web;
/**
 * 用户管理界面，进行用户密码修改，邮箱修改的判断
 * 判断后，执行修改的异步传输判断，然后可以执行修改操作
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

public class UserManageAction extends HttpServlet {

	//调用正则表达式检查用户名、密码、邮箱的工具包
	CheckDao checkdao = new CheckDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//2、通过ajax判断要修改的密码、邮箱是否符合格式，原密码是否正确
		checkModPwd(request,response,out);
		checkModEmail(request,response,out);
		
		//3、向数据库根据用户名，修改用户密码和邮箱
		modUser(request,response,out);
		
		
		out.flush();
		out.close();
	}
	/**
	 * 通过ajax判断要修改的邮箱是否符合格式，原密码是否正确,原密码和现在的密码是否相同
	 * @param request
	 * @param response
	 * @param out
	 */
	private void checkModEmail(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		//获得从modpwd.jsp传过来的method判断是进行以前密码格式，还是现在
		String method = request.getParameter("method");
		//获取邮箱
		String email = request.getParameter("email");
		
		//获得正则表达式判断过的信息结果
		if(method.equals("email")){
			if(!checkdao.checkEmail(email)){
				out.print("请输入正确的邮箱");
			}
		}
	}
		
	/**
	 * 向数据库根据用户名，修改用户密码和邮箱
	 * @param request
	 * @param response
	 * @param out
	 */
	private void modUser(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		//获得请求
		//从session获得当前用户
		User user = (User)request.getSession().getAttribute("currentuser");
		String inipwd = user.getUpwd();
		String uname = user.getUname();
		UserDao dao = new UserDao();
		//从usermanage获得method请求
		String method = request.getParameter("method");
		//从modemail获得修改后的密码
		String upwd1 = request.getParameter("upwd1");
		String initialpwd1 = request.getParameter("initialpwd1");
		
		String email1 = request.getParameter("email");
		
		try {
			if(method.equals("modpwd")){
				//判断密码是否符合正则表达式
				if(!initialpwd1.equals(inipwd)){
					out.print("<script>alert('密码错误！');location='usermanage/modpwd.jsp'</script>");
				}
				if(!checkdao.checkUpwd(upwd1)){
					out.print("<script>alert('字母开头6-20位');location='usermanage/modpwd.jsp'</script>");
				}if(!inipwd.equals(upwd1)){
					out.print("<script>alert('不能和以前的密码相同！');location='usermanage/modpwd.jsp'</script>");
				}else{
				//向数据库根据用户名，修改用户密码
				int row = dao.modUserPwd(uname, upwd1);
				response.sendRedirect("usermanage/modsuccess.jsp");
				}
			}
			if(method.equals("modemail")){
				//判断输入的邮箱是否符合正则表达式
				if(!checkdao.checkEmail(email1)){
					out.print("<script>alert('请输入正确的邮箱');location='usermanage/modemail.jsp'</script>");
				}else{
				//向数据库根据用户名，修改用户邮箱
				int row1 = dao.modUserEmail(uname, email1);
				response.sendRedirect("usermanage/modsuccess.jsp");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
	/**
	 * 通过ajax判断要修改的密码是否符合格式，原密码是否正确,原密码和现在的密码是否相同
	 * @param request
	 * @param response
	 * @param out
	 */
	private void checkModPwd(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		//获得从modpwd.jsp传过来的method判断是进行以前密码格式，还是现在
		String method = request.getParameter("method");
		//获取当前填入的以前的密码
		String initialpwd = request.getParameter("initialpwd");
		//获取当前填入的，想要修改的密码
		String upwd = request.getParameter("upwd");
		
		//先获取当前用户的密码
		User user = (User)request.getSession().getAttribute("currentuser");
		String pwd = user.getUpwd();
		
		//1、以前的密码判断，是否等于数据库中的密码
		if(method.equals("inilpwd")){
			if(!pwd.equals(initialpwd)){
				out.print("输入的密码不正确");
			}
		}
		//2、先判断，输入的密码是否和以前的相等，如果不相等再进行正则表达式判断
		//获得正则表达式判断过的信息结果
		if(method.equals("nowpwd")){
			if(upwd.equals(pwd)){
				out.print("不能和以前的密码相同！");
			}else if(!checkdao.checkUpwd(upwd)){
				out.print("字母开头6-20位");
			}
		}
	}
	
	
	

}
