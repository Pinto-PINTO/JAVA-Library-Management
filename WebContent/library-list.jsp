<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html style="height:100%;">
<head>
<title>Library Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body class="d-flex flex-column min-vh-100">
	
	
	<header>
	
		<!-- Nav Bar Section -->		
		<nav class="navbar navbar-expand-lg navbar-light mb-4" style="background-color: #5ca08e;">
  			<a class="navbar-brand pl-4 font-weight-bold" href="#" style="color: #08221b;">Music Online</a>
  			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    			<span class="navbar-toggler-icon"></span>
  			</button>
  			<div class="collapse navbar-collapse pl-5" id="navbarText">
    			<ul class="navbar-nav mr-auto">
      				<li class="nav-item">
        				<a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      				</li>
      				<li class="nav-item">
        				<a class="nav-link" href="#">Playlist</a>
      				</li>
      				<li class="nav-item active">
        				<a class="nav-link" href="<%=request.getContextPath()%>/list">Library</a>
      				</li>
      				<li class="nav-item">
        				<a class="nav-link" href="#">Subscription</a>
      				</li>
    			</ul>
    			<span class="navbar-text font-weight-bold text-white">
      				Listen 24/7
    			</span>
  			</div>
		</nav>
		
	</header>
	<br>
	
	
	<div class="row">		
		<div class="container">
			
			<div class="row">
				<div class="col-sm-8">
					<h3 class="text-center" style="color: #08221b;">Songs in Library</h3>
				</div>
				
  				<div class="col-sm-4">
  					<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Song </a>
  				</div>
        		
        		
    		</div>
    		<hr class="border border-5 border-success">
			<br>
			<table class="table table-bordered table-striped table-success">
				<thead>
					<tr class="bg-success">
						<th>ID</th>
						<th>Title</th>
						<th>Artist</th>
						<th>Category</th> 
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="library" items="${listLibrary}">

						<tr>
							<td><c:out value="${library.id}" /></td>
							<td><c:out value="${library.title}" /></td>
							<td><c:out value="${library.artist}" /></td>
							<td><c:out value="${library.category}" /></td>
							<td><a href="edit?id=<c:out value='${library.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${library.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div> 
	</div>
	
	
	<!-- Footer Section -->
	
	<footer class="text-center mt-auto font-weight-bold" style="background-color: #5ca08e;">
		<div class="text-center p-3" style="color: #08221b;">
    		© 2020 Copyright:
    		<a class="text-white">MusicOnline24/7.com</a>
  		</div>
	</footer>
	
	
</body>
</html>