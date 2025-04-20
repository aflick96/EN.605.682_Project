<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/components/expense/expense-logger.css">
</head>

<div id="expenseButtonContainer">
    <button 
        data-modal-target="expenseLogModal"
        data-modal-size="small"
        data-fetch-url="${pageContext.request.contextPath}/expenses/add-item?expenseLogId=${expenseLog.id}"
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
                        <form action="${pageContext.request.contextPath}/expenses/delete-item?itemId=${item.id}" method="post">
                            <button type="submit">Delete</button>
                        </form>
                    </span>
                    <span>
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
                              data-modal-target="expenseLogDetailModal"
                              data-modal-size="small"
                              data-fetch-url="${editUrl}">
                            <button type="button">Edit</button>
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