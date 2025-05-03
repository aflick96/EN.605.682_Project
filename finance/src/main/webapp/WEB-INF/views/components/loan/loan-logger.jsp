<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/loan/loan-logger.css">
</head>

<div class="page-main-button-container">
    <button
        class="page-button page-button-big"
        data-modal-target="loanModal"
        data-modal-size="medium"
        data-fetch-url="${pageContext.request.contextPath}/loans/add-item"
    >
        Add New Loan Item
    </button>
</div>

<div class="page-table-container">
    <c:if test="${not empty loanItems}">
        <table class="page-table" border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Item Value</th>
                    <th>Loan Amount</th>
                    <th>Interest Rate</th>
                    <th>Start Date</th>
                    <th>Loan Term (Months)</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="loan" items="${loanItems}">
                    <tr>
                        <td>${loan.name}</td>
                        <td>$<fmt:formatNumber value="${loan.itemValue}" type="number" groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" /></td>
                        <td>$<fmt:formatNumber value="${loan.loanAmount}" type="number" groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" /></td>
                        <td>${loan.interestRate}%</td>
                        <td>${loan.startDate}</td>
                        <td>${loan.loanTermMonths}</td>
                        <td>
                            <button
                                class="page-button"
                                data-modal-target="loanModal"
                                data-modal-size="medium"
                                data-fetch-url="${pageContext.request.contextPath}/loans/add-payment?loanItemId=${loan.id}"
                            >
                                Add Payment
                            </button>
                            <button
                                class="page-button"
                                data-modal-target="loanModal"
                                data-modal-size="large"
                                data-fetch-url="${pageContext.request.contextPath}/loans/edit-loan-payments?loanItemId=${loan.id}"
                            >
                                View Payments
                            </button>
                            <button
                                class="page-button"
                                data-modal-target="loanModal"
                                data-modal-size="large"
                                data-fetch-url="${pageContext.request.contextPath}/loans/what-if-loan-table?loanItemId=${loan.id}&interestRate=${loan.interestRate}&loanTerm=${loan.loanTermMonths}"
                            >
                                What-If Scenario
                            </button>
                            <form action="${pageContext.request.contextPath}/loans/delete-loan-item?loanItemId=${loan.id}" method="post">
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
