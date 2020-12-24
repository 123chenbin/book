<%@page import="com.dao.MessageDao"%>
<%@page import="com.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
					request.setCharacterEncoding("utf-8");
					User us=(User)session.getAttribute("currentuser");
					if(us!=null){
					MessageDao dao=new MessageDao();
					int n=dao.getCountByNameState(us.getUname());

 %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script src="js/jquery-1.11.1.js"></script>
<script>
	$(function(){
		 
		$.post("listorder","",function(res){
		for(var i=0;i<res.list.length;i++){
		$("#content table").append("<tr><td>"+res.list[i].orderno+"</td><td class=thumb><a href=orderdetail.jsp?orderno="+res.list[i].orderno+">点击查看详情</a></td><td>"+res.list[i].adress+"</td><td>"+res.list[i].tel+"</td><td>"+res.list[i].name+"</td><td>"+res.list[i].totalmoney+"</td><td>"+res.list[i].ordertime+"</td><td>"+res.list[i].state+"</td></tr>");
		};
		$("#spage").html(res.page+"/"+res.totalPage);
		$("a.pagex:eq(0)").attr("name",res.page-1==0?1:res.page-1);
		$("a.pagex:eq(1)").attr("name",1);
		$("a.pagex:eq(2)").attr("name",res.totalPage);
		$("a.pagex:eq(3)").attr("name",res.page+1>=res.totalPage?res.totalPage:res.page+1);
		},"json");
		//点击翻页
		$("a.pagex").click(function(){
			var pp=$(this).attr("name");
			$.post("listorder","page="+pp,function(res){
				$("#content table tr:gt(0)").remove();
				for(var i=0;i<res.list.length;i++){
				$("#content table").append("<tr><td>"+res.list[i].orderno+"</td><td class=thumb><a href=orderdetail.jsp?orderno="+res.list[i].orderno+">点击查看详情</a></td><td>"+res.list[i].adress+"</td><td>"+res.list[i].tel+"</td><td>"+res.list[i].name+"</td><td>"+res.list[i].totalmoney+"</td><td>"+res.list[i].ordertime+"</td><td>"+res.list[i].state+"</td></tr>");
				};
		$("#spage").html(res.page+"/"+res.totalPage);
		$("a.pagex:eq(0)").attr("name",res.page-1==0?1:res.page-1);
		$("a.pagex:eq(1)").attr("name",1);
		$("a.pagex:eq(2)").attr("name",res.totalPage);
		$("a.pagex:eq(3)").attr("name",res.page+1>=res.totalPage?res.totalPage:res.page+1);
		},"json");
		});
		
	});
</script>
<script >
		setInterval("location='orderlist.jsp'" ,5000 );
</script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
				<li><a href="qtpage">User首页</a></li>
				<li class="current"><a href="orderlist.jsp">我的订单</a></li>
				<li><a href="shopping.jsp">购物车</a></li>
				<c:if test="${empty sessionScope.currentuser }">
				
				</c:if>
				<c:if test="${not empty sessionScope.currentuser}">
					<li><a href="UserAction?method=cancel">注销</a></li>
					<li><a href="usermanage/usermanage.jsp">欢迎您：${sessionScope.currentuser.uname }</a></li>
				</c:if>
				
				
				<li><a href="messagelist.jsp?name=<%=us.getUname() %>" >你有<font color="red"><%=n %></font>条消息</a></li>
			</ul>
			<%} %>
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
					<th>订单商品详情</th>
					<th>收货地址</th>
					<th>联系电话</th>
					<th class="userName">收货人</th>
					<th class="price">订单金额</th>
					<th class="createTime">下单时间</th>
					<th class="status">订单状态</th>
				</tr>
				
			</table>
			<div class="page-spliter">
				
				<a class="pagex" name="" href="javascript:void(0)">&lt;</a>
				<a class="pagex" name="" href="javascript:void(0)">首页</a>
				<span id="spage"></span>
				<a class="pagex" name="" href="javascript:void(0)">尾页</a>
				<a class="pagex" name="" href="javascript:void(0)">&gt;</a>
			</div>
			
	</div>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有
</div>

</body>
</html>
