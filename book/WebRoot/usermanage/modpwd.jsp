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
    <title>modpwd.jsp</title>
    <link type="text/css" rel="stylesheet" href="css/style.css" />
    <script type="text/javascript" src="js/jquery-1.11.1.js"></script>
    <script type="text/javascript">
    	$(function(){
    	//判断原密码是否正确
    		$("#initialpwd").change(function(){
    			var initialpwd = $(this).val();
    			$.ajax({
    				url:"UserManageAction",
    				type:"post",
    				data:{"initialpwd":initialpwd,"method":"inilpwd"},
    				success:function(res1){
    					$("#initialpwdinfo").html(res1)
    				}
    			});
    		});
    		
    	//判断现在的密码，是否符合格式
    		$("#upwd").change(function(){
    			var upwd = $(this).val();
    			$.ajax({
    				url:"UserManageAction",
    				type:"post",
    				data:{"upwd":upwd,"method":"nowpwd"},
    				success:function(res2){
    					$("#upwdinfo").html(res2)
    				}
    			});
    		});
    
    	});
    </script>
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
						<li><a href="usermanage/usermanage.jsp">${sessionScope.currentuser.uname }</a></li>
					</c:if>
				</ul>
			</div>
			<form method="get" name="search" action="">
				搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
			</form>
		</div>
	</div>
	<div class="content_message">
				<h1>个人信息密码修改</h1>
				<div class="usermessage">
					原有用户密码：${sessionScope.currentuser.upwd }<br />
					<form action="UserManageAction?method=modpwd" method="post" name="form3">
						请输入当前的密码：<input type="text" name="initialpwd1" id="initialpwd"/><span id="initialpwdinfo"></span><br>
						请输入修改的密码：<input type="text" name="upwd1" id="upwd"/><span id="upwdinfo"></span><br>
						<input type="submit" name="submit" value="确认修改"/>
					</form>
				</div>
	</div>
	<div id="footer" class="wrap">
		网上书城 &copy; 版权所有
	</div>
  </body>
</html>
