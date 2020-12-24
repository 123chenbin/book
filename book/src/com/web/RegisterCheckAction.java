package com.web;
/**
 * 注册检查用户名、密码、邮箱是否符合
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;

public class RegisterCheckAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * 根据传过来的数据，进行判断是否符合格式
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置编码格式
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//获得请求
		String method = request.getParameter("method");
		
		
		if(method.equals("uname")){
			checkUname(response,request,out);
		}else if(method.equals("pwd")){
			checkPwd(response,request,out);
		}else if(method.equals("repassword")){
			checkRepassword(response,request,out);
		}else if(method.equals("email")){
			checkEmail(response,request,out);
		}
		
		out.flush();
		out.close();
	}
	
	/**
	 * 4、判断输入的email是否符合
	 * @param response
	 * @param request
	 * @param out
	 */
	public boolean checkEmail(HttpServletResponse response, HttpServletRequest request, PrintWriter out) {
		
		String email = request.getParameter("email");
		//邮箱（字母开头 + 数字/字母/下划线）
	    String regEx3 = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
	    // 编译正则表达式
	    Pattern pattern3 = Pattern.compile(regEx3);
	    Matcher matcher3 = pattern3.matcher(email);
	    //接受匹配的结果集
	    boolean rs4 = matcher3.matches();
		if(!rs4){
			out.print("邮箱格式不正确！");
		}
		return rs4;
	}

	/**
	 * 3、检查重复输入的密码是否等与刚刚输入的
	 * @param response
	 * @param request
	 * @param out
	 */
	public boolean checkRepassword(HttpServletResponse response, HttpServletRequest request, PrintWriter out) {
		String pwd = request.getParameter("pwd");
		String repassword = request.getParameter("repassword");
		boolean rs3 = pwd.equals(repassword);
		if(!rs3){
			out.print("两次输入不一样！");
		}
		return rs3;
	}

	/**
	 * //2、判断输入的密码是否符合格式
	 * @param response
	 * @param request
	 * @param out
	 */
	public boolean checkPwd(HttpServletResponse response, HttpServletRequest request, PrintWriter out) {
		
		String pwd = request.getParameter("pwd");
		//密码（字母开头 + 数字/字母/下划线,6-20位）
	    String regEx2 = "[A-Za-z][A-Za-z1-9_-]{5,20}+$";
	    // 编译正则表达式
	    Pattern pattern2 = Pattern.compile(regEx2);
	    Matcher matcher2 = pattern2.matcher(pwd);
	    //接受匹配的结果集
	    boolean rs2 = matcher2.matches();
		if(!rs2){
			out.print("密码字母开头6-20位");
		}
		return rs2;
	}

	/**
	 * 1、判断输入的用户名是否符合格式
	 * @param response
	 * @param request
	 * @param out
	 */
	public boolean checkUname(HttpServletResponse response, HttpServletRequest request, PrintWriter out) {
		//获得请求
		String uname = request.getParameter("uname");
		UserDao dao = new UserDao();
		boolean flag = dao.selectUname(uname);
		
		//用户名（字母开头 + 数字/字母/下划线，4-20位）
	    String regEx1 = "[A-Za-z][A-Za-z1-9_-]{3,20}+$";
	    // 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx1);
	    Matcher matcher = pattern.matcher(uname);
	    //接受匹配的结果集
	    boolean rs = matcher.matches();
		if(!rs){
			out.print("只能是英文字母数字4-20位");
		} 
		//判断用户名是否存在
		if(flag){
			out.print("当前用户已存在！");
		}
		return rs;
		
	}

}
