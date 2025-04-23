<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Dashboard</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/page.css">
</head>
<body class="page-container">
	<jsp:include page="components/base/main-navigation-bar.jsp" />
	<h2>Welcome, ${user.firstName}!</h2>
	<p>This is your personal finance dashboard.</p>
	<a href="${pageContext.request.contextPath}/auth/logout">Logout</a>
</body>
</html>