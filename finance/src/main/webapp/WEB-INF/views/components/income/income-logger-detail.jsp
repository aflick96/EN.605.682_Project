<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/income/income-logger-detail.css">
</head>

<h2>Income Log Details</h2>
<div class="income-logger-detail-container">
    <div id="income-logger-detail-header-container">
        <span>Pay Date</span>
        <span>Gross Income</span>
        <span>Additional Income</span>
        <span>Pre-Tax Deductions</span>
        <span>Taxable Income</span>
        <span>Federal Tax</span>
        <span>State Tax</span>
        <span>Post-Tax Deductions</span>
        <span>Net Income</span>
    </div>
    <div class="income-logger-detail-content-container">
        <c:forEach var="detail" items="${incomeLogDetails}">
            <div class="income-logger-detail-row">
                <span>${detail.payDate}</span>
                <span>$${detail.grossIncome}</span>
                <span>$${detail.additionalIncome}</span>
                <span>$${detail.pretaxDeductions}</span>
                <span>$${detail.taxableIncome}</span>
                <span>$${detail.federalTax}</span>
                <span>$${detail.stateTax}</span>
                <span>$${detail.posttaxDeductions}</span>
                <span>$${detail.netIncome}</span>
            </div>
        </c:forEach>    
    </div>
</div>