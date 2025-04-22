<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/page.css">
</head>
<body class="page-container">

    <jsp:include page="components/base/main-navigation-bar.jsp" />

    <c:if test="${not empty user}">
        <h1>Profile</h1>
        <p><strong>Name:</strong> ${user.firstName}</p>
        <p><strong>Last Name:</strong> ${user.lastName}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>State:</strong> ${user.state}</p>
        <p><strong>filingStatus:</strong> ${user.filingStatus}</p>
        <p><strong>dependents:</strong> ${user.dependents}</p>
    </c:if>
</body>
</html>
