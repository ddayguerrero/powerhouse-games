<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="./WEB-INF/shared/head.html" />
<link rel="stylesheet" href="./stylesheets/login.css">
</head>
<jsp:include page="./WEB-INF/shared/nav.jsp" />   
<body>
	<div class="container">
		<form action="http://localhost:8080/app/login" method="POST" class="form-signin">
			<h2 class="form-signin-heading">Log In</h2>
			<input
				type="email" id="inputEmail" class="form-control"
				placeholder="Email address" name="email" required>
			<input
				type="password" id="inputPassword" class="form-control"
				placeholder="Password" name="password" required>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
			
			<p class="text-danger">
				<c:out value="${requestScope.messages.get(\"login\")}"/>
			</p>
		</form>
	</div>
</body>
<jsp:include page="./WEB-INF/shared/footer.html" />
</html>