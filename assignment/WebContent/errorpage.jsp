<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<jsp:include page="./WEB-INF/shared/head.jsp" />
<link rel="stylesheet" href="./stylesheets/errorpage.css">
<body>
	<div class="error">
		<div class="error-code m-b-10 m-t-20">
			Game Over! <i class="fa fa-warning"></i>
		</div>
		<h3 class="font-bold">404 - Page Not Found</h3>

		<div class="error-desc">
			Sorry, but the page you are looking for was either not found or does
			not exist. <br /> Try refreshing the page or click the button below
			to go back Home.
			<div>
				<a class=" login-detail-panel-button btn" href="/app/search.jsp"> <i
					class="fa fa-arrow-left"></i> Go back to Home
				</a>
			</div>
		</div>
	</div>
</body>
</html>