<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/login.css">
</head>
<body class="page-container">
		
	<form action="${pageContext.request.contextPath}/auth/login" method="post">
        <h2>Login</h2>
        <label>Email:</label><br>
        <input type="email" name="email" required><br><br>

        <label>Password:</label><br>
        <input type="password" name="password" required><br><br>

        <button type="submit">Login</button>

        <p>Don't have an account? <a href="${pageContext.request.contextPath}/auth/register">Register</a></p>
	</form>
	
	

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
</body>
</html>