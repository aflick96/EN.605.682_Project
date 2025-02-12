<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/income/income-logger.css">
</head>

<div id="incomeButtonContainer">
    <button data-modal-target="incomeLogModal">Add New Income Log</button>
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
                <td>$${log.amount}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/income/delete/${log.incomeLogId}" method="post">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
