<%@page import="com.entity.Book"%>
<%@page import="com.dao.BookDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addbook.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/jquery-1.11.1.js"></script>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <%
  String id=request.getParameter("id");
  if(id!=null){
  BookDao dao=new BookDao();
  Book b=dao.getProById(Integer.parseInt(id));
   %>
  
   <body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		
	</div>
</div>
<div id="register">
	<div class="title">
		<h2>书城修改</h2>
	</div>
	
	 <form action="modBook" method="post" name="form1" enctype="multipart/form-data">
     <input type="hidden" name="id" value="<%=b.getId()%>"/><br>
     <br>
    <input type="text" name="bname" value="<%=b.getBname()%>"/><br>
    <br>
    <input type="text" name="author" value="<%=b.getAuthor()%>"/><br>
    <br>
    <input type="text" name="price" value="<%=b.getPrice()%>"/><br>
    <br>
    <input type="text" name="store" value="<%=b.getStore()%>"/><br>
    <br>
    <input type="file" name="img"    value="<%=b.getImg()%>"/> <br>
    <br>
   	 <input type="submit" value="修改"/>
    </form>

</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有
</div>

</body>

    <%} %>
  </body>

</html>
