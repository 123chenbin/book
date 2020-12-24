<%@page import="com.entity.User"%>
<%@page import="com.dao.ShopCartDao"%>
<%@page import="com.entity.ShopCart"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>

<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
				<li><a href="qtpage">User首页</a></li>
				<li><a href="orderlist.jsp">我的订单</a></li>
				<li class="current"><a href="shopping.jsp">购物车</a></li>
				<c:if test="${empty sessionScope.currentuser }">
				
				</c:if>
				<c:if test="${not empty sessionScope.currentuser}">
					<li><a href="UserAction?method=cancel">注销</a></li>
					<li><a href="usermanage/usermanage.jsp">欢迎您：${sessionScope.currentuser.uname }</a></li>
				</c:if>
			</ul>
		</div>
		<form method="get" name="search" action="">
			搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>

<script type="text/javascript">
  function delconf(pid){
      
     var flag=confirm("你确认要删除吗？");
     if(flag)
     {
        location='dodelbook?id='+pid;
     }
   
   }
   </script>

	<script>
	
		$(function(){
		//数量的异步刷新
			$(".input-text").change(function(){
				var nu=$(this).val();
				var nuid=$(this).parents("tr").find("td:eq(0)").find("input").val();
				var sp=$(this).next(".spnum");
				$.post("upnum",{"num":nu,"id":nuid},function(res){
					
					sp.html(res);
					
				});
				
			});
			
			//全选和反选
		      $("#checkAll").click(function() {
		       
		        var ch=this.checked;
		        
		        $(".cbox").prop("checked",ch); 
		        
		      });
		       var $subBox = $("input[name='subBox']");
		     $("input[name='subBox']").click(function(){
		        var ch=$subBox.length == $("input[name='subBox']:checked").length ? true : false;
		        $("#checkAll").prop("checked",ch);
		      }); 
            
			//计算总金额
			 var sum=0;
			 $("input[name='subBox']").click(function(){
			    var snum=$(this).parents("tr").find("td:eq(3)").find("input").val();
				var pri=$(this).parents("tr").find("td:eq(4)").find(".sp").text();
			 if($(this).is(':checked')) {
				sum+=snum*pri;
    			$(".spri").html(sum); 
 			}else{
 			
    			$(".spri").html($(".spri").text()-snum*pri); 
 			
 			}if($("input[name='subBox']").is(':checked')){
 				
 			}else{
 			sum=0
 			  $(".spri").html(0); 
 			}
 			 }); 
			
		
			//全选按钮，计算总价
		
		$("#checkAll").click(function(){
		  if($(this).is(':checked')) {
			$.post("sumprice","",function(res){
					$(".spri").html(res); 
				});
		   }else{
				  $(".spri").html(0); 
				 }
		  });
	
		})
		


	</script>

<div id="content" class="wrap">
	<div class="list bookList">
		<form method="post" name="shoping" action="addorder.jsp">
			<table id="tab">
				<tr class="title">
				    <th><input type='checkbox' class='che' id="checkAll" /></th>
					<th class="view">图片预览</th>
					<th>书名</th>
					<th class="nums">数量</th>
					<th class="price">价格</th>
					<th>操作</th>
				</tr>
			<%
			ShopCartDao scd=new ShopCartDao();
			User us=(User)session.getAttribute("currentuser");
		
			List<ShopCart> list=null;
			if(us!=null){
				
			
		   		 list=scd.getShopCartByUnaem(us.getUname());
			
			
			for(ShopCart sc:list){
			%>
			    <tr>
			    	<td><input class="cbox" type='checkbox' name='subBox'  value="<%=sc.getId() %>"/></td>
			        <td class="thumb"><img src='<%=sc.getImg()%>' /></td>
			        <td class="title"><%=sc.getBname() %></td>
			        <td><input  class="input-text" type="text" name="nums" value='<%=sc.getNum() %>'/><span class="spnum"></span></td>
			        <td>￥<span class="sp" ><%=sc.getPrice()%></span></td>
			        <td><a href="javascript:delconf(<%=sc.getId() %>)" >删除</a> </td>
	     		 </tr> 
			<% 
				}
			}
			 %>
			</table>
	
			
			<div class="button">
				<h4>总价：￥<span class="spri">0.00</span>元</h4>
				<input class="input-chart" type="submit" name="submit" value="" />
			</div>
	
			
		</form>
	</div>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有
</div>

</body>
</html>
