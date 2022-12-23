<%@page import="fsoft.jits.mybatis.model.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
</head>
<body>
	<% List<Category> list = (List<Category>) request.getAttribute("listCate"); %>
	
	<a href="initCategory">Init Category</a> <br>
	<a href="create-update">Create new Category</a>
	<table border="1" width= "50%">
		<tr>
			<th>#</th>
			<th>Name</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		
			<% for(Category cate: list){ %>
			<tr>
				<td><%= cate.getId()%></td>
				<td><%= cate.getName()%></td>
				<td><a href="create-update?cateId=<%= cate.getId()%>">Edit</a></td>
				<td><a href="delete?cateId=<%= cate.getId()%>">Delete</a></td>
			</tr>
			<%} %>
	</table>
</body>
</html>