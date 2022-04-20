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
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h1>${book.title}</h1>
			<a href="/books">back to the shelves</a>
		</div>
		<h5>${book.user.userName} read ${book.title} by ${book.author}</h5>
		<h5>Here are ${book.user.userName}'s thoughts:</h5>
		<p>${book.myThoughts}</p>
		<c:if test="${user_id == book.user.id }">
			<a href="/books/edit/${book.id}"><button class="btn btn-primary">Edit</button></a>
		</c:if>		
	</div>   
</body>
</html>