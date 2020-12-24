<%@page import="com.dao.MessageDao"%>
<%@page import="com.entity.User"%>
<%@page import="com.util.PageBean"%>
<%@page import="com.entity.Book"%>
<%@page import="com.dao.BookDao"%>
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'listpro.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
   <script type="text/javascript">
  function delBook(id){
      
     var flag=confirm("你确认要删除吗？");
     if(flag)
     {
        location='dodelBook?id='+id;
     }
   
   }
   </script>
   <script >
		setInterval("location='back/backlistpro.jsp'" ,5000 );
	</script>
  </head>
  
  <body>
  <div id="header" class="wrap">
	<div id="logo"></div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
				<li class="current"><a href="back/backlistpro.jsp">admin首页</a></li>
				<li><a href="back/addbook.jsp">商品添加</a></li>
				<li><a href="back/bkorderlist.jsp">订单管理</a></li>
				<li><a href="AdminAction?method=manageuser">用户管理</a></li>
				<c:if test="${empty sessionScope.currentuser }">
					<li><a href="login.jsp">登录/注册</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.currentuser}">
					<li><a href="UserAction?method=cancel">注销</a></li>
					<li><a href="AdminAction?method=manageuser">${sessionScope.currentuser.uname }</a></li>
				</c:if>
				<%
					MessageDao dao=new MessageDao();
					int n=dao.adGetCountBy();
				
				 %>
				<li><a href="back/bkmessage.jsp">你有<font color="red"><%=n %></font>条消息</a></li>
			</ul>
		</div>
		<form method="get" name="search" action="">
			搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
  
  
  <div id="content" class="wrap">
	<div class="list bookList">
		<form method="post" name="shoping" action="shopping.html">
			<table>
				<tr class="title">
					<th class="checker"></th>
					<th>书名</th>
					<th>作者</th>
					<th class="price">价格</th>
					<th class="store">库存</th>
					<th class="view">图片预览</th>
					<th>管理员操作</th>
				</tr>
				
				<%
				PageBean pb=(PageBean)session.getAttribute("prob");
				if(pb!=null){
				List<Book> list=pb.getList();
				for(Book b:list){
				
				 %>
				<tr>
					<td><input type="checkbox" name="bookId" value="1" /></td>
					<td class="title" style="text-align: center"><%=b.getBname()%></a></td>
					<td><%=b.getAuthor()%></td>
					<td><%=b.getPrice()%></td>
					<td><%=b.getStore()%></td>
					<td class="thumb"><img src="<%=b.getImg()%>" /></td>
					<td><a href="javascript:delBook(<%=b.getId()%>)">删除</a>&nbsp;&nbsp;<a href="back/backmod.jsp?id=<%=b.getId()%>">修改</a></td>
					
				</tr>
		         <%
		         }
		          %>
				
			</table>
			<div class="page-spliter">
				<a href="BackPagelist?page=1">首页</a>
    			<a href="BackPagelist?page=<%=pb.getPage()-1==0?1:pb.getPage()-1%>">上一页</a>
    		    <a href="BackPagelist?page=<%=pb.getPage()+1>=pb.getTotalPage()?pb.getTotalPage():pb.getPage()+1%>">下一页</a>
    			<a href="BackPagelist?page=<%=pb.getTotalPage()%>">尾页</a>
			</div>
			<%} %>
		</form>
	</div>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有

</div>
</body>
</html>
 