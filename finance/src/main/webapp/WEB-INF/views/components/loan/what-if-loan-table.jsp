<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

<div id="scenarioTableContainer">
    <table border="1" style="margin-top: 1rem;">
        <thead>
            <tr>
                <th>Payment Date</th>
                <th>Real Payment Amount</th>
                <th>Scenario Payment Amount</th>
                <th>Principal This Month</th>
                <th>Interest This Month</th>
                <th>Total Principal Paid</th>
                <th>Total Interest Paid</th>
                <th>Total Paid</th>
                <th>Remaining Balance</th>
                <th>End Balance</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="row" items="${tableRows}">
                <tr>
                    <td>${row.monthStartDate}</td>
                    <td>$<c:out value="${row.realPayment}" /></td>
                    <td>$<c:out value="${row.scenarioPayment}" /></td>
                    <td>$<c:out value="${row.principalThisMonth}" /></td>
                    <td>$<c:out value="${row.interestThisMonth}" /></td>
                    <td>$<c:out value="${row.totalPrincipal}" /></td>
                    <td>$<c:out value="${row.totalInterest}" /></td>
                    <td>$<c:out value="${row.totalPaid}" /></td>
                    <td>$<c:out value="${row.principalRemaining}" /></td>
                    <td>$<c:out value="${row.endBalance}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>