<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>Edit Loan Payments</h2>
<form action="${pageContext.request.contextPath}/loans/edit-loan-payments" method="post">
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
            <c:forEach var="payment" items="${loanPayments}" varStatus="status">
                <tr>
                    <input type="hidden" name="loanPayments[${status.index}].id" value="${payment.id}" />
                    <td>${status.index + 1}</td>

                    <td>
                        <input type="date"
                               name="loanPayments[${status.index}].paymentDate"
                               value="${payment.paymentDate}" />
                    </td>

                    <td>
                        <input type="number" step="0.01"
                               name="loanPayments[${status.index}].paymentAmount"
                               value="${payment.paymentAmount}" />
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <button type="submit">Update Payments</button>
</form>