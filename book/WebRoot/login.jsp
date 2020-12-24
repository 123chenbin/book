<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
	$(function(){
		//检查账户
		$("#uname").change(function(){
			var un = $(this).val();
			$.ajax({
				url:"CheckAction",
				type:"post",
				data:"method=user&uname="+un,
				success:function(res){
					$("#unameinfo").html(res)
				}
			});
		});
	
		//检查密码
		$("#upwd").change(function(){
			var up = $(this).val();
			var un = $("#uname").val();
			
			$.ajax({
				url:"CheckAction",
				type:"post",
				data:{"method":"pwd","uname":un,"upwd":up},
				success:function(res){
					$("#upwdinfo").html(res);
				}
			})
		});
		$("#yzm").click(function(){
			$(this).attr("src","imgcode?"+Math.random());
			
		
		});
	});

</script>

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
<div id="login">
	<h2>用户登陆</h2>
	<form action="UserAction" method="post" name="form1">
			<input type="hidden" name="method" value="login" />
		<dl>
			<dt>用户名：</dt>
			<dd><input class="input-text" id="uname" type="text" name="uname" /><span id="unameinfo"></span></dd>
			<dt>密　码：</dt>
			<dd><input class="input-text" id="upwd" type="password" name="upwd" /><span id="upwdinfo"></span></dd>
			<dt>验证码：</dt>
			<dd><input class="input-text" type="text" name="valcode"/><img src="imgcode" alt="" style="vertical-align: middle;" id="yzm"/></dd>
			<dt></dt>
			<dd class="button"><input class="input-btn" type="submit" name="submit" value="" /><input class="input-reg" type="button" name="register" value="" onclick="window.location='register.jsp';" /></dd>
		</dl>
	</form>
</div>

</body>
</html>
