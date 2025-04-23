<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>Edit Investment Contributions</h2>

<form action="${pageContext.request.contextPath}/investment/edit-investment-contributions" method="post">
    <input type="hidden" name="investmentLogId" value="${param.investmentLogId}" />

    <table>
        <thead>
            <tr>
                <th>#</th>
                <th>Contribution Date</th>
                <th>Contribution Amount</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="contribution" items="${investmentContributions}" varStatus="status">
                <tr>
                    <input type="hidden" name="investmentContributions[${status.index}].id" value="${contribution.id}" />
                    <td>${status.index + 1}</td>

                    <td>
                        <input type="date"
                               name="investmentContributions[${status.index}].contributionDate"
                               value="${contribution.contributionDate}" />
                    </td>

                    <td>
                        <input type="number" step="0.01"
                               name="investmentContributions[${status.index}].contributionAmount"
                               value="${contribution.contributionAmount}" />
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <button type="submit">Update Contributions</button>
</form>
