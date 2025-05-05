<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/page.css">
</head>
<body class="page-container">
	<div id="loginRegisterFormContainer">
        <form action="${pageContext.request.contextPath}/auth/login" method="post">
            <h2 class="page-header-h2">Login</h2>
            <label>Email:</label><br>
            <input type="email" name="email" required><br>
    
            <label>Password:</label><br>
            <input type="password" name="password" required>

            <br><br>
            <button type="submit">Login</button>
            <p>Don't have an account? <a href="${pageContext.request.contextPath}/auth/register">Register</a></p>
        </form>    
    </div>
	
    <c:if test="${not empty error}">
        <div class="client-message-overlay" id="clientMessageOverlay">
            <p>${error}</p>
        </div>
    </c:if>

    <script>
        window.onload = function () {
            const errorMsg = document.getElementById("clientMessageOverlay");
            if (errorMsg && errorMsg.innerText.trim() === "") {
                errorMsg.style.display = "none";
            } else if (errorMsg) {
                setTimeout(() => {
                    setTimeout(() => errorMsg.style.display = "none", 500);
                }, 3000);
            }
        };
    </script>
</body>
</html>