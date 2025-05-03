<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/loan/loan-payment-create.css">
</head>

<div class="modal-form-container">
    <h2>Create Loan Payment</h2>
    
    <div>
        <button class="page-button" type="button" onclick="togglePaymentForm('single')">Single Payment</button>
        <button class="page-button" type="button" onclick="togglePaymentForm('recurring')">Recurring Payment</button>
    </div>
    
    <div class="modal-form-container" id="singlePaymentForm">
        <form:form class="modal-form" id="loanPaymentCreateForm" modelAttribute="loanPayment" method="post" action="${pageContext.request.contextPath}/loans/add-payment">
            <label for="paymentDate">Payment Date</label>
            <form:input path="paymentDate" id="paymentDate" type="date" required="true"/><br/>

            <label for="paymentAmount">Payment Amount</label>
            <form:input path="paymentAmount" id="paymentAmount" type="number" step="0.01" required="true"/><br/>

            <form:hidden path="loanItemId" />
            
            <br/>
            <button type="submit">Save</button> 
        </form:form>
    </div>
    <div class="modal-form-container" id="recurringPaymentForm" style="display:none;">
        <form:form class="modal-form" id="loanRecurringPaymentForm" modelAttribute="loanPayments" method="post" action="${pageContext.request.contextPath}/loans/add-payments">
            <label for="startDate">Start Date</label>
            <form:input path="startDate" id="startDate" type="month" required="true"/><br/>
        
            <label for="endDate">End Date</label>
            <form:input path="endDate" id="endDate" type="month" required="true"/><br/>
        
            <label for="paymentAmount">Payment Amount</label>
            <form:input path="paymentAmount" id="recurringPaymentAmount" type="number" step="0.01" required="true"/><br/>
        
            <label for="paymentDay">Payment Day</label>
            <form:select path="paymentDay" id="paymentDay" required="true" onchange="hideSpecificDay()">
                <form:option value="FIRST">First of Month</form:option>
                <form:option value="LAST">Last of Month</form:option>
                <form:option value="SPECIFIC">Specific Day</form:option>
            </form:select><br/>
            
            <div id="specificDayContainer" style="display:none;">
                <label for="specificDay">Specific Day of Month (1-28)</label>
                <form:input path="specificDay" id="specificDay" type="number" min="1" max="28" /><br/>
            </div>
            
        
            <form:hidden path="loanItemId" />
            <br/>
            <button type="submit">Create Payments</button>
        </form:form>
    </div>
</div>