<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
  <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/components/investment/what-if-investment-table.css">
</head>

<div id="whatIfScenarioContainer" data-context="${pageContext.request.contextPath}">
  <h2 class="section-title">What-If Investment Scenario</h2>

  <div class="investment-header">
    <div class="investment-details">
      <div><strong>Investment:</strong> ${investment.name}</div>
      <div><strong>Annual Expected Return:</strong> ${investment.expectedAnnualReturn}%</div>
    </div>

    <div class="investment-inputs">
      <input type="hidden" id="investmentLogId" value="${investment.id}" />

      <div class="input-group">
        <label for="weeklyContributionInput">Weekly Contribution ($):</label>
        <input type="number" step="0.01" min="0" id="weeklyContributionInput" value="${weeklyContribution}" />
      </div>

      <div class="input-group">
        <label for="annualReturnInput">Annual Return (%):</label>
        <input type="number" step="0.01" min="0" id="annualReturnInput" value="${annualReturn}" />
      </div>
    </div>
  </div>

  <div class="investment-graphics">
    <div class="chart-wrapper">
      <canvas id="investmentProgressChart"></canvas>
    </div>

    <div class="investment-summary" id="investmentSummary">
        <table>
            <tr>
                <th>End Balance</th>
                <td id="summaryEndBalance">$0.00</td>
            </tr>
            <tr>
                <th>Total Contributions</th>
                <td id="summaryTotalContributions">$0.00</td>
            </tr>
            <tr>
                <th>Total Growth</th>
                <td id="summaryTotalGrowth">$0.00</td>
            </tr>
        </table>
    </div>
  </div>

  <div id="scenarioTableContainer">
    <table class="investment-table">
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
</div>

<script>
  document.addEventListener("DOMContentLoaded", function () {
    const container = document.getElementById("whatIfScenarioContainer");
    if (container) {
      attachWhatIfInvestment(container);
    }
  });
</script>
