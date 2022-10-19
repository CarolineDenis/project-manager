<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Task</title>
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
	<h1 class="text-center m-3">Project: ${project.title}</h1>
	<p class="m-3">Project Lead: ${project.leader.userName}</p>
	<a href="/dashboard" class="btn btn-info m-3">Back to Dashboard</a>

	<form:form action="/project/${project.id}/new/task" method="POST"
		class="container d-flex justify-content-center" modelAttribute="task">
		<div class="col-md-6">

			<div>
				<form:label path="name">Add a task ticket for this team:</form:label>
				<form:errors path="name" class="text-danger" />
				<form:textarea type="text" path="name" class="form-control" />
			</div>

			<form:hidden path="creator" value="${userId}" />
			<form:hidden path="project" value="${project.id}" />

			<div class="d-flex align-items-center justify-content-center">
				<input type="submit" value="Submit" class="btn btn-primary m-3" />
			</div>

		</div>
	</form:form>

	<table class="table table-striped container">
		<c:forEach var="task" items="${tasks}">
			<tr>
				<td>Added by: <c:out value="${task.creator.userName}"></c:out> at <c:out value="${task.createdAt}"></c:out></td>
			</tr>
			<tr>
				<td><c:out value="${task.name}"></c:out></td>
			</tr>
			<tr>
				<td><fmt:formatDate value="${task.createdAt}" pattern="HH:mm:ss a" /></td>
			</tr>
			<tr>
				
				<td>   Today's date and now time   <%
         Date date = new Date();
         out.print( "<p>" +date.toString()+"</p>");
      %></td>
		
			</tr>
		</c:forEach>

	</table>
</body>
</html>