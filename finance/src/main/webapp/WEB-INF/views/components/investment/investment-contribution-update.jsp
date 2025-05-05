<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/loan/loan-edit.css">
<h2>Edit Investment Contributions</h2>

<form:form method="post" modelAttribute="investmentContributionsWrapper" action="${pageContext.request.contextPath}/investment/edit-investment-contributions">
    <input type="hidden" name="investmentLogId" value="${param.investmentLogId}" />

    <table>
        <thead>
            <tr>
                <th>#</th>
                <th>Contribution Date</th>
                <th>Contribution Amount</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="contribution" items="${investmentContributionsWrapper.investmentContributions}" varStatus="status">
                <tr id="contribution-${status.index}">
                    <form:input path="investmentContributions[${status.index}].id" type="hidden" />
                    <td>${status.index + 1}</td>
                    <td>
                        <form:input path="investmentContributions[${status.index}].contributionDate" type="date"/>
                    </td>
                    <td>
                        <form:input path="investmentContributions[${status.index}].contributionAmount" id="amount-${status.index}" type="number" step="0.01"/>
                    </td>
                    <td>
                        <button type="button" class="delete-button" onclick="deleteContribution('${status.index}')">&times;</button>
                    </td>                
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <button type="submit">Update Contributions</button>
</form:form>
