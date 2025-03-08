<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/page.css">
</head>
<body class="page-container">
	<jsp:include page="components/base/main-navigation-bar.jsp" />
    <h2>Profile Section</h2>
    <p>Manage your profile here.</p>
    <a href="${pageContext.request.contextPath}/dashboard">Back to Dashboard</a>
</body>
</html>