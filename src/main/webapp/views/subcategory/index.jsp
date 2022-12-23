<%@page import="fsoft.jits.mybatis.model.Category"%>
<%@page import="java.util.Map"%>
<%@page import="fsoft.jits.mybatis.model.SubCategory"%>
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
	<% List<SubCategory> list = (List<SubCategory>) request.getAttribute("listSubCategory"); 
		Map<Integer, Category> mapCate = (Map<Integer, Category>) request.getAttribute("mapCate");
		String searchByName = (String) request.getAttribute("searchByName");
	%>
	<form action="">
		<input type="text" placeholder="Search by Name" name="searchByName" value="<%= searchByName != null ? searchByName : "" %>">
		<button>Search</button>
	</form>
	<br>
	<a href="create-update">Create new SubCategory</a>
	<table border="1" width= "50%">
		<tr>
			<th>#</th>
			<th>category</th>
			<th>name</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		
			<% for(SubCategory subCate: list){ %>
			<tr>
				<td><%= subCate.getId()%></td>
				<td><%= mapCate.get(subCate.getCategoryId()).getName()%></td>
				<td><%= subCate.getName()%></td>
				<td><a href="create-update?id=<%= subCate.getId()%>">Edit</a></td>
				<td><a href="delete/<%= subCate.getId()%>">Delete</a></td>
			</tr>
			<%} %>
	</table>
</body>
</html>