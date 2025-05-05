<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Register</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/page.css">
</head>
<body class="page-container">
    <div id="loginRegisterFormContainer">
        <form action="${pageContext.request.contextPath}/auth/register" method="post">
            <h2 class="page-header-h2">Register</h2>
            <label>First Name:</label>
            <input type="text" name="firstName" required><br>

            <label>Last Name:</label>
            <input type="text" name="lastName" required><br>

            <label>Email:</label>
            <input type="email" name="email" required><br>

            <label>Password:</label>
            <input type="password" name="password" required><br>

            <label>State of Residence:</label>
            <input type="text" name="state" required><br>

            <label>Tax Filling Status:</label>
            <select name="filingStatus">
                <option value="single">Single</option>
                <option value="married">Married</option>
            </select><br>

            <label>Dependents</label>
            <input type="number" name="dependents" required>
            
            <br><br>
            <button type="submit">Register</button>
        
            <p>Already have an account? <a href="${pageContext.request.contextPath}/auth/login">Login</a></p>
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