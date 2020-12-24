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
  <script type="text/javascript">
  $(function(){
  $("#bb").blur(function(){
   var mm=$(this).val();
   
   $.ajax({
   url:"backadd",
   type:"post",
   data:"bname="+mm,
   success:function(res){
   $("#nameinfo").html(res);
   
   }
   
   })
  
  
  })
  
  
  })
  
  
  
  </script>
   <body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		
	</div>
</div>
<div id="register">
	<div class="title">
		<h2>增加书城</h2>
	</div>
	
	<form method="post" action="UploadImg" enctype="multipart/form-data">
	       书籍名称:<input type="text" name="bname" id="bb"/><span id="nameinfo"></span> <br>
	       <br>
	       书籍作者:<input type="text" name="author"/><br>
	       <br>    
                书籍价格:<input type="text" name="price"/><br>
                <br>
                数据库存:<input type="text" name="store"/><br>
                <br>
                书籍图片:<input type="file" name="img"/><br>
                <br>
		      <input type="submit" value="增加"/>
	</form>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有
</div>

</body>
</html>
