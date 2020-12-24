<%@page import="com.entity.Order"%>
<%@page import="com.dao.OrderDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	

  </head>
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
		<h2>管理员修改订单</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
			<li class="current">1.修改订单信息</li>
			<li class="unpass">2.修改成功</li>
		</ul>
	</div>
	<%
  	String orderno=request.getParameter("id");
  	if(orderno!=null){
  	OrderDao dao=new OrderDao();
  	Order or= dao.getOrderByOno(Integer.parseInt(orderno));
  	
   %>
	<form method="post" action="modorder">
	 <input type="hidden" name="orderno" value="<%=orderno%>"/>
	
			收  货  人：<input  type="text" name="uname" value="<%=or.getName() %>"/><br><br>
			联系方式：<input  type="text" name="tel" value="<%=or.getTel() %>"/><br><br>
			收货地址：<input  type="text" name="adress" value="<%=or.getAdress() %>"/><br><br>
			订单状态：<select name="ostate">
		 			<option value="已发货" <%=or.getState().equals("已发货")?"selected":"" %>>已发货</option>
		 			<option value="已签收" <%=or.getState().equals("已签收")?"selected":"" %>>已签收</option>
		 			<option value="待审核" <%=or.getState().equals("待审核")?"selected":"" %>>待审核</option>
 				</select><br><br>
			
			总  价  格：<%=or.getTotalmoney() %><br><br>
		<dl>
			<dt></dt>
			<dd class="button"><input class="input-xiugai" type="submit"  value="修改" /></dd>
		</dl>
	</form>
	<%} %>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有
</div>

</body>
</html>
