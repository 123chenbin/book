<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'listpro.jsp' starting page</title>
 <link type="text/css" rel="stylesheet" href="css/style.css" />
  </head>
  
  <body>
  <div id="header" class="wrap">
	<div id="logo"></div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
				<li><a href="back/backlistpro.jsp">admin首页</a></li>
				<li><a href="back/addbook.jsp">商品添加</a></li>
				<li><a href="back/bkorderlist.jsp">订单管理</a></li>
				<li  class="current"><a href="back/useradmin.jsp">用户管理</a></li>
				<c:if test="${empty sessionScope.currentuser }">
					<li><a href="login.jsp">登录/注册</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.currentuser}">
					<li><a href="UserAction?method=cancel">注销</a></li>
					<li><a href="AdminAction?method=manageuser">${sessionScope.currentuser.uname }</a></li>
				</c:if>
			</ul>
		</div>
		<form method="get" name="search" action="">
			搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
  
  
  <div id="content" class="wrap">
	<div class="list bookList">
		<form action="AdminAction" method="post" name="useradmin" >
			<table>
				<tr class="title">
					<th>用户id</th>
					<th>用户名</th>
					<th class="price">用户密码</th>
					<th class="store">邮箱</th>
					<th class="view">管理员权限</th>
					<th>管理员操作|<a href="AdminAction?method=addadmin">添加管理员</a></th>
				</tr>
				<c:forEach items="${list }" var="user">
					<tr>
						<td>${user.id }</td>
						<td>${user.uname }</td>
						<td>${user.upwd }</td>
						<td>${user.email }</td>
						<td>${user.is_admin }</td>
						<td><a href="AdminAction?method=deladmin&uname=${user.uname }">删除</a>|<a href="AdminAction?method=modadmin&uname=${user.uname }">修改权限</a></td>
					</tr>
				</c:forEach>
			</table>
			
			<div class="page-spliter">
				<a href="#">&lt;</a>
				<a href="#">首页</a>
				<span class="current">1</span>
				<a href="#">2</a>
				<a href="#">3</a>
				<a href="#">4</a>
				<span>...</span>
				<a href="#">尾页</a>
				<a href="#">&gt;</a>
			</div>
			
		</form>
	</div>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有

</div>
</body>
</html>
 