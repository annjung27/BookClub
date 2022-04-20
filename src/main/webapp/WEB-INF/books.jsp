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
    <title>Read Share</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="d-flex flex-row justify-content-between align-items-center">
			<div>
				<h1>Welcome, ${thisUser.userName} </h1>
			   	<p>Books from everyone's shelf:</p>
			</div>
			<div class="d-flex flex-column align-items-end">
				<a href="/logout">logout</a>
			   	<a href="/books/new">+ Add a book to my shelve</a>
			</div>	   	
		</div>	
		
		<table class="table table-striped">
				<thead>
					<tr>
						<th>Id</th>
					    <th>Title</th>
					    <th>Author Name</th>					    
					    <th>Posted By</th>
					    <th>Action</th>						    					    
					</tr>			
				</thead>
				<tbody>				
					<c:forEach items="${allBooks}" var="book">
						<c:if test="${user_id != book.borrower.id }">
							<tr>
								<td>${book.id}</td>						
								<td><a href="/books/${book.id}">${book.title}</a></td>						
								<td>${book.author}</td>	
								<td>${book.user.userName}</td>
								
								<td>
									<c:if test="${user_id == book.user.id }">
										<div class="d-flex">
											<form action="/books/delete/${book.id}" method="post">
												<input type="hidden" name="_method" value="delete"/>
												<input type="submit" value="delete" class="btn btn-danger"/>
											</form>
											<a href="/books/edit/${book.id}"><button class="btn btn-success ms-2">Edit</button></a>
										</div>									
									</c:if>
									<c:if test="${user_id != book.user.id }">
										<form:form action="/borrow/${book.id}" method="post" >									
											<button class="btn btn-primary">borrow</button>
										</form:form>								
									</c:if>					
								</td>																													
							</tr>
						</c:if>
					</c:forEach>																									
				</tbody>				
		</table>
		
		<hr/>
		
		<h2>Borrowed book list</h2>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Title</th>
					<th>Author Name</th>					    
					<th>Posted By</th>
					<th>Action</th>						    					    
				</tr>			
			</thead>
			<tbody>
				<c:forEach items="${allBooks}" var="book">
					<c:if test="${user_id == book.borrower.id }">
						<tr>
							<td>${book.id}</td>						
							<td><a href="/books/${book.id}">${book.title}</a></td>						
							<td>${book.author}</td>	
							<td>${book.user.userName}</td>
							<td>
								<form:form action="/return/${book.id}" method="post" >									
									<button class="btn btn-primary">return</button>
								</form:form>
							</td>
						</tr>							
					</c:if>
				</c:forEach>				
			</tbody>
		</table>
		
		
		
		
	</div>   
</body>
</html>