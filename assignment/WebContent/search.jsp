<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<jsp:include page="./WEB-INF/shared/head.html" />
<link rel="stylesheet" href="./stylesheets/search.css">
</head>
<jsp:include page="./WEB-INF/shared/nav.jsp" />
<body>
	<div class="search">
		<div class="search-box">
			<div class="text-center mb-4"></div>
			<form action="http://localhost:8080/app/game" method="GET">
				<div class="input-group">
					<input type="text" class="form-control" name="title"
						placeholder="Enter game title..."> <span
						class="input-group-btn">
						<button class="btn btn-primary" type="submit">Search</button>
					</span>
				</div>
				<p id="help">
					Need help finding what you're looking for? Try the <a href="/app/advanced_search.jsp">Advanced Search</a>
				</p>
			</form>
		</div>
	</div>
</body>
<jsp:include page="./WEB-INF/shared/footer.html" />
</html>