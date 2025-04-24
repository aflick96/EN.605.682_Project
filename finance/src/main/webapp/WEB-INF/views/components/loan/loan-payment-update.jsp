<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>Edit Loan Payments</h2>
<form:form method="post" modelAttribute="loanPaymentsWrapper" action="${pageContext.request.contextPath}/loans/edit-loan-payments">
    <input type="hidden" name="loanItemId" value="${param.loanItemId}" />

    <table>
        <thead>
            <tr>
                <th>#</th>
                <th>Payment Date</th>
                <th>Payment Amount</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="payment" items="${loanPaymentsWrapper.loanPayments}" varStatus="status">
                <tr>
                    <form:input path="loanPayments[${status.index}].id" type="hidden" />
                    <td>${status.index + 1}</td>
                    <td>
                        <form:input path="loanPayments[${status.index}].paymentDate" type="date" />
                    </td>
                    <td>
                        <form:input path="loanPayments[${status.index}].paymentAmount" type="number" step="0.01" />
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <button type="submit">Update Payments</button>
</form:form>
