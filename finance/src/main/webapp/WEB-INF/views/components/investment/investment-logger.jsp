<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/investment/investment-logger.css">
</head>

<!-- add new investment button -->
<div id="investmentButtonContainer">
    <button 
        data-modal-target="investmentModal"
        data-modal-size="small"
        data-fetch-url="${pageContext.request.contextPath}/investment/add-investment-log"
    >
        Add New Investment Item
    </button>
</div>

<div id="investmentLogContainer">
    <c:if test="${empty investmentLogs}">
        <p>No investment logs found.</p>
    </c:if>
    <c:if test="${not empty investmentLogs}">
        <table border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Expected Annual Return</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="investment" items="${investmentLogs}">
                    <tr>
                        <td>${investment.name}</td>
                        <td>${investment.startDate}</td>
                        <td>${investment.endDate}</td>
                        <td>${investment.expectedAnnualReturn}</td>
                        <td>
                            <button
                                class="add-contribution-button"
                                data-modal-target="investmentModal"
                                data-modal-size="small"
                                data-fetch-url="${pageContext.request.contextPath}/investment/add-investment-contribution?investmentLogId=${investment.id}"
                            >
                                Add Contribution
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>