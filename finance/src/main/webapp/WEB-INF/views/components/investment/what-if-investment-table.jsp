<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>What-If Investment Scenario</h2>
<p>Investment: ${investment.name}</p>
<p>Annual Expected Return: ${investment.expectedAnnualReturn}%</p>

<!-- Hidden inputs to store context -->
<input type="hidden" id="investmentLogId" value="${investment.id}" />

<!-- 1) Single input for Additional Weekly Contribution -->
<label>Additional Weekly Contribution ($):</label>
<input type="number" step="0.01" id="weeklyContributionInput"
       value="${weeklyContribution}" style="width: 100px;" />

<label>Annual Expected Return Change</label>
<input type="number" step="0.01" id="annualReturnInput"
       value="${annualReturn}" style="width: 100px;" />

<!-- The scenario table container -->
<div id="scenarioTableContainer">
    <table border="1" style="margin-top: 1rem;">
        <thead>
            <tr>
                <th>Week Start</th>
                <th>Real Contrib</th>
                <th>Scenario Contrib</th>
                <th>Total Contrib So Far</th>
                <th>Growth This Week</th>
                <th>Total Growth</th>
                <th>End Balance</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="row" items="${tableRows}">
                <tr>
                    <td>${row.weekStart}</td>
                    <td>$<c:out value="${row.realContribution}" /></td>
                    <td>$<c:out value="${row.scenarioContribution}" /></td>
                    <td>$<c:out value="${row.totalContributions}" /></td>
                    <td>$<c:out value="${row.growthThisWeek}" /></td>
                    <td>$<c:out value="${row.totalGrowth}" /></td>
                    <td>$<c:out value="${row.endBalance}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
