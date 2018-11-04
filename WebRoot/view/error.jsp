<%@ page language="java" import="java.util.*,java.text.*" 
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
	<head>
		<base href="<%=basePath%>">
		<title>登录信息已过期</title>
		<meta charset="utf-8">
		<script type="text/javascript" src="<%=basePath %>static/js/jquery-3.2.1.js"></script>
	</head>
  
	<body>
		<h1>登录信息已过期,即将跳转到登录页</h1>
		<script type="text/javascript">
			setTimeout(function(){ window.location.href = "<%=basePath%>loginAndRegister"}, 3000);// 3秒后跳转到登录页 
		</script>
	</body>
</html>
