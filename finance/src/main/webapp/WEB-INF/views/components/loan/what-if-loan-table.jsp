<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/loan/what-if-loan-table.css">
</head>

<h2>What-If Loan Scenario</h2>
<p>Loan: ${loan.name}</p>
<p>Loan Amount: ${loan.loanAmount}</p>
<p>Interest Rate: ${loan.interestRate}</p>
<p>Loan Start Date: ${loan.startDate}</p>
<p>Loan Month Terms: ${loan.loanTermMonths}</p>

<input type="hidden" id="loanItemId" value="${loan.id}" />

<label>Monthly Payment ($)</label>
<input type="number" step="0.01" id="monthlyPaymentInput" value="${monthlyPayment}" style="width: 100px;" />

<label>Interest Rate (%)</label>
<input type="number" step="0.01" id="interestRateInput" value="${interestRate}" style="width: 100px;" />

<label>Terms (months)</label>
<input type="number" id="loanTermInput" value="${loanTerm}" style="width: 100px;" />

<div id="scenarioContentContainer">
    <div id="scenarioTableContainer">
        <table border="1" style="margin-top: 1rem;">
            <thead>
                <tr>
                    <th>Payment Date</th>
                    <th>Payment</th>
                    <th>Scenario Payment</th>
                    <th>Principal</th>
                    <th>Interest</th>
                    <th>Total Principal</th>
                    <th>Total Interest</th>
                    <th>Total Paid</th>
                    <th>Remaining Balance</th>
                    <th>End Balance</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="row" items="${tableRows}">
                    <tr>
                        <td>${row.monthStartDate}</td>
                        <td>$<fmt:formatNumber value="${row.realPayment}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
                        <td>$<fmt:formatNumber value="${row.scenarioPayment}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
                        <td>$<fmt:formatNumber value="${row.principalThisMonth}" type="number" minFractionDigits="2" maxFractionDigits="2" /></td>
                        <td>$<fmt:formatNumber value="${row.interestThisMonth}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
                        <td>$<fmt:formatNumber value="${row.totalPrincipal}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
                        <td>$<fmt:formatNumber value="${row.totalInterest}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
                        <td>$<fmt:formatNumber value="${row.totalPaid}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
                        <td>$<fmt:formatNumber value="${row.principalRemaining}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
                        <td>$<fmt:formatNumber value="${row.endBalance}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div id="graphicsContainer">
        <div id="loanBreakdownPieContainer" style="width: 200px; height: 200px; margin-top: 1rem;">
            <canvas id="loanBreakdownPie" width="200" height="200"></canvas>
        </div>
        <div id="loanBreakdownProgressChartContainer" style="width: 400px; height: 200px; margin-top: 1rem;">
            <canvas id="loanProgressChart" width="400" height="200"></canvas>
        </div>
    </div>
</div>

<script>
    window.loanBreakdown = {
        totalPrincipal: <c:out value="${fn:length(tableRows) > 0 ? tableRows[fn:length(tableRows) - 1].totalPrincipal : 0}" />,
        totalInterest: <c:out value="${fn:length(tableRows) > 0 ? tableRows[fn:length(tableRows) - 1].totalInterest : 0}" />
    };
</script>