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
<title>Dashboard</title>
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
	<h2 class="m-3">Welcome, ${user.userName}</h2>

	<a href="/logout" class="btn btn-info m-3">logout</a>
	<a href="/new/project" class="btn btn-info m-3">New Project</a>

	<h2 class="text-center m-3">All Projects</h2>

	<table class="table table-striped container">
		<tr>
			<th>Project</th>
			<th>Team Lead</th>
			<th>Due Date</th>
			<th>Actions</th>
		</tr>
		<c:forEach var="project" items="${projectVaccant}">
			<tr>
				<td><a href="/project/${project.id}"><c:out
							value="${project.title}"></c:out></a></td>
				<td><c:out value="${project.leader.userName}"></c:out></td>
				<td><fmt:formatDate value="${project.date}" pattern="MMMM dd" /></td>
				<td>
					<form action="/jointeam/${project.id}" method="post">
						<input type="hidden" name="_method" value="put"> <input
							type="hidden" name="projectId" value="${project.id}" /> <input
							type="hidden" name="userId" value="${user.id}" /> <input
							type="submit" value="join" class="btn btn-dark mt-3">
					</form>
				</td>
				<td></td>
			</tr>
		</c:forEach>
	</table>

	<h2 class="text-center m-3">Your projects</h2>
	<table class="table table-striped container">
		<tr>
			<th>Project</th>
			<th>Team Lead</th>
			<th>Due Date</th>
			<th>Actions</th>
		</tr>
		<c:forEach var="project" items="${projectForUser}">
			<tr>
				<td><a href="/project/${project.id}"><c:out
							value="${project.title}"></c:out></a></td>
				<td><c:out value="${project.leader.userName}"></c:out></td>
				<td><fmt:formatDate value="${project.date}" pattern="MMMM dd" /></td>
				<td>
					<div>
						<c:choose>
							<c:when test="${userId==project.leader.id}">
								<a href="/project/edit/${project.id}"
									class="btn btn-warning mt-3">Edit</a>
							</c:when>
							<c:otherwise>
								<form action="/leaveteam/${project.id}" method="post">
									<input type="hidden" name="_method" value="put"> <input
										type="hidden" name="projectId" value="${project.id}" /> <input
										type="hidden" name="userId" value="${user.id}" /> <input
										type="submit" value="leave team" class="btn btn-dark mt-3">
								</form>
							</c:otherwise>
						</c:choose>
					</div>
				</td>
				<td></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>