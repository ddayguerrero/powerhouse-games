<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<jsp:include page="./WEB-INF/shared/head.html" />
<link rel="stylesheet" href="./stylesheets/search_results.css">
</head>
<jsp:include page="./WEB-INF/shared/nav.jsp" />
<body>

	<div class="results-container">
		<div class="results-header">
			<div class="results-header-title">
				<h2> Search Results</h2>
				<span> We've found ${fn:length(games)} games for your search!</span>
			</div>
		</div>
		<ul class="list-unstyled">
			<c:forEach items="${games}" var="game">
				<li class="media"><img class="mr-3" height="150" height="170"
					src="${game.front_box_art}" alt="Generic placeholder image">
					<div class="media-body">
						<div class="container">
							<div class="row">
								<div class="col col-md-6">
									<h5 class="mt-0 mb-1">
										<a href="/app/game?id=<c:out value="${game.gameid}"/>">${game.gameTitle}</a>
									</h5>
									<h6>by ${game.developer}</h6>
									<p>${game.console}</p>
								</div>
								<div class="col col-md-6">
									<div class="rating-block">
										Rating
										<button type="button" class="btn btn-warning btn-sm" aria-label="Left Align">
										  <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
										</button>
										<button type="button" class="btn btn-warning btn-sm" aria-label="Left Align">
										  <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
										</button>
										<button type="button" class="btn btn-warning btn-sm" aria-label="Left Align">
										  <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
										</button>
										<button type="button" class="btn btn-default btn-grey btn-sm" aria-label="Left Align">
										  <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
										</button>
										<button type="button" class="btn btn-default btn-grey btn-sm" aria-label="Left Align">
										  <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
										</button>
									</div>
									<button type="button" class="btn btn-dark">NEW $${game.price}</button>
								</div>
							</div>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>

</body>
<jsp:include page="./WEB-INF/shared/footer.html" />
</html>