<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Income Logs</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/page.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/modal.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/base/modal-form.css">
    <script src="${pageContext.request.contextPath}/js/components/modal.js"></script>
</head>
<body class="page-container">
	<jsp:include page="components/base/main-navigation-bar.jsp" />

    <div class="page-content">
        <h2 class="page-header-h2">Income Logs</h2>
        <jsp:include page="components/income/income-logger.jsp" />
    </div>

    <div data-modal-id="incomeLogModal" class="modal">
        <div class="modal-content">
            <span class="modal-close">&times;</span>
            <div class="modal-content-body">

            </div>
        </div>
    </div>
</body>
</html>