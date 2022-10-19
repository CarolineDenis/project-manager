<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>One Project</title>
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
	<h2 class="text-center m-3">Project Details</h2>

	<a href="/dashboard" class="btn btn-info m-3">Back to dashboard</a>

	<p class="text-center m-3">All Projects</p>

	<table class="table table-striped container">
		<tr>
			<td>Project</td>
			<td><c:out value="${project.title}"></c:out></td>
		</tr>
		<tr>
			<td>Description</td>
			<td><c:out value="${project.description}"></c:out></td>
		</tr>
		<tr>
			<td>Due Date</td>
			<td><fmt:formatDate value="${project.date}" pattern="MMMM dd" /></td>
		</tr>

	</table>
		<c:choose>
			<c:when test="${userId==project.leader.id}">
				<form action="/project/${project.id}" method="post">
					<input type="hidden" name="_method" value="delete"> <input
						type="submit" value="Delete" class="btn btn-danger mt-3">
				</form>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		
<a href="/project/${project.id}/new/task" class="btn btn-dark m-3">See Tasks!</a>
</body>
</html>