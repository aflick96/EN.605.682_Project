<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/loan/loan-logger.css">
</head>

<div id="loanButtonContainer">
    <button 
        data-modal-target="loanModal"
        data-modal-size="small"
    >
        Add New Loan Item
    </button>
</div>

<div id="loanItemsContainer">
    <c:if test="${empty userLoans}">
        <p>No loan items found.</p>
    </c:if>

    <c:if test="${not empty userLoans}">
        <table border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Item Value</th>
                    <th>Loan Amount</th>
                    <th>Interest Rate</th>
                    <th>Start Date</th>
                    <th>Loan Term (Months)</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="loan" items="${userLoans}">
                    <tr>
                        <td>${loan.name}</td>
                        <td>$${loan.itemValue}</td>
                        <td>$${loan.loanAmount}</td>
                        <td>${loan.interestRate}%</td>
                        <td>${loan.startDate}</td>
                        <td>${loan.loanTermMonths}</td>
                        <td>
                            <button
                                class="add-payment-button"
                                data-modal-target="loanPaymentModal"
                                data-modal-size="small"
                                data-fetch-url="${pageContext.request.contextPath}/loans/create-payment?loanItemId=${loan.id}"
                            >
                                Add Payment
                            </button>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
