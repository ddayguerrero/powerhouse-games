<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<jsp:include page="./WEB-INF/shared/head.jsp" />
<link rel="stylesheet" href="./stylesheets/specials.css">
</head>
<jsp:include page="./WEB-INF/shared/nav.jsp" />
<body>
	
	<div class="specials-container">
		<div class="specials-header">
			<div class="specials-header-title">
				<h2>Power Specials</h2>
				<span> Week of October 23th 2017</span>
			</div>
		</div>
		<div class="card-deck">
			<div class="card">
				<!-- <img class="card-img-top" src="" alt="Card image cap"> -->
				<div class="card-body">
					<h4 class="card-title">Card title</h4>
					<p class="card-text">This is a wider card with supporting text
						below as a natural lead-in to additional content. This content is a
						little bit longer.</p>
				</div>
				<div class="card-footer">
					<small class="text-muted">Last updated 3 mins ago</small>
				</div>
			</div>
			<div class="card">
				<!-- <img class="card-img-top" src="" alt="Card image cap"> -->
				<div class="card-body">
					<h4 class="card-title">Card title</h4>
					<p class="card-text">This card has supporting text below as a
						natural lead-in to additional content.</p>
				</div>
				<div class="card-footer">
					<small class="text-muted">Last updated 3 mins ago</small>
				</div>
			</div>
			<div class="card">
				<!-- <img class="card-img-top" src="" alt="Card image cap"> -->
				<div class="card-body">
					<h4 class="card-title">Card title</h4>
					<p class="card-text">This is a wider card with supporting text
						below as a natural lead-in to additional content. This card has
						even longer content than the first to show that equal height
						action.</p>
				</div>
				<div class="card-footer">
					<small class="text-muted">Last updated 3 mins ago</small>
				</div>
			</div>
		</div>
	</div>

</body>
<jsp:include page="./WEB-INF/shared/footer.html" />
</html>