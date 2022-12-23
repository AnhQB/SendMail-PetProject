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
	<h3>Update <span><%= cate.getName() %></span></h3>
	<form method="post" action="update">
		<input type="hidden" value= "<%= cate.getId() %>" name="id" >
		<input type="text" value="<%= cate.getName() %>" name="name"> <br>
		<button>Update</button>
	</form>
</body>
</html>