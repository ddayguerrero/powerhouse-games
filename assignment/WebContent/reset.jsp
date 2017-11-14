<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="./WEB-INF/shared/head.jsp" />
<link rel="stylesheet" href="./stylesheets/reset.css">
</head>
<jsp:include page="./WEB-INF/shared/nav.jsp" />   
<body>
	<div class="container">
		<form action="http://localhost:8080/app/password" method="POST" class="form-signin">
			<h2 class="form-signin-heading">Insert your account email</h2>
			<input
				type="email" id="email" class="form-control"
				placeholder="Email address" name="email" required>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Reset Password</button>
		</form>
	</div>
</body>
<jsp:include page="./WEB-INF/shared/footer.jsp" />
</html>