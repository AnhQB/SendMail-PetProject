<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="fsoft.jits.mybatis.model.SubCategory"%>
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
	<% SubCategory subCate = (SubCategory) request.getAttribute("subCategory");
		List<Category> listCate = (List<Category>) request.getAttribute("listCate");
	%>
	<form method="post" action="save">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="hidden" name="id" value="<%= subCate != null ? subCate.getId() : 0 %>" >
		Category 
		<br>
		<select name="categoryId">
			<% for(Category cate: listCate){ %>
				<option value="<%= cate.getId() %>" 
					<%= subCate == null ? "" : cate.getId() == subCate.getCategoryId() ? "selected" : "" %>	
				>
					<%= cate.getName() %>
				</option>
			<% } %>
		</select>
		<br>
		Name
		<br>
		<input type="text" name="name" value="<%= subCate != null ? subCate.getName() : "" %>" >
		<br>
		<button>Save</button>
	</form>
</body>
</html>