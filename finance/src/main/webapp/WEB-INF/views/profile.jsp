<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/page.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/profile/profile.css">
</head>
<body class="page-container">

    <jsp:include page="components/base/main-navigation-bar.jsp" />

    <h2 class="page-header-h2">Profile</h2>

    <div class="profile-container">
        <c:if test="${not empty user}">
            <div class="profile-details">
                <div><span class="label">First Name:</span> <span class="value">${user.firstName}</span></div>
                <div><span class="label">Last Name:</span> <span class="value">${user.lastName}</span></div>
                <div><span class="label">Email:</span> <span class="value">${user.email}</span></div>
                <div><span class="label">State:</span> <span class="value">${user.state}</span></div>
                <div><span class="label">Filing Status:</span> <span class="value">${user.filingStatus}</span></div>
                <div><span class="label">Dependents:</span> <span class="value">${user.dependents}</span></div>
            </div>
        </c:if>
    </div>
</body>
</html>
