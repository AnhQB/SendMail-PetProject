<%@page import="fsoft.jits.mybatis.model.Product"%>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<%
		Product product = (Product) request.getAttribute("product");
		List<Category> lstCategory = (List<Category>) request.getAttribute("lstCategory");
		List<SubCategory> lstSubCate = (List<SubCategory>) request.getAttribute("lstSubCate");
	%>
	<form method="post" action="save" >
		<input type="hidden" name="id" value="<%= product != null ? product.getId() : 0 %>" >
		Category 
		<br>
		<select id="select-category" name="categoryId">
			<% for(Category cate: lstCategory){ %>
				<option value="<%= cate.getId() %>" 
					<%= product == null ? "" : cate.getId() == product.getCategoryId() ? "selected" : "" %>	
				>
					<%= cate.getName() %>
				</option>
			<% } %>	
		</select>
		<br>
		SubCategory 
		<br>
		<select id="select-subcategory" name="subcategoryId">
			<% if ( lstSubCate != null){ %>
				<% for(SubCategory subCate: lstSubCate){ %>
					<option value="<%= subCate.getId() %>" 
						<%= product == null ? "" : subCate.getId() == product.getSubcategoryId() ? "selected" : "" %>	
					>
						<%= subCate.getName() %>
					</option>
				<% } %>	
			<% } %>
		</select>
		<br>
		Name
		<br>
		<input type="text" name="name" value="<%= product != null ? product.getName() : "" %>" >
		<br>
		Price
		<br>
		<input type="number" name="price" value="<%= product != null ? product.getPrice() : 0 %>" min=0 step=0.1>
		<br>
		Image
		<br>
		<input type="file" name="img" accept="image/*" id="uploadImg">
		<input type="hidden" name="imageBase64" id="imgBase64">
		<img class="preview" id="img" src="" style="max-width: 200px">
		<br>
		<br>
		<button>Save</button>
	</form>
</body>
<script>
	$(document).ready(function() {
		$('#select-category').on('change', function($event){
			//console.log($(this).val());
			let valueCate = $(this).val();
			$.ajax({
				url: '/api/get_all_by_cate_id',
				type: 'POST',
				dataType: 'json',
				data: {cateId: valueCate},
			})
			.done(function(items) {
				$('#select-subcategory').empty();
				$.each(items, function (i, item) {
				    $('#select-subcategory').append($('<option>', { 
				        value: item.id,
				        text : item.name 
				    }));
				});
			})
			
		});

		$('#uploadImg').on('change', function($event){
			var file = $('#uploadImg').prop('files')[0];
			var base64Mark = ';base64,';
			
			var reader = new FileReader();
		    reader.onloadend = function() {
		    	const result =  reader.result;
				console.log(result);
				const indexOffset = result.indexOf(base64Mark) + base64Mark.length;
				const base64 = result.substring(indexOffset);
				console.log(base64); 
				
				$('#imgBase64').attr('value', base64)
				$('#img').attr("src", result);
				
			}
			reader.readAsDataURL(file);
			
			
		})
		
	});
</script>

</html>