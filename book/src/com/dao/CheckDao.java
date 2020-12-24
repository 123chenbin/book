package com.dao;

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 检查用户名、密码、邮箱、是否符合正则表达式标准
 * @author zhendejiade
 *
 */

public class CheckDao {

	/**
	 * 判断用户名是否符合正则表达式标准
	 * 入口：用户名:uname
	 * 出口：bolean rs1
	 */
	public boolean checkUname(String uname){
		//用户名（字母开头 + 数字/字母/下划线，4-20位）
	    String regEx1 = "[A-Za-z][A-Za-z1-9_-]{3,20}+$";
	    // 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx1);
	    Matcher matcher = pattern.matcher(uname);
	    //接受匹配的结果集
	    boolean rs = matcher.matches();
		return rs;
	}
	
	/**
	 * 判断密码是否符合正则表达式标准
	 * 入口：密码:upwd
	 * 出口：boolean rs2
	 */
	public boolean checkUpwd(String upwd){
		//密码（字母开头 + 数字/字母/下划线,6-20位）
	    String regEx2 = "[A-Za-z][A-Za-z1-9_-]{5,20}+$";
	    // 编译正则表达式
	    Pattern pattern2 = Pattern.compile(regEx2);
	    Matcher matcher2 = pattern2.matcher(upwd);
	    //接受匹配的结果集
	    boolean rs2 = matcher2.matches();
		return rs2;
	}
	
	/**
	 * 判断邮箱是否符合正则表达式标准
	 * 入口：email
	 * 出口：boolean rs3
	 */
	public boolean checkEmail(String email){
		//邮箱（字母开头 + 数字/字母/下划线）
	    String regEx3 = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
	    // 编译正则表达式
	    Pattern pattern3 = Pattern.compile(regEx3);
	    Matcher matcher3 = pattern3.matcher(email);
	    //接受匹配的结果集
	    boolean rs3 = matcher3.matches();
		return rs3;
	}
	
	/**
	 * 判断两次密码是否一样
	 */
	public boolean checkRepassword(String upwd, String repassword){
		if(upwd.equals(repassword)){
			return true;
		}
		return false;
	}
	
	
	
}
