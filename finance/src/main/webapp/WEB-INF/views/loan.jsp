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
</head>
<body class="page-container">
    <jsp:include page="components/base/main-navigation-bar.jsp" />

    <!-- Display existing loan items -->
    <div class="page-content">
        <h2>Loan Items</h2>
        <jsp:include page="components/loan/loan-logger.jsp" />
    </div>

    <!-- Display the loan creation form for user to log a new loan item -->
    <div data-modal-id="loanModal" class="modal">
        <div class="modal-content">
            <span class="modal-close">&times;</span>
            <jsp:include page="components/loan/loan-create.jsp" />
        </div>
    </div>

    <!-- Display the payment form for user to log a new payment on an existing item -->
    <div data-modal-id="loanPaymentModal" class="modal">
        <div class="modal-content">
            <span class="modal-close">&times;</span>
            <div class="modal-content-body">
                <!-- component will be inserted -->
            </div>
        </div>
    </div>
</body>
</html>
