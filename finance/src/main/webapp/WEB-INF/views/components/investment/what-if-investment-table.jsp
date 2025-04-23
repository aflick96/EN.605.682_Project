<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>What-If Investment Scenario</h2>
<p>Investment: ${investment.name}</p>
<p>Annual Expected Return: ${investment.expectedAnnualReturn}%</p>

<input type="hidden" id="investmentLogId" value="${investment.id}" />

<label>Additional Weekly Contribution ($):</label>
<input type="number" step="0.01" min="0" id="weeklyContributionInput" value="${weeklyContribution}" style="width: 100px;" />

<label>Annual Expected Return Change</label>
<input type="number" step="0.01" min="0" id="annualReturnInput" value="${annualReturn}" style="width: 100px;" />

<div id="scenarioTableContainer">
    <table border="1" style="margin-top: 1rem;">
        <thead>
            <tr>
                <th>Week Start</th>
                <th>Contributions</th>
                <th>Scenario Contributions</th>
                <th>Total Contributions</th>
                <th>Growth</th>
                <th>Total Growth</th>
                <th>End Balance</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="row" items="${tableRows}">
                <tr>
                    <td>${row.weekStart}</td>
                    <td>$<fmt:formatNumber value="${row.realContribution}" type="number" minFractionDigits="2" maxFractionDigits="2" /></td>
                    <td>$<fmt:formatNumber value="${row.scenarioContribution}" type="number" minFractionDigits="2" maxFractionDigits="2" /></td>
                    <td>$<fmt:formatNumber value="${row.totalContributions}" type="number" minFractionDigits="2" maxFractionDigits="2" /></td>
                    <td>$<fmt:formatNumber value="${row.growthThisWeek}" type="number" minFractionDigits="2" maxFractionDigits="2" /></td>
                    <td>$<fmt:formatNumber value="${row.totalGrowth}" type="number" minFractionDigits="2" maxFractionDigits="2" /></td>
                    <td>$<fmt:formatNumber value="${row.endBalance}" type="number" minFractionDigits="2" maxFractionDigits="2" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
