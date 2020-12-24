<%@page import="com.entity.Message"%>
<%@page import="com.dao.MessageDao"%>
<%@page import="com.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />

</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	
	</div>
</div>
<div id="register">
	<div class="title">
		<h2>我的消息</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
		<%
		User us=(User)session.getAttribute("currentuser");
		MessageDao dao=new MessageDao();
		List<Message> list=dao.getCountByName(us.getUname());
		 %>
			<li class="unpass"><a href="userMessage?name=<%=us.getUname()%>">返回我的订单</a></li>
		</ul>
	</div>
	<%
	for(Message mm:list){
	%>
	<dl><%
		if(mm.getUmessage()!=null){
		%>
			<dd style="color:blue"><%=us.getUname()%>:</dd>
			<dt><%=mm.getUmessage() %></dt>
		<% 
		}
			if(mm.getAdmessage()!=null){
			
			%>
			
		<dd style="color:blue">客服:</dd>
		<dt><%=mm.getAdmessage()%></dt>
			<% 
			}
		
		%>
		
	</dl>
	
	<%
	}
	 %>
	<form method="post" action="addmessage">
	<input type="hidden" name="uname" value="<%=us.getUname()%>"/>
		<dl>
			<dt>发消息：</dt>
			<dd><textarea class="input-text"  name="message"></textarea></dd>
			<dt></dt>
			<dd class="button"><input class="input-tijiao" type="submit"  value="发送消息" /></dd>
		</dl>
	</form>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有
</div>

</body>
</html>
