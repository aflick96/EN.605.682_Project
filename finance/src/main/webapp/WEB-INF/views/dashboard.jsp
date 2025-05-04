<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Dashboard</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/page.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/dashboard/dashboard.css">
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/luxon@3.4.0/build/global/luxon.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-luxon@1.3.1"></script>
</head>
<body class="page-container">
	<jsp:include page="components/base/main-navigation-bar.jsp" />

	<h2 class="page-header-h2">Welcome, ${user.firstName}!</h2>

	<div class="dashboard-component-container">
		<div class="dashboard-graphic-component-container">
			<jsp:include page="components/dashboard/net-worth.jsp" />
		</div>
	
		<div class="dashboard-graphic-small-component-container">
			<jsp:include page="components/dashboard/expense-by-category.jsp" />
		</div>

		<div class="dashboard-graphic-component-container">
			<jsp:include page="components/dashboard/monthly-cash-flow.jsp" />
		</div>

		<div class="dashboard-graphic-small-component-container">
			<jsp:include page="components/dashboard/loan-completion.jsp" />
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/js/components/dashboard/net-worth.js"></script>
	<script src="${pageContext.request.contextPath}/js/components/dashboard/expense-by-category.js"></script>
	<script src="${pageContext.request.contextPath}/js/components/dashboard/monthly-cash-flow.js"></script>
</body>
</html>