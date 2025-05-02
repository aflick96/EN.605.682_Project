<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<canvas id="expenseByCategoryChart"></canvas>
<script>
    window.expenseByCategory = {
      labels: [
        <c:forEach var="label" items="${expenseByCategory.labels}" varStatus="loop">
          "${label}"<c:if test="${!loop.last}">,</c:if>
        </c:forEach>
      ],
      values: [
        <c:forEach var="val" items="${expenseByCategory.values}" varStatus="loop">
          ${val}<c:if test="${!loop.last}">,</c:if>
        </c:forEach>
      ]
    };
</script>
  