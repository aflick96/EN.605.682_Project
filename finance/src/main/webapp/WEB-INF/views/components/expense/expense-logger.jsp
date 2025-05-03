<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/expense/expense-logger.css">
</head>

<div class="page-main-button-container">
    <button 
        class="page-button page-button-big"
        data-modal-target="expenseLogModal"
        data-modal-size="medium"
        data-fetch-url="${pageContext.request.contextPath}/expenses/add-item?expenseLogId=${expenseLog.id}"
    >
        Add New Expense Item
    </button>
</div>

<c:if test="${not empty expenseLog.items}">
    <div class="page-table-container">
        <table class="page-table" border="1">
        <tr>
            <th>Expense Name</th>
            <th>Category</th>
            <th>Frequency</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Amount</th>
            <th colspan="2">Actions</th>
        </tr>

             <c:forEach var="item" items="${expenseLog.items}">
                 <tr>
                    <td>${item.name}</td>
                    <td>
                        <c:choose>
                            <c:when test="${item.category == 'RENT'}">Rent</c:when>
                            <c:when test="${item.category == 'UTILITIES'}">Utilities</c:when>
                            <c:when test="${item.category == 'TRANSPORTATION'}">Transportation</c:when>
                            <c:when test="${item.category == 'FOOD'}">Food</c:when>
                            <c:when test="${item.category == 'INSURANCE'}">Insurance</c:when>
                            <c:when test="${item.category == 'HEALTHCARE'}">Health Care</c:when>
                            <c:when test="${item.category == 'ENTERTAINMENT'}">Entertainment</c:when>
                            <c:when test="${item.category == 'PERSONAL_CARE'}">Personal Care</c:when>
                            <c:when test="${item.category == 'CHILDCARE'}">Child Care</c:when>
                            <c:when test="${item.category == 'GIFTS_DONATIONS'}">Gifts/Donations</c:when>
                            <c:otherwise>Other</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${item.frequency == 'WEEKLY'}">Weekly</c:when>
                            <c:when test="${item.frequency == 'BIWEEKLY'}">Bi-Weekly</c:when>
                            <c:when test="${item.frequency == 'MONTHLY'}">Monthly</c:when>
                            <c:when test="${item.frequency == 'YEARLY'}">Yearly</c:when>
                            <c:when test="${item.frequency == 'ONE_TIME'}">Once</c:when>
                        </c:choose>
                    </td>                    
                    <td>${item.startDate}</td>
                    <td>${item.endDate}</td>
                    <td>$<fmt:formatNumber value="${item.amount}" type="number" groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" /></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/expenses/delete-item?itemId=${item.id}" method="post">
                            <button 
                                class="page-button"
                                type="submit"
                            >
                                Delete
                            </button>
                        </form>
                    </td>
                    <td>
                        <c:url var="editUrl" value="/expenses/update-item">
                            <c:param name="id" value="${item.id}" />
                            <c:param name="expenseLogId" value="${expenseLog.id}" />
                            <c:param name="name" value="${item.name}" />
                            <c:param name="category" value="${item.category}" />
                            <c:param name="frequency" value="${item.frequency}" />
                            <c:param name="startDate" value="${item.startDate}" />
                            <c:param name="endDate" value="${item.endDate}" />
                            <c:param name="amount" value="${item.amount}" />
                        </c:url>
                        
                        <form class="edit-expense-button"
                            data-modal-target="expenseLogModal"
                            data-modal-size="medium"
                            data-fetch-url="${editUrl}">
                            <button 
                                class="page-button"
                                type="button"
                            >
                                Edit
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
</c:if>

<!-- <c:if test="${empty expenseLog.items}">
    <div id="expenseLogEmptyContainer">
        <span>No expenses added yet.</span>
    </div>
</c:if> -->

<!-- <div id="expenseLogDetailModal" class="modal" data-modal-id="expenseLogDetailModal">
    <div class="modal-content">
        <span class="modal-close">&times;</span>
        <div class="modal-content-body"></div>
    </div>
</div> -->