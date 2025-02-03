<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="edu.fin.models.user.User" %>
<%
	User user = (User) session.getAttribute("user");
%>
<jsp:include page="<%= (user != null) ? 'dashboard.jsp' : 'login.jsp' %>" />