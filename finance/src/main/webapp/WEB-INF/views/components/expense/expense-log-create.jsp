<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/expense/expense-log-create.css">
</head>

<h2 class="modal-form-header">Create Expense Log</h2>
<form id="expenseLogItemCreateForm" action="${pageContext.request.contextPath}/expenses/add-item" method="post">
    <label for="name">Expense Name</label>
    <input type="text" id="name" name="name" required />
    <label for="category">Category</label>
    <select id="category" name="category" required>
        <option value="RENT">Rent</option>
        <option value="UTILITIES">Transportation</option>
        <option value="TRANSPORTATION">Food</option>
        <option value="FOOD">Utilities</option>
        <option value="INSURANCE">Insurance</option>
        <option value="HEALTHCARE">Healthcare</option>
        <option value="ENTERTAINMENT">Savings</option>
        <option value="PERSONAL_CARE">Personal</option>
        <option value="CHILDCARE">Entertainment</option>
        <option value="GIFTS_DONATIONS">Miscellaneous</option>
        <option value="OTHER">Other</option>
    </select>
    <label for="Frequency">Frequency</label>
    <select id="frequency" name="frequency" required>
        <option value="WEEKLY">Weekly</option>
        <option value="BIWEEKLY">Bi-Weekly</option>
        <option value="MONTHLY">Monthly</option>
        <option value="YEARLY">Yearly</option>
        <option value="ONE_TIME">Once</option>
    </select>
    <label for="startDate">Start Date</label>
    <input type="date" id="startDate" name="startDate" required />
    <label for="endDate">End Date</label>
    <input type="date" id="endDate" name="endDate" required />
    <label for="amount">Amount</label>
    <input type="number" id="amount" name="amount" required />    
    <br /><br />
    <button type="submit">Save</button>
</form>

