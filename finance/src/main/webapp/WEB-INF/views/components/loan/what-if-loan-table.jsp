<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/loan/what-if-loan-table.css">
</head>

<h2>What-If Loan Scenario</h2>

<div id="headerContainer">
    <div id="loanItemDetailsContainer">
        <span>Loan: ${loan.name}</span>
        <span>Loan Amount: ${loan.loanAmount}</span>
        <span>Interest Rate: ${loan.interestRate}</span>
        <span>Loan Start Date: ${loan.startDate}</span>
        <span>Loan Month Terms: ${loan.loanTermMonths}</span>
    </div>

    <div id="loanItemInputsContainer">
        <input type="hidden" id="loanItemId" value="${loan.id}" />
        
        <div class="input-group">            
            <label>Additional Monthly Payment ($)</label>
            <input type="number" step="0.01" min="0" id="monthlyPaymentInput" value="<fmt:formatNumber value='${monthlyPayment}' type='number' minFractionDigits='2' maxFractionDigits='2' groupingUsed="false"/>" />
        </div>

        <div class="input-group">                        
            <label>Interest Rate (%)</label>
            <input type="number" step="0.01" min="0" id="interestRateInput" value="${interestRate}" />
        </div>

        <div class="input-group">                        
            <label>Terms (months)</label>
            <input type="number" disabled="true" min="0" max="${loan.loanTermMonths}" id="loanTermInput" value="${loanTerm}" />
        </div>
    </div>
</div>

<div id="graphicsContainer">
    <div id="loanBreakdownProgressChartContainer">
        <canvas id="loanProgressChart"></canvas>
    </div>
    <div id="loanBreakdownPieContainer">
        <canvas id="loanBreakdownPie"></canvas>
    </div>
    <div class="loan-summary" id="loanSummary">
        <table>
            <tr>
                <th>Total Principal</th>
                <td id="summaryTotalPrincipal">$0.00</td>
            </tr>
            <tr>
                <th>Total Interest</th>
                <td id="summaryTotalInterest">$0.00</td>
            </tr>
            <tr>
                <th>Total Paid</th>
                <td id="summaryTotalPaid">$0.00</td>
            </tr>
            <tr>
                <th>Ending Balance</th>
                <td id="summaryEndBalance">$0.00</td>
            </tr>
            <tr>
                <th>Last Payment Date</th>
                <td id="summaryLastPaymentDate"></td>
            </tr>
        </table>
    </div>
</div>

<div id="scenarioContentContainer">
    <div id="scenarioTableContainer" data-loan-term="${loanTerm}">
        <table border="1" style="margin-top: 1rem;">
            <thead>
                <tr>
                    <th>Payment Date</th>
                    <th>Payment</th>
                    <th>Additional Payment</th>
                    <th>Total Payment</th>
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
                        <td>$<fmt:formatNumber value="${row.paymentThisMonth}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
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
</div>

<script>
    window.loanBreakdown = {
        totalPrincipal: <c:out value="${fn:length(tableRows) > 0 ? tableRows[fn:length(tableRows) - 1].totalPrincipal : 0}" />,
        totalInterest: <c:out value="${fn:length(tableRows) > 0 ? tableRows[fn:length(tableRows) - 1].totalInterest : 0}" />
    };
</script>