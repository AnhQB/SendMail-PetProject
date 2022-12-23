<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/home.css">

<title>Euro Championship</title>
</head>

<body>
	<h3 class="my-header">EURO CHAMPIONSHIP</h3>
	<hr>
	<div class="container">
		<div class="row">
			<div class="col-3 border-right">
				<div class="container">
					<% if(request.getUserPrincipal() != null){ %>
						<p>Welcome ${pageContext["request"].userPrincipal.principal.username}</p>
					<%} %>
					<p>Menu</p>
					<ul>
						<li><a href="#" id="team-management">Teams Management</a></li>
						<li><a href="#" id="information-group">Information Group</a></li>
						<li><a href="#" id="information-chars">Information Chars</a></li>
						<li><a href="#" id="who-champion">Who's Champion?</a></li>
						<li><a href="categories/" >category</a></li>
						<li><a href="subcategory/" >subcategory</a></li>
						<li><a href="manager/products/" >product</a></li>
						<li><a href="manager/role/" >role</a></li>
						<% if(request.getUserPrincipal() != null){ %>
							<li><a href="/perform_logout" >logout</a></li>
						<%}else{ %>
							<li><a href="/login" >login</a></li>
						<% } %>
					</ul>
				</div>
			</div>
			<div class="col-9">
				<div class="container my-content" id="my-content">
					<h3>WELCOME TO EURO FOOTBALL CHAMPION</h3>
				</div>
			</div>
		</div>
	</div>

	<div class="row fixed-bottom">
		<div class="col-md-12">
			<h3 class="my-footer">Base on Spring Framework</h3>
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/resources/js/home.js"></script>
</html>