<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Loan Items</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/page.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/modal.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/model-form.css">
    <script src="${pageContext.request.contextPath}/js/components/modal.js"></script>
    <script src="${pageContext.request.contextPath}/js/components/loan/loanScenario.js"></script>
    <script src="${pageContext.request.contextPath}/js/components/loan/loan.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body class="page-container">
    <jsp:include page="components/base/main-navigation-bar.jsp" />

    <div class="page-content">
        <h2>Loan Items</h2>
        <jsp:include page="components/loan/loan-logger.jsp" />
    </div>

    <div data-modal-id="loanModal" class="modal">
        <div class="modal-content">
            <span class="modal-close">&times;</span>
            <div class="modal-content-body">
                <!-- component will be inserted -->
            </div>
        </div>
    </div>
    
</body>
</html>
