<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>    
    
    <!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Project</title>
        <!-- OWN CSS -->
        <link rel="stylesheet" type="text/css" href="/css/style.css">
		<script type="text/javascript" src="/js/app.js"></script>
		<!-- for Bootstrap CSS -->
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<!-- For any Bootstrap that uses JS or jQuery-->
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    </head>
<body>
	<h1 class="d-flex justify-content-center">Edit Project</h1>
	<a href="/dashboard">Home</a>

	<form:form action="/project/edit/${project.id}" method="POST" class="container d-flex justify-content-center" modelAttribute="project">
		<input type="hidden" name="_method" value="put">
		<div class="col-md-6"> 
		
		<div>
			<form:label path="title">Project Title:</form:label>
			<form:errors path="title" class="text-danger"/>
			<form:input type="text" path="title" class="form-control"/>
		</div>
		
		<div>
			<form:label path="description">Project Description:</form:label>
			<form:errors path="description" class="text-danger"/>
			<form:input type="text" path="description" class="form-control"/>
		</div>
		
		<div>
			<form:label path="date">Date:</form:label>
			<form:errors path="date" class="text-danger"/>
			<form:input type="date" path="date" class="form-control"/>
		</div>
		
		<form:hidden path="leader"/>
		<form:hidden path="workers"/>

		<div class="d-flex">
			<input type="submit" value="Submit" class="btn btn-primary m-3"/>
			<a href="/dashboard" class="btn btn-warning m-3">Cancel</a>	
		</div>

		</div>
	</form:form>
</body>
</html>