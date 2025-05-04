<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/investment/investment-contribution.css">
</head>

<div class="modal-form-container">
    <h2>Investment Contribution</h2>

    <div>
        <button class="page-button" type="button" onclick="togglePaymentForm('single')">Single Contribution</button>
        <button class="page-button" type="button" onclick="togglePaymentForm('recurring')">Recurring Contribution</button>
    </div>

    <div class="modal-form-container" id="singlePaymentForm">
        <form:form class="modal-form" id="investmentContributionForm" modelAttribute="investmentContribution" method="post" action="${pageContext.request.contextPath}/investment/add-investment-contribution">
            <label for="contribution-date">Contribution Date</label>
            <form:input path="contributionDate" id="contribution-date" type="date" required="true"/><br/>

            <label for="contribution-amount">Contribution Amount</label>
            <form:input path="contributionAmount" id="contribution-amount" type="number" step="0.01" required="true"/><br/>

            <form:hidden path="investmentLogId" />
            <br/>
            <button type="submit">Save</button>
        </form:form>       
    </div>
    <div class="modal-form-container" id="recurringPaymentForm" style="display:none;">
        <form:form class="modal-form" id="investmentRecurringContributionForm" modelAttribute="investmentContributions" method="post" action="${pageContext.request.contextPath}/investment/add-investment-contributions">
            <label for="startDate">Start Date</label>
            <form:input path="startDate" id="startDate" type="month" required="true"/><br/>

            <label for="endDate">End Date</label>
            <form:input path="endDate" id="endDate" type="month" required="true"/><br/>

            <label for="contributionAmount">Contribution Amount</label>
            <form:input path="contributionAmount" id="recurringContributonAmount" type="number" step="0.01" required="true"/><br/>

            <label for="contributionDay">Contribution Day</label>
            <form:select path="contributionDay" id="contributionDay" required="true" onchange="hideSpecificDay()">
                <form:option value="FIRST">First of Month</form:option>
                <form:option value="LAST">Last of Month</form:option>
                <form:option value="SPECIFIC">Specific Day</form:option>
            </form:select><br/>

            <div id="specificDayContainer" style="display:none;">
                <label for="specificDay">Specific Day of Month (1-28)</label>
                <form:input path="specificDay" id="specificDay" type="number" min="1" max="28" /><br/>
            </div>

            <form:hidden path="investmentLogId"/>
            <br/>
            <button type="submit">Create Contributions</button>
        </form:form>
    </div>
</div>