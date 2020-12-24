<%@page import="com.entity.OrderDetail"%>
<%@page import="com.dao.OrderDetailDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script src="js/jquery-1.11.1.js"></script>
</head>
<%
	String orderno=request.getParameter("orderno");
	OrderDetailDao dao=new OrderDetailDao();
	if(orderno!=null){
	List<OrderDetail> list=dao.getOrdtilByOid(Integer.parseInt(orderno));
	
 %>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
				<li><a href="index.jsp">User首页</a></li>
				<li class="current"><a href="orderlist.jsp">我的订单</a></li>
				<li><a href="shopping.jsp">购物车</a></li>
			</ul>
		</div>
		<form method="get" name="search" action="">
			搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
<div id="content" class="wrap">
	<div class="list orderList">
			<table>
				<tr class="title"> 
					<th class="orderId">订单编号</th>
					<th>商品图片</th>
					<th class="userName">商品名</th>
					<th class="price">商品单价</th>
					<th class="createTime">商品数量</th>
					
				</tr>
				
		<%for(OrderDetail odd:list){
		
		%>
		<tr>
		<td><%=orderno %></td>
		<td><img src="<%=odd.getImg() %>"/></td>
		<td class="thumb"><%=odd.getPname() %></td>
		<td><%=odd.getPrice() %></td>
		<td><%=odd.getNum() %></td>
		</tr>		
		<%
		}
		} %>
		
			</table>
		
	</div>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有
</div>

</body>
</html>
