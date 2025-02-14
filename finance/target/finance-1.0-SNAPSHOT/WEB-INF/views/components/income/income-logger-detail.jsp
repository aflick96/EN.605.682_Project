<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/income/income-logger-detail.css">
</head>

<h2>Income Log Details</h2>
<table border="1">
    <tr>
        <th>Pay Date</th>
        <th>Gross Income</th>
        <th>Additional Income</th>
        <th>Pre-Tax Deductions</th>
        <th>Taxable Income</th>
        <th>Federal Tax</th>
        <th>State Tax</th>
        <th>Post-Tax Deductions</th>
        <th>Net Income</th>
    </tr>
    <c:forEach var="detail" items="${incomeLogDetails}">
        <tr>
            <td>${detail.payDate}</td>
            <td>$${detail.grossIncome}</td>
            <td>$${detail.additionalIncome}</td>
            <td>$${detail.pretaxDeductions}</td>
            <td>$${detail.taxableIncome}</td>
            <td>$${detail.federalTax}</td>
            <td>$${detail.stateTax}</td>
            <td>$${detail.posttaxDeductions}</td>
            <td>$${detail.netIncome}</td>
        </tr>
    </c:forEach>
</table>