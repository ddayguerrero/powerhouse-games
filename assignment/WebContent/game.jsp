<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<jsp:include page="./WEB-INF/shared/head.html" />
<link rel="stylesheet" href="./stylesheets/game.css">
</head>
<jsp:include page="./WEB-INF/shared/nav.jsp" />
<body>

	<div class="game-container">
		<div class="game-header">
			<div class="game-header-title">
				<h2>${game.gameTitle}</h2>
				<span> by ${game.developer} </span>
			</div>
		</div>
		<div class="media">
			<img class="align-self-center mr-3" height="300"
				src="${game.front_box_art}" alt="Generic placeholder image">
			<div class="media-body">
				<div class="card">
					<div class="card-body">
						<h3 class="card-title">$ ${game.price}</h3>
						<h6 class="card-subtitle mb-2 text-muted">${game.console}</h6>
						<p class="card-text">
						<ul class="list-unstyled">
							<li><span>Number of Players:</span> ${game.numPlayers}</li>
							<li><span>Co-op:</span> <c:out default="None"
									escapeXml="true" value="${game.coop?'Yes':'No'}" /></li>
							<li><span>Release Date:</span></li>
							<li><span>Genres:</span></li>
						</ul>
						</p>
						<a href="#" class="btn btn-primary">Add to Cart</a>
					</div>
				</div>
			</div>
		</div>
		<div class="game-description">
			<h2 class="game-description-title">
	             <a class="text-primary">Description</a>
	        </h2>
			${game.gameDescription}
		</div>
	</div>

</body>
<jsp:include page="./WEB-INF/shared/footer.html" />
</html>