<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>    
    
    <!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registration</title>
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
<h2 class="text-center m-3">Welcome</h2>
<p class="text-center m-3">Join our growing community</p>
<p class="text-left m-3">Register</p>
<form:form action="/register" method="POST" class="container d-flex justify-content-center" modelAttribute="newUser">
		<div class="col-md-6">
		<div>
			<form:label path="userName">User Name:</form:label>
			<form:errors path="userName" class="text-danger"/>
			<form:input type="text" path="userName" class="form-control"/>
		</div>
		<div>
			<form:label path="email">Email:</form:label>
			<form:errors path="email" class="text-danger"/>
			<form:input type="text" path="email" class="form-control"/>
		</div>
		<div>
			<form:label path="password">Password:</form:label>
			<form:errors path="password" class="text-danger"/>
			<form:input type="password" path="password" class="form-control"/>
		</div>
		<div>
			<form:label path="confirm">Confirm Password:</form:label>
			<form:errors path="confirm" class="text-danger"/>
			<form:input type="password" path="confirm" class="form-control"/>
		</div>
		<div>
			<input type="submit" value="Submit" class="btn btn-primary mt-3"/>
		</div>
		</div>
	</form:form>

<p class="text-left m-3">Login</p>

<form:form action="/login" method="POST" class="container d-flex justify-content-center" modelAttribute="newLogin">
		<div class="col-md-6">

		<div>
			<form:label path="email">Email:</form:label>
			<form:errors path="email" class="text-danger"/>
			<form:input type="text" path="email" class="form-control"/>
		</div>
		<div>
			<form:label path="password">Password:</form:label>
			<form:errors path="password" class="text-danger"/>
			<form:input type="password" path="password" class="form-control"/>
		</div>

		<div>
			<input type="submit" value="Submit" class="btn btn-primary mt-3"/>
		</div>
		</div>
	</form:form>
</body>
</html>