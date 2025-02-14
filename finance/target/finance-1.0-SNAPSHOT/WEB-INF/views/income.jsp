<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="edu.fin.models.income.IncomeLog" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Income Logs</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/page.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/modal.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/views/income.css">
    <script src="${pageContext.request.contextPath}/js/components/modal.js"></script>
</head>
<body class="page-container">
	<jsp:include page="components/main-navigation-bar.jsp" />
    
    <div class="page-content">
        <h2>Income Logs</h2>
        <jsp:include page="components/income/income-logger.jsp" />
    </div>

    <div data-modal-id="incomeLogModal" class="modal">
        <div class="modal-content">
            <span class="modal-close">&times;</span>
            <jsp:include page="components/income/income-log-create.jsp" />
        </div>
    </div>
</body>
</html>