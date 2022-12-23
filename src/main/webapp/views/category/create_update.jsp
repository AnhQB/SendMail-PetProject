<%@page import="fsoft.jits.mybatis.model.Category"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% Category cate = (Category) request.getAttribute("category"); %>
	<form method="post" action="save">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="hidden" name="id" value="<%= cate != null ? cate.getId() : 0 %>" >
		Name<br>
		<input type="text" name="name" value="<%= cate != null ? cate.getName() : "" %>" >
		<br>
		<button>Create</button>
	</form>
</body>
</html>