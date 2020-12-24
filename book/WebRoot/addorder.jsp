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
<%
 	String[] str=request.getParameterValues("subBox");
 	session.setAttribute("strr", str);

 %>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<form method="get" name="search" action="">
			搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
<div id="register">
	<div class="title">
		<h2>请填写订单信息</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
			<li class="current">1.填写订单信息</li>
			<li class="unpass">2.下单成功</li>
		</ul>
	</div>
	<form method="post" action="addorder"">
		<dl>
			<dt>收  货  人：</dt>
			<dd><input class="input-text" type="text" name="uname" /></dd>
			<dt>联系方式：</dt>
			<dd><input class="input-text" type="text" name="tel" /></dd>
			<dt>收货地址：</dt>
			<dd><input class="input-text" type="text" name="adress" /></dd>
			<dt></dt>
			<dd class="button"><input class="input-tijiao" type="submit"  value="提交订单" /></dd>
		</dl>
	</form>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有
</div>

</body>
</html>
