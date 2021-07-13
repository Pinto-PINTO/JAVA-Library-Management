<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
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
	
	
	
	
	<div class="container col-md-5 pt-5">
		<div class="card">
			<div class="card-body border border-success" style="background-color: #d4eee7; color: #04110d;">
				<c:if test="${library != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${library == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2 class="pb-3">
						<c:if test="${library != null}">
            			Edit Song
            		</c:if>
						<c:if test="${library == null}">
            			Add New Song
            		</c:if>
					</h2>
				</caption>
				<hr class="border border-5 border-success">

				<c:if test="${library != null}">
					<input type="hidden" name="id" value="<c:out value='${library.id}' />" />
				</c:if>

				<fieldset class="form-group pt-2">
					<label>Title</label> <input type="text"
						value="<c:out value='${library.title}' />" class="form-control"
						name="title" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Artist</label> <input type="text"
						value="<c:out value='${library.artist}' />" class="form-control"
						name="artist">
				</fieldset>

				<fieldset class="form-group pb-2">
					<label>Category</label> <input type="text"
						value="<c:out value='${library.category}' />" class="form-control"
						name="category">
				</fieldset>

				<button type="submit" class="btn btn-success">Add</button>
				</form>
			</div>
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