<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="loan-completion-container">
    <c:forEach var="loan" items="${loanCompletion.loanCompletionData}">
        <div class="loan-bar-wrapper">
        <div class="loan-label">
            ${loan.loanName} (<fmt:formatNumber value="${loan.percentageCompleted}" type="number" minFractionDigits="2" maxFractionDigits="2"/>%)
        </div>
        <div class="loan-bar">
            <div class="loan-bar-fill" style="width: ${loan.percentageCompleted}%"></div>
        </div>
        </div>
    </c:forEach>
</div>