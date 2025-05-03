<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/loan/loan-create.css">
</head>

<div class="modal-form-container">
    <h2>Create Loan Item</h2>
    <form:form class="modal-form" modelAttribute="loanItem" method="post" action="${pageContext.request.contextPath}/loans/add-item">
        <label for="name">Loan Name</label>
        <form:input path="name" id="name" required="true"/><br/>

        <label for="itemValue">Item Value</label>
        <form:input path="itemValue" id="itemValue" type="number" step="0.01" required="true"/><br/>

        <label for="loanAmount">Loan Amount</label>
        <form:input path="loanAmount" id="loanAmount" type="number" step="0.01" required="true"/><br/>

        <label for="interestRate">Interest Rate (%)</label>
        <form:input path="interestRate" id="interestRate" type="number" step="0.01" required="true"/><br/>

        <label for="startDate">Start Date</label>
        <form:input path="startDate" id="startDate" type="date" required="true"/><br/>

        <label for="loanTermMonths">Loan Term (Months)</label>
        <form:input path="loanTermMonths" id="loanTermMonths" type="number" required="true"/><br/>

        <br/>
        <button type="submit">Save</button>
    </form:form>
</div>
