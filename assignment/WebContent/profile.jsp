<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<jsp:include page="./WEB-INF/shared/head.jsp" />
<link rel="stylesheet" href="./stylesheets/game.css">
</head>
<jsp:include page="./WEB-INF/shared/nav.jsp" />
<body>

	<div class="game-container">
		<div class="game-header">
			<div class="game-header-title">
				<h2>User Profile</h2>
			</div>
		</div>
		<div class="game-description"></div>
	</div>

	<div class="game-container">
		<div class="game-header">
			<div class="game-header-title">
				<h2>Favorites</h2>
			</div>
		</div>
		<div class="game-description">
			<ul class="list-unstyled">
				<c:forEach items="${favorites}" var="game">
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
										<h3 class=".text-primary">$${game.price}</h3>
									</div>
								</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

</body>
<jsp:include page="./WEB-INF/shared/footer.jsp" />
</html>