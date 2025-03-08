<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Expense Logs</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/page.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/modal.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/model-form.css">
    <script src="${pageContext.request.contextPath}/js/components/modal.js"></script>
</head>
<body class="page-container">
	<jsp:include page="components/base/main-navigation-bar.jsp" />
    
    <div class="page-content">
        <h2>Expense Logs</h2>
        <jsp:include page="components/expense/expense-logger.jsp" />
    </div>

    <div data-modal-id="expenseLogModal" class="modal">
        <div class="modal-content">
            <span class="modal-close">&times;</span>
            <jsp:include page="components/expense/expense-log-create.jsp" />
        </div>
    </div>
</body>
</html>