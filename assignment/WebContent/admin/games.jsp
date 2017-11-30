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
				Games <span>- ${fn:length(games)} rows</span>
			</h5>
			
			<p>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addGameModal">Add Game</button>
			</p>
			
			<div class="table-responsive">
				<table id="gameInventory" class="table table-sm table-hover">
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
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${games}" var="game">
							<tr data-toggle="modal" data-id="${game.gameid}" data-target="#editGameModal">
								<td class="rowGameId">${game.gameid}</td>
								<td class="rowGameTitle">${game.gameTitle}</td>
								<td class="rowGameDescription text-truncate" style="max-width: 200px;"> ${game.gameDescription} </td>
								<td class="rowGameConsole">${game.console}</td>
								<td class="rowGamePlayers">${game.numPlayers}</td>
								<td class="rowGameCoop">${game.coop}</td>
								<td class="rowGameGenre">${game.genre}</td>
								<td class="rowGameRelease">${game.release_date}</td>
								<td class="rowGameDeveloper">${game.developer}</td>
								<td class="rowGamePublisher">${game.publisher}</td>
								<td><img class="rowGameFront align-self-center mr-3" height="80"
				src="${game.front_box_art}" alt="Generic placeholder image"></td>
								<td><img class="rowGameBack align-self-center mr-3" height="80"
				src="${game.back_box_art}" alt="Generic placeholder image"></td>
								<td class="rowGamePrice">${game.price}</td>
								<td class="rowGameDiscount">${game.discount}</td>
								<th><span class="badge badge-pill badge-warning">Special</span></th>
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
	                		<input type="number" step=".01" name="price" class="form-control" id="form_price" required>
		            </div>
		            
		            <div class="form-group col-md-3">
	                		<label for="form_discount">Discount ($)</label>
	                		<input type="number" step=".01" name="discount" class="form-control" id="form_discount" required>
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
	
	<!-- Edit Game Modal -->
	<div class="modal fade" id="editGameModal" tabindex="-1" role="dialog" aria-labelledby="editGame" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Edit Game - <span id="editGame"></span></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
  	      <form action="http://localhost:8080/app/admin/games/edit" method="POST">
		      <div class="modal-body">
		        <div class="container-fluid">
		        		<input type="hidden" name="edit_id" id="edit_form_id">
		        		<div class="form-row">
		        			<div class="form-group col-md-6">
		                		<label for="form_title">Title</label>
		                		<input type="text" name="edit_title" class="form-control" id="edit_form_title" required>
			            </div>
			            <div class="form-group col-md-6">
		                		<label for="form_console">Console</label>
		                		<select id="edit_form_console" name="edit_console" class="form-control custom-select">
							  <option value="Microsoft Xbox 360">Xbox 360</option>
							  <option value="Sony Playstation 3">Playstation 3</option>
							  <option value="Nintendo Wii">Wii</option>
							</select>
			            </div>
		        		</div>
		        		<div class="form-row">
		        			<div class="form-group col-md-12">
		                		<label for="form_title">Description</label>
		                		<textarea name="edit_description" class="form-control" id="edit_form_description" rows="3" required></textarea>
			            </div>
		        		</div>
		        		<div class="form-row">
		        			<div class="form-group col-md-3">
		                		<label for="form_players">Players</label>
		                		<select id="edit_form_players" name="edit_players" class="form-control custom-select" required>
							  <option value="1">1</option>
							  <option value="2">2</option>
							  <option value="4+">4 +</option>
							</select>
			            </div>
			            
			            <div class="form-group col-md-3">
		                		<label for="form_coop">Coop</label>
		                		<select id="edit_form_coop" name="edit_coop" class="form-control custom-select" required>
							  <option value="No">No</option>
							  <option value="Yes">Yes</option>
							</select>
			            </div>
			            
			            <div class="form-group col-md-6">
		                		<label for="form_genre">Genre</label>
		                		<input type="text" name="edit_genre" class="form-control" id="edit_form_genre" required>
			            </div>
		        		</div>
		        		<div class="form-row">
		        			<div class="form-group col-md-4">
		                		<label for="form_developer">Developer</label>
		                		<input type="text" name="edit_developer" class="form-control" id="edit_form_developer" required>
			            </div>
			            
			            <div class="form-group col-md-4">
		                		<label for="form_developer">Publisher</label>
		                		<input type="text" name="edit_publisher" class="form-control" id="edit_form_publisher" required>
			            </div>
			            
			            <div class="form-group col-md-4">
		                		<label for="form_genre">Release Date</label>
		                		<input type="date" name="edit_release" class="form-control" id="edit_form_release" required>
			            </div>
		        		</div>
		        		
		        		<div class="form-row">
		        			<div class="form-group col-md-6">
		                		<label for="form_front">Front Cover</label>
		                		<input type="text" name="edit_front" class="form-control" id="edit_form_front">
			            </div>
			            
			            <div class="form-group col-md-6">
		                		<label for="form_back">Back Cover</label>
		                		<input type="text" name="edit_back" class="form-control" id="edit_form_back">
			            </div>
		        		</div>
		        		
		        		<div class="form-row">
		        			<div class="form-group col-md-3">
		                		<label for="form_price">Price ($)</label>
		                		<input type="number" step=".01" name="edit_price" class="form-control" id="edit_form_price" required>
			            </div>
			            
			            <div class="form-group col-md-3">
		                		<label for="form_discount">Discount ($)</label>
		                		<input type="number" step=".01" name="edit_discount" class="form-control" id="edit_form_discount" required>
			            </div>
		        		</div>
		        </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        <button type="submit" class="btn btn-primary">Edit Game</button>
		      </div>
	      </form>
	    </div>
	  </div>
	</div>
</body>
<jsp:include page="../WEB-INF/shared/admin/footer.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#editGameModal').modal({
	        keyboard: true,
	        backdrop: "static",
	        show:false
	    }).on('show', function(){});
		
		$("#gameInventory").find('tr[data-id]').on('click', function () {
			
			var gameId = $(this).find('td.rowGameId').text();
			$('#editGame').html(gameId);
			$('#edit_form_id').val(gameId);
			
	        var gameTitle = $(this).find('td.rowGameTitle').text();
			$('#edit_form_title').val(gameTitle);
			
			var gameConsole = $(this).find('td.rowGameConsole').text();
			$('#edit_form_console').val(gameConsole);
			
			var gameDescription = $(this).find('td.rowGameDescription').text();
			$('#edit_form_description').val(gameDescription);
			
			var gamePlayers = $(this).find('td.rowGamePlayers').text();
			$('#edit_form_players').val(gamePlayers);
			
			var gameCoop = $(this).find('td.rowGameCoop').text();
			var gameCoopBool = gameCoop == 'true' ? 'Yes' : 'No';
			$('#edit_form_coop').val(gameCoopBool);
			
			var gameGenre = $(this).find('td.rowGameGenre').text();
			$('#edit_form_genre').val(gameGenre);
			
			var gameDeveloper = $(this).find('td.rowGameDeveloper').text();
			$('#edit_form_developer').val(gameDeveloper);
			
			var gamePublisher = $(this).find('td.rowGamePublisher').text();
			$('#edit_form_publisher').val(gamePublisher);
			
			var gameRelease = $(this).find('td.rowGameRelease').text();
			$('#edit_form_release').val(gameRelease);
			
			var gameFront = $(this).find('img.rowGameFront').attr('src');
			$('#edit_form_front').val(gameFront);
			
			var gameBack = $(this).find('img.rowGameBack').attr('src');
			$('#edit_form_back').val(gameBack);
			
			var gamePrice = $(this).find('td.rowGamePrice').text();
			$('#edit_form_price').val(gamePrice);
			
			var gameDiscount = $(this).find('td.rowGameDiscount').text();
			$('#edit_form_discount').val(gameDiscount);

	        $('#editGameModal').modal('show');
	    });
	});
</script>
</html>