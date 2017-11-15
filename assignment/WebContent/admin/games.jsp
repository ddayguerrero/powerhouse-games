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

			<main role="main" class="col-sm-9 ml-sm-auto col-md-10 pt-3">
			<h1>Control Panel</h1>

			<h5>
				Games <span>- ${fn:length(games)} rows</span>
			</h5>
			
			<p>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addGameModal">Add Game</button>
			</p>
			
			<div class="table-responsive">
				<table class="table table-striped">
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
							<th>Price</th>
							<th>Discount</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${games}" var="game">
							<tr>
								<td>${game.gameid}</td>
								<td>${game.gameTitle}</td>
								<td class="text-truncate" style="max-width: 200px;"> ${game.gameDescription} </td>
								<td>${game.console}</td>
								<td>${game.numPlayers}</td>
								<td>${game.coop}</td>
								<td>${game.genre}</td>
								<td>${game.release_date}</td>
								<td>${game.developer}</td>
								<td>${game.publisher}</td>
								<td><img class="align-self-center mr-3" height="80"
				src="${game.front_box_art}" alt="Generic placeholder image"></td>
								<td><img class="align-self-center mr-3" height="80"
				src="${game.back_box_art}" alt="Generic placeholder image"></td>
								<td>${game.price}</td>
								<td>${game.discount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</main>
		</div>
	</div>
	<!-- Add Game Modal -->
	<div class="modal fade" id="addGameModal" tabindex="-1" role="dialog" aria-labelledby="addGame" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="addGame">New Game</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
  	      <form action="http://localhost:8080/app/admin/games" method="POST">
	      <div class="modal-body">
	        <div class="container-fluid">
	        		<div class="form-row">
	        			<div class="form-group col-md-6">
	                		<label for="form_title">Title</label>
	                		<input type="text" name="title" class="form-control" id="form_title" required>
		            </div>
		            <div class="form-group col-md-6">
	                		<label for="form_console">Console</label>
	                		<select id="form_console" name="console" class="form-control custom-select">
						  <option value="Microsoft Xbox 360">Xbox 360</option>
						  <option value="Sony Playstation 3">Playstation 3</option>
						  <option value="Nintendo Wii">Wii</option>
						</select>
		            </div>
	        		</div>
	        		<div class="form-row">
	        			<div class="form-group col-md-12">
	                		<label for="form_title">Description</label>
	                		<textarea name="description" class="form-control" id="form_description" rows="3" required></textarea>
		            </div>
	        		</div>
	        		<div class="form-row">
	        			<div class="form-group col-md-3">
	                		<label for="form_players">Players</label>
	                		<select id="form_players" name="players" class="form-control custom-select" required>
						  <option value="1">1</option>
						  <option value="2">2</option>
						  <option value="4+">4 +</option>
						</select>
		            </div>
		            
		            <div class="form-group col-md-3">
	                		<label for="form_coop">Coop</label>
	                		<select id="form_coop" name="coop" class="form-control custom-select" required>
						  <option value="No">No</option>
						  <option value="Yes">Yes</option>
						</select>
		            </div>
		            
		            <div class="form-group col-md-6">
	                		<label for="form_genre">Genre</label>
	                		<input type="text" name="genre" class="form-control" id="form_genre" required>
		            </div>
	        		</div>
	        		<div class="form-row">
	        			<div class="form-group col-md-4">
	                		<label for="form_developer">Developer</label>
	                		<input type="text" name="developer" class="form-control" id="form_developer" required>
		            </div>
		            
		            <div class="form-group col-md-4">
	                		<label for="form_developer">Publisher</label>
	                		<input type="text" name="publisher" class="form-control" id="form_publisher" required>
		            </div>
		            
		            <div class="form-group col-md-4">
	                		<label for="form_genre">Release Date</label>
	                		<input type="date" name="release" class="form-control" id="form_release" required>
		            </div>
	        		</div>
	        		
	        		<div class="form-row">
	        			<div class="form-group col-md-6">
	                		<label for="form_front">Front Cover</label>
	                		<input type="text" name="front" class="form-control" id="form_front">
		            </div>
		            
		            <div class="form-group col-md-6">
	                		<label for="form_back">Back Cover</label>
	                		<input type="text" name="back" class="form-control" id="form_back">
		            </div>
	        		</div>
	        		
	        		<div class="form-row">
	        			<div class="form-group col-md-3">
	                		<label for="form_price">Price ($)</label>
	                		<input type="number" name="price" class="form-control" id="form_price" required>
		            </div>
		            
		            <div class="form-group col-md-3">
	                		<label for="form_discount">Discount ($)</label>
	                		<input type="number" name="discount" class="form-control" id="form_discount" required>
		            </div>
	        		</div>
	        </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Add Game</button>
	      </div>
	      </form>
	    </div>
	  </div>
	</div>
</body>
<jsp:include page="../WEB-INF/shared/admin/footer.jsp" />
</html>