<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/investment/investment-create.css">
</head>

<div id="investmentCreateContainer">
    <h2>Create Investment Item</h2>
    <form:form id="investmentCreateForm" modelAttribute="investmentLog" method="post" action="${pageContext.request.contextPath}/investment/add-investment-log">
        <label for="name">Investment Name</label>
        <form:input path="name" id="name" required="true"/><br/>

        <label for="start-date">Start Date</label>
        <form:input path="startDate" id="start-date" type="date" required="true"/><br/>

        <label for="end-date">End Date</label>
        <form:input path="endDate" id="end-date" type="date" required="true"/><br/>

        <label for="expected-return">Expected Annual Return</label>
        <form:input path="expectedAnnualReturn" id="expected-return" type="number" step="0.01" required="true"/><br/>

        <br/>
        <button type="submit">Save</button>
    </form:form>
</div>