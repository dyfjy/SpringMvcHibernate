<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String context = request.getContextPath();
	pageContext.setAttribute("context_", context);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>alexgaoyh My title</title>
</head>	
<body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.4.1.js"></script>
	<h2>Hello World!</h2>
	<h2>${test}</h2>
	<h2>${sysmanUser.name}</h2>
</body>
</html>
