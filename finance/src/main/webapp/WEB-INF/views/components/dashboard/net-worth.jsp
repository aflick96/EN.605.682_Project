<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
</head>

<div>
    <canvas id="netWorthChart" width="800" height="400"></canvas>
</div>

<script>
    window.netWorthData = {
      labels: [
        <c:forEach var="nw" items="${netWorthList}" varStatus="status">
          "${nw.date}"<c:if test="${!status.last}">,</c:if>
        </c:forEach>
      ],
      values: [
        <c:forEach var="nw" items="${netWorthList}" varStatus="status">
          ${nw.netWorth}<c:if test="${!status.last}">,</c:if>
        </c:forEach>
      ]
    };
</script>

<script>
    window.netWorthBreakdown = {
        labels: [
        <c:forEach var="entry" items="${breakdown.netWorth}" varStatus="status">
            "${entry.key}"<c:if test="${!status.last}">,</c:if>
        </c:forEach>
        ],
        netWorth: [
        <c:forEach var="entry" items="${breakdown.netWorth}" varStatus="status">
            ${entry.value}<c:if test="${!status.last}">,</c:if>
        </c:forEach>
        ],
        income: [
        <c:forEach var="entry" items="${breakdown.income}" varStatus="status">
            ${entry.value}<c:if test="${!status.last}">,</c:if>
        </c:forEach>
        ],
        expenses: [
        <c:forEach var="entry" items="${breakdown.expenses}" varStatus="status">
            ${entry.value}<c:if test="${!status.last}">,</c:if>
        </c:forEach>
        ],
        investments: [
        <c:forEach var="entry" items="${breakdown.investments}" varStatus="status">
            ${entry.value}<c:if test="${!status.last}">,</c:if>
        </c:forEach>
        ],
        loans: [
        <c:forEach var="entry" items="${breakdown.loans}" varStatus="status">
            ${entry.value}<c:if test="${!status.last}">,</c:if>
        </c:forEach>
        ]
    };
</script>
  
  