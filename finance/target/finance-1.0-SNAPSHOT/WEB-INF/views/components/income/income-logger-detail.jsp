<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/income/income-logger-detail.css">
</head>

<h2 class="modal-form-header">Income Log Details</h2>
<div class="income-logger-detail-container">
    <div class="income-logger-detail-header-container">
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
                <span>$<fmt:formatNumber value="${detail.grossIncome}" type="number" minFractionDigits="2" maxFractionDigits="2"/></span>
                <span>$<fmt:formatNumber value="${detail.additionalIncome}" type="number" minFractionDigits="2" maxFractionDigits="2"/></span>
                <span>$<fmt:formatNumber value="${detail.pretaxDeductions}" type="number" minFractionDigits="2" maxFractionDigits="2"/></span>
                <span>$<fmt:formatNumber value="${detail.taxableIncome}" type="number" minFractionDigits="2" maxFractionDigits="2"/></span>
                <span>$<fmt:formatNumber value="${detail.federalTax}" type="number" minFractionDigits="2" maxFractionDigits="2"/></span>
                <span>$<fmt:formatNumber value="${detail.stateTax}" type="number" minFractionDigits="2" maxFractionDigits="2"/></span>
                <span>$<fmt:formatNumber value="${detail.posttaxDeductions}" type="number" minFractionDigits="2" maxFractionDigits="2"/></span>
                <span>$<fmt:formatNumber value="${detail.netIncome}" type="number" minFractionDigits="2" maxFractionDigits="2"/></span>
            </div>
        </c:forEach>    
    </div>
</div>