<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/income/income-logger.css">
</head>

<div id="incomeButtonContainer">
    <button 
        data-modal-target="incomeLogModal"
        data-modal-size="small"
    >
        Add New Income Log
    </button>
</div>

<div id="incomeLogsContainer">
    <table border="1">
        <tr>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Pay Frequency</th>
            <th>Amount</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="log" items="${incomeLogs}">
            <tr>
                <td>${log.startDate}</td>
                <td>${log.endDate}</td>
                <td>
                    <c:choose>
                        <c:when test="${log.annualPayFrequency == 'WEEKLY'}">Weekly</c:when>
                        <c:when test="${log.annualPayFrequency == 'BIWEEKLY'}">Bi-Weekly</c:when>
                        <c:when test="${log.annualPayFrequency == 'SEMIMONTHLY'}">Semi-monthly</c:when>
                        <c:when test="${log.annualPayFrequency == 'MONTHLY'}">Monthly</c:when>
                    </c:choose>
                </td>
                <td>$<fmt:formatNumber value="${log.amount}" type="number" groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" /></td>
                <td>
                    <button
                        class="view-details-button"
                        data-modal-target="incomeLogDetailModal"
                        data-modal-size="large"
                        data-fetch-url="${pageContext.request.contextPath}/income/${log.incomeLogId}"
                    >
                        Details
                    </button>
                    <form action="${pageContext.request.contextPath}/income/delete/${log.incomeLogId}" method="post">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div id="incomeLogDetailModal" class="modal" data-modal-id="incomeLogDetailModal">
    <div class="modal-content">
        <span class="modal-close">&times;</span>
        <div class="modal-content-body"></div>
    </div>
</div>