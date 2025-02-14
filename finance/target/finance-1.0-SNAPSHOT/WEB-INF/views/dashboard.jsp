<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="edu.fin.models.user.User" %>
<%
	//forward user to login if session has expired
	User user = (User) session.getAttribute("user");
	if (user == null) {
		response.sendRedirect(request.getContextPath() + "/auth/login");
		return;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Dashboard</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/page.css">
</head>
<body class="page-container">
	<jsp:include page="components/main-navigation-bar.jsp" />
	<h2>Welcome, <%= user.getFirstName() %>!</h2>
	<p>This is your personal finance dashboard.</p>
	<a href="${pageContext.request.contextPath}/auth/logout">Logout</a>
</body>
</html>