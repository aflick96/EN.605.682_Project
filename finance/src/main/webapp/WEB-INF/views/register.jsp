<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Register</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/page.css">
</head>
<body class="page-container">
    <h2>Register</h2>

    <form action="${pageContext.request.contextPath}/auth/register" method="post">
        <label>First Name:</label><br>
        <input type="text" name="firstName" required><br><br>

        <label>Last Name:</label><br>
        <input type="text" name="lastName" required><br><br>

        <label>Email:</label><br>
        <input type="email" name="email" required><br><br>

        <label>Password:</label><br>
        <input type="password" name="password" required><br><br>

        <label>State of Residence:</label><br>
        <input type="text" name="state" required><br><br>

        <button type="submit">Register</button>
    </form>

    <p>Already have an account? <a href="${pageContext.request.contextPath}/auth/login">Login</a></p>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
</body>
</html>