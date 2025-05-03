<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/investment/investment-logger.css">
</head>

<div class="page-main-button-container">
    <button
        class="page-button page-button-big"
        data-modal-target="investmentModal"
        data-modal-size="medium"
        data-fetch-url="${pageContext.request.contextPath}/investment/add-investment-log"
    >
        Add New Investment Item
    </button>
</div>

<div class="page-table-container">
    <c:if test="${not empty investmentLogs}">
        <table class="page-table" border="1">
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
                        <td>${investment.expectedAnnualReturn}%</td>
                        <td>
                            <button
                                class="page-button"
                                data-modal-target="investmentModal"
                                data-modal-size="medium"
                                data-fetch-url="${pageContext.request.contextPath}/investment/add-investment-contribution?investmentLogId=${investment.id}"
                            >
                                Add Contribution
                            </button>
                            <button
                                class="page-button"
                                data-modal-target="investmentModal"
                                data-modal-size="large"
                                data-fetch-url="${pageContext.request.contextPath}/investment/edit-investment-contributions?investmentLogId=${investment.id}"
                            >
                                View Contributions
                            </button>
                            <button 
                                class="page-button"
                                data-modal-target="investmentModal"
                                data-modal-size="large"
                                data-fetch-url="${pageContext.request.contextPath}/investment/what-if-investment-table?investmentLogId=${investment.id}&annualReturn=${investment.expectedAnnualReturn}"
                            >
                                What If Investment Table
                            </button>
                            <form action="${pageContext.request.contextPath}/investment/delete-investment-log" method="post">
                                <input type="hidden" name="investmentLogId" value="${investment.id}" />
                                <button
                                    class="page-button" 
                                    type="submit"
                                >
                                Delete
                            </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>