<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script src="../js/jquery-1.11.1.js"></script>
<script>
	$(function(){
		
	$.post("../bkorderlist","",function(res){
		for(var i=0;i<res.list.length;i++){
		$("#content table").append("<tr><td>"+res.list[i].orderno+"</td><td><a href=detail.jsp?orderno="+res.list[i].orderno+">查看详情</a></td><td>"+res.list[i].adress+"</td><td>"+res.list[i].tel+"</td><td>"+res.list[i].name+"</td><td>"+res.list[i].totalmoney+"</td><td>"+res.list[i].ordertime+"</td><td>"+res.list[i].state+"</td><td><a href=modorder.jsp?id="+res.list[i].orderno+">修改</a></td></tr>");
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
			$.post("../bkorderlist","page="+pp,function(res){
				$("#content table tr:gt(0)").remove();
				for(var i=0;i<res.list.length;i++){
				$("#content table").append("<tr><td>"+res.list[i].orderno+"</td><td class=thumb><a href=detail.jsp?orderno="+res.list[i].orderno+">点击查看详情</a></td><td>"+res.list[i].adress+"</td><td>"+res.list[i].tel+"</td><td>"+res.list[i].name+"</td><td>"+res.list[i].totalmoney+"</td><td>"+res.list[i].ordertime+"</td><td>"+res.list[i].state+"</td><td><a href=modorder.jsp?id="+res.list[i].orderno+">修改</a></td></tr>");
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
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城后台管理</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
				<li><a href="backlistpro.jsp">admin首页</a></li>
				<li><a href="addbook.jsp">商品添加</a></li>
				<li class="current"><a href="orderlist.jsp">订单管理</a></li>
				<li><a href="useradmin.jsp">用户管理</a></li>
				<c:if test="${empty sessionScope.currentuser }">
					<li><a href="login.jsp">登录/注册</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.currentuser}">
					<li><a href="UserAction?method=cancel">注销</a></li>
					<li><a href="../AdminAction?method=manageuser">${sessionScope.currentuser.uname }</a></li>
				</c:if>
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
					<th>订单详情</th>
					<th>收货地址</th>
					<th>联系电话</th>
					<th class="userName">收货人</th>
					<th class="price">订单金额</th>
					<th class="createTime">下单时间</th>
					<th class="status">订单状态</th>
					<th class="status">操作</th>
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
