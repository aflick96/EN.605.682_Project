<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/loan/loan-payment-create.css">
</head>

<div id="loanPaymentCreateContainer">
    <h2>Create Loan Payment</h2>
    <form:form id="loanPaymentCreateForm" modelAttribute="loanPayment" method="post" action="${pageContext.request.contextPath}/loans/add-payment">
        <label for="paymentDate">Payment Date</label>
        <form:input path="paymentDate" id="paymentDate" type="date" required="true"/><br/>

        <label for="paymentAmount">Payment Amount</label>
        <form:input path="paymentAmount" id="paymentAmount" type="number" step="0.01" required="true"/><br/>

        <form:hidden path="loanItemId" />
        
        <br/>
        <button type="submit">Save</button> 
    </form:form> 
</div>