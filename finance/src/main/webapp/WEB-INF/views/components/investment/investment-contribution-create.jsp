<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/investment/investment-contribution.css">
</head>

<div id="investmentContributionContainer">
    <h2>Investment Contribution</h2>
    <form:form id="investmentContributionForm" modelAttribute="investmentContribution" method="post" action="${pageContext.request.contextPath}/investment/add-investment-contribution">
        <label for="contribution-date">Contribution Date</label>
        <form:input path="contributionDate" id="contribution-date" type="date" required="true"/><br/>

        <label for="contribution-amount">Contribution Amount</label>
        <form:input path="contributionAmount" id="contribution-amount" type="number" step="0.01" required="true"/><br/>

        <form:hidden path="investmentLogId" />
        <br/>
        <button type="submit">Save</button>
    </form:form>       
</div>