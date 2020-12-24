<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../js/jquery-1.11.1.js"></script>
<script type="text/javascript">
	$(function(){
		//判断用户名
		$("#uname").change(function(){
			var un = $(this).val();
			$.ajax({
				url:"../RegisterCheckAction",
				type:"post",
				data:{"uname":un,"method":"uname"},
				success:function(res){
					$("#unameinfo").html(res);			
				}
			});
		});
	
		//判断密码是否符合格式
		$("#pwd").change(function(){
			var pw = $(this).val();
			$.ajax({
				url:"../RegisterCheckAction",
				type:"post",
				data:{"pwd":pw,"method":"pwd"},
				success:function(res1){
					$("#pwdinfo").html(res1);
				}
			});
		});
		
		//判断再次输入的密码
		//判断密码是否符合格式
		$("#repassword").change(function(){
			var rpw = $(this).val();
			var pw = $("#pwd").val();
			$.ajax({
				url:"../RegisterCheckAction",
				type:"post",
				data:{"repassword":rpw,"method":"repassword","pwd":pw},
				success:function(res2){
					$("#repasswordinfo").html(res2);
				}
			});
		});
		
		//判断邮箱名
		$("#email").change(function(){
			var email = $(this).val();
			$.ajax({
				url:"../RegisterCheckAction",
				type:"post",
				data:{"email":email,"method":"email"},
				success:function(res3){
					$("#emailinfo").html(res3);
				}
			});
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
<div id="register">
	<div class="title">
		<h2>欢迎注册网上书城</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
			<li class="current">1.填写注册信息</li>
			<li class="unpass">2.注册成功</li>
		</ul>
	</div>
	<form action="../AdminRegisterAction" method="post" name="form5">
		<dl>
			<dt>用 户 名：</dt>
			<dd><input class="input-text" type="text" name="uname" id="uname"/><span id="unameinfo"></span></dd>
			<dt>密　　码：</dt>
			<dd><input class="input-text" type="password" name="pwd" id="pwd"/><span id="pwdinfo"></span></dd>
			<dt>确认密码：</dt>
			<dd><input class="input-text" type="password" name="repassword" id="repassword"/><span id="repasswordinfo"></span></dd>
			<dt>Email地址：</dt>
			<dd><input class="input-text" type="text" name="email" id="email"/><span id="emailinfo"></span></dd>
			<dt></dt>
			<dd class="button"><input class="input-reg" type="submit" name="register" value="" /></dd>
		</dl>
	</form>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有
</div>

</body>
</html>
