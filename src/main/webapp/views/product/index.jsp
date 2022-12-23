<%@page import="fsoft.jits.mybatis.dto.ProductDTO"%>
<%@page import="fsoft.jits.mybatis.model.Product"%>
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
<link rel="stylesheet" href="/resources/css/style.css" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
	<% List<ProductDTO> list = (List<ProductDTO>) request.getAttribute("listProduct"); 
		Map<Integer, Category> mapCate = (Map<Integer, Category>) request.getAttribute("mapCategory");
		Map<Integer, SubCategory> mapSubCate = (Map<Integer, SubCategory>) request.getAttribute("mapSubCategory");
		String searchByName = (String) request.getAttribute("searchByName");
		String searchPriceFrom = (String) request.getAttribute("searchPriceFrom");
		String searchPriceTo = (String) request.getAttribute("searchPriceTo");
		int numberPage = (Integer) request.getAttribute("numberPage");
		int currentPage = (Integer) request.getAttribute("currentPage");
	%>
	<div class="form">
		<form action="">
			<label>Name</label>
			<input type="text" placeholder="Search by Name" name="searchByName" value="<%= searchByName != null ? searchByName : "" %>">
			<br>
			<label>Price</label>
			<input id="input-from" type="number" min=0 step=0.1 placeholder="From" name="searchPriceFrom" value="<%= searchPriceFrom != null ? searchPriceFrom : 0 %>">
			<input id="input-to" type="number" min=0 step=0.1 placeholder="To" name="searchPriceTo" value="<%= searchPriceTo != null ? searchPriceTo : 0 %>">
			<br> 
			<button type="reset" >Reset</button>
			<button id="btn-search">Search</button>
		</form>
		<br>
		<a href="create-update">Create new Product</a>
		<a href="export" style="margin-left: 50px">DownLoad file </a> 
	</div>
	<div class="form">
		<form action="import" method="post">
	        <label for="import-excel" href="/" class=" btn btn-success" style="color: lightgreen; cursor: pointer;">
				Add file list product 
	        
		        <span id="nameFile"></span>
		        <small class="font-italic help-block " style="color: brown">( dinh dang ten: ten j cx dc )</small>
	        </label>
	        <input type="file" name="csv" id="import-excel" style="display: none"
	               accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
	        >
	        <button id="btn-import" style="padding: 2px; margin-left: 60px">Submit</button>
	    </form>
	</div>
	<table border="1" width= "50%">
		<tr>
			<th>#</th>
			<th>category</th>
			<th>subcategory</th>
			<th>name</th>
			<th>price</th>
			<th>image</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		
			<% for(ProductDTO product: list){ %>
			<tr>
				<td><%= product.getId()%></td>
				<td><%= mapCate.get(product.getCategoryId()).getName()%></td>
				<td><%= mapSubCate.get(product.getSubcategoryId()).getName()%></td>
				<td><%= product.getName()%></td>
				<td><%= product.getPrice()%></td>
				<td>
					<% if(product.getImageBase64() != null){ %>
						<img src="data:image/jpeg;base64,<%= product.getImageBase64() %>" style="max-width: 200px">
					<%}%>
				</td>
				<td><a href="create-update?id=<%= product.getId()%>">Edit</a></td>
				<td><a href="delete/<%= product.getId()%>">Delete</a></td>
			</tr>
			<%} %>
	</table>
	<%if(numberPage > 1){ %>
	<div style="text-align: center; margin-top: 30px; margin-bottom: 20px">
	
	 <% for(int i= 1; i <= numberPage; ++i) {%>
	 	<a style="margin: 0 5px; text-decoration: none" 
	 	href="?searchByName=<%= searchByName != null ? searchByName : "" %>&searchPriceFrom=<%= searchPriceFrom != null ? searchPriceFrom : "" %>&searchPriceTo=<%= searchPriceTo != null ? searchPriceTo : "" %>&currentPage=<%= i %>" data-page="<%= i %>">
	 		<% if(currentPage == i){ %>
		 		<b style="border: 1px solid black; padding: 4px; border-radius: 50%"><%= i %></b>		
	 		<%} else{ %>
	 			<%= i %>
	 		<% } %>
	 	</a>
	 
	 <%} %>
	</div>
	<% }%>
</body>
<script>
	$(document).ready(function() {
		$('#btn-import').on('click', function($event){
			$event.preventDefault();
			//console.log($(this).val());
			var formData = new FormData();
			var value = $('#import-excel').prop('files')[0];
			formData.append("file", value);
			
			console.log(value);
			$.ajax({
				url: '/products/import',
				type: 'POST',
				dataType: 'json',
				data: formData,
				enctype : 'multipart/form-data',
				async: false,
	            cache: false,
	            processData: false,
	            contentType: false,
			})
			.done(function(items) {
				location.reload();
			}) 
			
		});
	});
</script>
</html>