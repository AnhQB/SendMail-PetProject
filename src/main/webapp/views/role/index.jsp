<%@page import="fsoft.jits.mybatis.model.Role"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<link rel="stylesheet" href="/resources/css/style.css" type="text/css">

</head>
<body>
	<% List<Role> list = (List<Role>) request.getAttribute("listRole"); 
	%>
	<a href="create-update">Create new role</a>
	<table border="1" width= "50%">
		<tr>
			<th>#</th>
			<th>Name</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		
			<% for(Role role: list){ %>
			<tr>
				<td><%= role.getRoleId()%></td>
				<td><%= role.getName()%></td>
				<td><a href="create-update?id=<%= role.getRoleId()%>">Edit</a></td>
				<td><a href="delete/<%= role.getRoleId()%>">Delete</a></td>
			</tr>
			<%} %>
	</table>
</body>
</html>