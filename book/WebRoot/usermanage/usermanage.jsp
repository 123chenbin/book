<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户管理</title>
    
<link type="text/css" rel="stylesheet" href="css/style.css" />
  </head>
  
<body>
	<div id="header" class="wrap">
		<div id="logo">网上书城</div>
		<div id="navbar">
			<div class="userMenu">
				<ul>
					<li class="current"><a href="index.jsp">User首页</a></li>
					<li><a href="orderlist.jsp">我的订单</a></li>
					<li><a href="shopping.jsp">购物车</a></li>
					<c:if test="${empty sessionScope.currentuser }">
						
					</c:if>
					<c:if test="${not empty sessionScope.currentuser}">
						<li><a href="UserAction?method=cancel">注销</a></li>
						<li><a href="usermanage.jsp">${sessionScope.currentuser.uname }</a></li>
					</c:if>
				</ul>
			</div>
			<form method="get" name="search" action="">
				搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
			</form>
		</div>
	</div>
	<div class="content_message">
				<h1>个人信息</h1>
				<div class="usermessage">
					用户名：${sessionScope.currentuser.uname }<br />
					用户密码：${sessionScope.currentuser.upwd }<a href="SkipCheck?method=modupwdjsp">修改密码</a><br />
					用户邮箱：${sessionScope.currentuser.email }<a href="SkipCheck?method=modemailjsp">修改邮箱</a><br />
				</div>
			</div>
	<div id="footer" class="wrap">
		网上书城 &copy; 版权所有
	</div>
</body>
</html>
