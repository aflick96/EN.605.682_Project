<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="dashboard-graphic-horizontal-scrollable-container">
    <div style="min-width: 900px; width: max-content;">
        <canvas id="monthlyCashFlowChart"></canvas>
    </div>
</div>

<script>
    window.monthlyCashFlowData = {
        labels: [
        <c:forEach var="row" items="${monthlyCashFlow.cashFlowData}" varStatus="status">
            "${row.month}"<c:if test="${!status.last}">,</c:if>
        </c:forEach>
        ],
        income: [
        <c:forEach var="row" items="${monthlyCashFlow.cashFlowData}" varStatus="status">
            ${row.income}<c:if test="${!status.last}">,</c:if>
        </c:forEach>
        ],
        expenses: [
        <c:forEach var="row" items="${monthlyCashFlow.cashFlowData}" varStatus="status">
            ${row.expenses}<c:if test="${!status.last}">,</c:if>
        </c:forEach>
        ],
        netCashFlow: [
            <c:forEach var="row" items="${monthlyCashFlow.cashFlowData}" varStatus="status">
                ${row.netCashFlow}<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ]
    };
</script>