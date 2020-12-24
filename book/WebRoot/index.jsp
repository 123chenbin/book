<%@page import="com.dao.MessageDao"%>
<%@page import="com.entity.User"%>
<%@page import="com.util.PageBean"%>
<%@page import="com.entity.Book"%>
<%@page import="com.dao.BookDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script src="js/jquery-1.11.1.js"></script>
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
					<li><a href="login.jsp">登录/注册</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.currentuser}">
					<li><a href="UserAction?method=cancel">注销</a></li>
					<li><a href="usermanage/usermanage.jsp">欢迎您：${sessionScope.currentuser.uname }</a></li>
				</c:if>
				
			</ul>
		</div>
		<form method="post" name="search" action="firstpagemohu">
			搜索：<input class="input-text" type="text" name="keywords" placeholder="请输入书名或作者" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>



<div id="content" class="wrap">
	<div class="list bookList">
		<form method="post" name="shoping" action="addbook">
			<table id="tab">
				<tr class="title">
					<th style="width:10%"></th>
					<th style="width:15%">书名</th> 
					<th style="width:15%">作者</th>
					<th class="price" style="width:15%">价格</th>
					<th class="store" style="width:15%">库存</th>
					<th class="view" style="width:30%">图片预览</th>
				</tr>
				<%
				PageBean pb=(PageBean)session.getAttribute("prob");
				if(pb!=null){
				List<Book> list=pb.getList();
				for(Book b:list){
				%>
				<tr>
					<td><input type="checkbox" name="bookId" value="<%=b.getId() %>" /></td>
					<td class="title" style="text-align: center"><%=b.getBname() %></td>
					<td><%=b.getAuthor()%></td>
					<td><%=b.getPrice()%></td>
					<td><%=b.getStore()%></td>
					<td class="thumb"><img src="<%=b.getImg()%>" /></td>
				</tr>
		         <%
		         }
		        
				%>
			</table>
			
				
			<div class="page-spliter">
				<a href="qtpage?page=1">首页</a>
    			<a href="qtpage?page=<%=pb.getPage()-1==0?1:pb.getPage()-1%>">上一页</a>
    		    <a href="qtpage?page=<%=pb.getPage()+1>=pb.getTotalPage()?pb.getTotalPage():pb.getPage()+1%>">下一页</a>
    			<a href="qtpage?page=<%=pb.getTotalPage()%>">尾页</a>
			</div>
			<%
			}
			 %>
			<div class="button"><input class="input-btn" type="submit" name="submit" value="" /></div>
		</form>
	</div>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有
</div>
</body>
</html>
