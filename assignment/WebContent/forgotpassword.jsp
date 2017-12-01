<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="./WEB-INF/shared/head.jsp" />
<link rel="stylesheet" href="./stylesheets/login.css">
</head>
<jsp:include page="./WEB-INF/shared/nav.jsp" />   
<body>
	<div class="container">
		<form action="http://localhost:8080/app/password" method="POST" class="form-signin">
			<h2 class="form-forgotpassword-heading">Forgot Password</h2>
			<input
				type="text" id="inputName" class="form-control"
				placeholder="Name" name="name">
			<input
				type="email" id="inputEmail" class="form-control"
				placeholder="Email address" name="email" required>
			<p>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Send Verification Link</button>
			</p>
			<p class="text-primary">
				<c:out value="${requestScope.messages.get(\"pass\")}"/>
			</p>
		</form>
	</div>
</body>
<jsp:include page="./WEB-INF/shared/footer.jsp" />
</html>