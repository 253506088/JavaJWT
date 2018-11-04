<%@ page language="java" import="java.util.*,java.text.*" 
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
	<head>
		<base href="<%=basePath%>">
		<title>个人主页</title>
		<meta charset="utf-8">
		<script type="text/javascript" src="<%=basePath %>static/js/jquery-3.2.1.js"></script>
		<script src="<%=basePath %>static/js/jquery.cookie.js"></script>
	</head>
  
	<body>
		<h1 id="msg"></h1>
	</body>
	
	<script type="text/javascript">
		$(document).ready(function(){
			//取出Cookie里的JSON字符串并转换为JSON对象
			var json = $.cookie("userMsg"); 
			var user = JSON.parse(json);
			console.log(user);
			$("#msg").text("欢迎回来"+user.nikeName);
		});
	</script>
</html>
