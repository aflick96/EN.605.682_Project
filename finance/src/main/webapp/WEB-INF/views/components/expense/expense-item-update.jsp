<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/expense/expense-log-create.css">
</head>

<div class="modal-form-container" id="expenseItemUpdateContainer">
    <h2>Update Expense Item</h2>
    <form:form class="modal-form" id="expenseItemUpdateForm" modelAttribute="item" method="post" action="${pageContext.request.contextPath}/expenses/update-item">
        <label for="name">Expense Name</label>
        <form:input path="name" id="name" required="true"/><br/>

        <label for="category">Category</label>
        <form:select path="category" id="category" required="true">
            <form:option value="RENT">Rent</form:option>
            <form:option value="TRANSPORTATION">Transportation</form:option>
            <form:option value="FOOD">Food</form:option>
            <form:option value="UTILITIES">Utilities</form:option>
            <form:option value="INSURANCE">Insurance</form:option>
            <form:option value="HEALTHCARE">Healthcare</form:option>
            <form:option value="PERSONAL_CARE">Personal</form:option>
            <form:option value="ENTERTAINMENT">Entertainment</form:option>
            <form:option value="CHILDCARE">Child Care</form:option>
            <form:option value="GIFTS_DONATIONS">Gifts/Donations</form:option>
            <form:option value="OTHER">Other</form:option>
        </form:select><br/>

        <label for="frequency">Frequency</label>
        <form:select path="frequency" id="frequency" required="true">
            <form:option value="WEEKLY">Weekly</form:option>
            <form:option value="BIWEEKLY">Bi-Weekly</form:option>
            <form:option value="MONTHLY">Monthly</form:option>
            <form:option value="YEARLY">Yearly</form:option>
            <form:option value="ONE_TIME">Once</form:option>
        </form:select><br/>

        <label for="startDate">Start Date</label>
        <form:input path="startDate" id="startDate" type="date" required="true"/><br/>

        <label for="endDate">End Date</label>
        <form:input path="endDate" id="endDate" type="date" required="true"/><br/>

        <label for="amount">Amount</label>
        <form:input path="amount" id="amount" type="number" required="true"/><br/>

        <form:hidden path="expenseLogId" />
        <form:hidden path="id" />
        <br/>
        <button type="submit">Save</button>
    </form:form> 
</div>