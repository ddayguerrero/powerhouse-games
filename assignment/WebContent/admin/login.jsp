<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="../WEB-INF/shared/head.jsp" />
<link rel="stylesheet" href="../stylesheets/login.css">
</head>
<jsp:include page="../WEB-INF/shared/admin/nav.jsp" />   
<body>
	<% if(session != null && session.getAttribute("email") != null) { %>
		<c:redirect url="/admin/users.jsp"/>
	<% } %>
	<div class="container">
		<form action="http://localhost:8080/app/admin/login" method="POST" class="form-signin">
			<h2 class="form-signin-heading">Administrator Log In</h2>
			<input
				type="email" id="inputEmail" class="form-control"
				placeholder="admin@powerhouse.com" name="email" required>
			<input
				type="password" id="inputPassword" class="form-control"
				placeholder="Password" name="password" required>
			<button class="btn btn-lg btn-dark btn-block" type="submit">Log in</button>
			
			<p class="text-danger">
				<c:out value="${requestScope.messages.get(\"login\")}"/>
			</p>
		</form>
	</div>
</body>
</html>