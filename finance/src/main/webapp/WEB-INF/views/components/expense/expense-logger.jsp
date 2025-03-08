<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/expense/expense-logger.css">
</head>

<div id="expenseButtonContainer">
    <button 
        data-modal-target="expenseLogModal"
        data-modal-size="small"
    >
        Add New Expense Item
    </button>
</div>

<c:if test="${not empty expenseLog.items}">
    <div id="expenseLogContainer">
        <div id="expenseLogHeaderContainer">
            <span>Expense Name</span>
            <span>Category</span>
            <span>Frequency</span>
            <span>Start Date</span>
            <span>End Date</span>
            <span>Amount</span>
            <span>Actions</span>
        </div>
        <div id="expenseLogContentContainer">
             <c:forEach var="item" items="${expenseLog.items}">
                 <div class="expense-log-row">
                    <span>${item.name}</span>
                    <span>${item.category}</span>
                    <span>${item.frequency}</span>
                    <span>${item.startDate}</span>
                    <span>${item.endDate}</span>
                    <span>$${item.amount}</span>
                    <span>
                        <form action="${pageContext.request.contextPath}/expense/delete-item/${item.expenseItemId}" method="post">
                            <button type="submit">Delete</button>
                        </form>
                    </span>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

<c:if test="${empty expenseLog.items}">
    <div id="expenseLogEmptyContainer">
        <span>No expenses added yet.</span>
    </div>
</c:if>


<div id="expenseLogDetailModal" class="modal" data-modal-id="expenseLogDetailModal">
    <div class="modal-content">
        <span class="modal-close">&times;</span>
        <div class="modal-content-body"></div>
    </div>
</div>