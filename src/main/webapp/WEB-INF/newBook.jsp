<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Share</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-between align-items-center">
			<h1>Add a Book to Your Shelf!</h1>
			<a href="/books">back to the shelves</a>
		</div>
				
		<form:form action="/books/new" method="post" modelAttribute="book">			
			<div class="form-group mb-2">
				<form:label path="title">Title</form:label>
				<form:errors path="title"  class="text-danger"/>
				<form:input path="title" class="form-control" />
			</div>
			<div class="form-group mb-2">
				<form:label path="author">Author</form:label>
				<form:errors path="author"  class="text-danger"/>
				<form:input path="author" class="form-control" />
			</div>
			<div class="form-group mb-2">
				<form:label path="myThoughts">My thoughts</form:label>
				<form:errors path="myThoughts"  class="text-danger"/>
				<form:textarea path="myThoughts" class="form-control" />
			</div>
			<input type="submit" value="submit" class="btn btn-primary" />	
		</form:form>
		
	</div>
   
</body>
</html>