
<%@page import="com.entity.User"%>
<%@page import="com.dao.UserDao"%>
<%@page import="com.entity.Message"%>
<%@page import="com.dao.MessageDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script src="../js/jquery-1.11.1.js"></script>
<script >
		setInterval("location='bkmessage.jsp'" ,5000 );
</script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	
</div>
<div id="content" class="wrap">
	<div class="list orderList">
			<table>
				<tr class="title">
					<th>用户名</th>
					<th>消息</th>
				</tr>
				<%
				UserDao udao=new UserDao();
				List<User> ulist=udao.selectAll();
				for(User us:ulist){
					if(us.getIs_admin()==0){
				 %>
				 <tr>
				 <td><%=us.getUname() %></td>
				 <%
				 MessageDao dao=new MessageDao();
				 int n=dao.adGetCountByNState(us.getUname());
				  %>
				 <td><a href="bkmessagelist.jsp?name=<%=us.getUname() %>" id="mes" name="<%=us.getUname() %>">有条<font color="red"><%=n %></font>消息</a></td>
				 </tr>
				 <%
				}
				}
				 %>
			</table>
			<a href="backlistpro.jsp">返回首页</a>
	</div>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有
</div>

</body>
</html>
