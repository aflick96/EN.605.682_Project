<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="components/main-navigation-bar.jsp" />
    <h2>Expenses Section</h2>
    <p>Manage your expense logs here.</p>
    <a href="${pageContext.request.contextPath}/dashboard">Back to Dashboard</a>
</body>
</html>