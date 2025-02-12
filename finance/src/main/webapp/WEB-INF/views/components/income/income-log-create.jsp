<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/income/income-log-create.css">
</head>

<div id="incomeLogCreateContainer">
    <h2>Create Income Log</h2>
    <form id="incomeLogCreateForm" action="${pageContext.request.contextPath}/income/create" method="post">
        <label for="startDate">Start Date</label>
        <input type="date" id="startDate" name="startDate" required />
        <label for="endDate">End Date</label>
        <input type="date" id="endDate" name="endDate" required />
        <label for="payFrequency">Pay Frequency</label>
        <select id="annualPayFrequency" name="annualPayFrequency" required>
            <option value="WEEKLY">Weekly</option>
            <option value="BIWEEKLY">Bi-Weekly</option>
            <option value="SEMIMONTHLY">Semi-monthly</option>
            <option value="MONTHLY">Monthly</option>
        </select>
        <label for="amount">Amount</label>
        <input type="number" id="amount" name="amount" required />
        <br /><br />
        <button type="submit">Save</button>
    </form>
</div>

