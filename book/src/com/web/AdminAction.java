package com.web;
/**
 * 登录到useradmin页面加载所有数据库中用户；
 * 管理员操作，进行增加管理员用户，删除用户，修改管理员权限
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.entity.User;

public class AdminAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * 根据传过来的method值，判断传过来的是哪种操作
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		//获得请求
		//根据传过来的值判断做增加管理员、删除用户、修改权限的操作
		String method = request.getParameter("method");
		String uname = request.getParameter("uname");
		
		User user = (User)request.getSession().getAttribute("currentuser");
		String name = user.getUname();
		
		UserDao dao = new UserDao();
		
		User user1 = dao.selectUserByUname(uname);
		int is_admin =  user1.getIs_admin();
		//处理业务，查询所有用户信息
		List<User> list = dao.selectAll();
		request.getSession().setAttribute("list", list);
		
		if(method.equals("manageuser")){
			response.sendRedirect("back/useradmin.jsp");
		}else if(method.equals("addadmin")){
			//添加管理员
			//只有当是超级管理员用户才能进行添加管理员操作
			if(is_admin == 2){
				response.sendRedirect("back/addadmin.jsp");
			}else{
				out.print("<script>alert('需要超级管理员用户权限');location='back/useradmin.jsp'</script>");
			}
			
		}else if(method.equals("deladmin")){
			//删除用户
			if(!uname.equals(name)){
				dao.delUser(uname);
				out.print("<script>alert('删除成功!');location='AdminAction?method=manageuser'</script>");

			}else{
				out.print("<script>alert('不能删除自己！');location='AdminAction?method=manageuser'</script>");
			}
		}else if(method.equals("modadmin")){
			//修改普通用户权限变为管理员
			if(!uname.equals(name)){
				//如果传过来的值是1，让用户变为管理员，如果为0，变为普通用户

				if(is_admin == 1){
					dao.modUser(uname);
					out.print("<script>location='AdminAction?method=manageuser'</script>");
				}else{
					dao.modManageUser(uname);
					out.print("<script>location='AdminAction?method=manageuser'</script>");
				}
			}else{
				out.print("<script>alert('不能更改自己的权限！');location='AdminAction?method=manageuser'</script>");
			}
		}
		
		out.flush();
		out.close();
	}

}
