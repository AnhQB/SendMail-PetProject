<%@page import="fsoft.jits.mybatis.model.Role"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Role role = (Role) request.getAttribute("role");
	%>
	<form method="post" action="save" >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="hidden" name="id" value="<%= role != null ? role.getRoleId() : 0 %>" >
		Name 
		<input type="text" name="name" value="<%= role != null ? role.getName() : "" %>"  >
		<button>Save</button>
	</form>
</body>
</html>