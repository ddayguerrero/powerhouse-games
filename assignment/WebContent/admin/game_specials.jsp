<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../WEB-INF/shared/head.jsp" />
<link rel="stylesheet" href="../stylesheets/sidebar.css">
</head>
<jsp:include page="../WEB-INF/shared/admin/nav.jsp" />
<body>
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="../WEB-INF/shared/admin/sidebar.jsp" />

			<main role="main" class="col-sm-12 ml-sm-auto col-md-12 pt-3">
			<h1>Control Panel</h1>

			<h5>
				Games on Sale <span>- ${fn:length(games)} rows</span>
			</h5>

			<p>
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#sendNewsletterModal">Send Newsletter</button>
			</p>

			<div class="table-responsive">
				<table id="gameInventory"
					class="table table-sm table-hover table-dark">
					<thead>
						<tr>
							<th>#</th>
							<th>Title</th>
							<th>Game Description</th>
							<th>Console</th>
							<th>Number of Players</th>
							<th>Coop</th>
							<th>Genre</th>
							<th>Release Date</th>
							<th>Developer</th>
							<th>Publisher</th>
							<th>Front-Box Art</th>
							<th>Back-Box Art</th>
							<th>MSRP</th>
							<th>Discount</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${games}" var="game">
							<tr>
								<td class="rowGameId">${game.gameid}</td>
								<td class="rowGameTitle">${game.gameTitle}</td>
								<td class="rowGameDescription text-truncate"
									style="max-width: 200px;">${game.gameDescription}</td>
								<td class="rowGameConsole">${game.console}</td>
								<td class="rowGamePlayers">${game.numPlayers}</td>
								<td class="rowGameCoop">${game.coop}</td>
								<td class="rowGameGenre">${game.genre}</td>
								<td class="rowGameRelease">${game.release_date}</td>
								<td class="rowGameDeveloper">${game.developer}</td>
								<td class="rowGamePublisher">${game.publisher}</td>
								<td><img class="rowGameFront align-self-center mr-3"
									height="80" src="${game.front_box_art}"
									alt="Generic placeholder image"></td>
								<td><img class="rowGameBack align-self-center mr-3"
									height="80" src="${game.back_box_art}"
									alt="Generic placeholder image"></td>
								<td class="rowGamePrice">${game.price}</td>
								<td class="rowGameDiscount">${game.discount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</main>
		</div>
	</div>

	<div class="modal" id="sendNewsletterModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Confirm Newsletter</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>Are you sure you want to advertise current PGH to all
						members?</p>
				</div>
				<div class="modal-footer">
					<form action="http://localhost:8080/app/admin/specials"
						method="POST">
						<button type="submit" class="btn btn-primary">Send</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
<jsp:include page="../WEB-INF/shared/admin/footer.jsp" />
<script type="text/javascript">
	
</script>
</html>