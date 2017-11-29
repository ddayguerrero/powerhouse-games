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
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="form_firstname">First Name</label> <input type="text"
						name="firstname" class="form-control" id="form_firstname" value="${user.firstName}" required>
				</div>
				<div class="form-group col-md-6">
					<label for="form_lastname">Last Name</label> <input type="text"
						name="lastname" class="form-control" id="form_lastname" value="${user.lastName}" required>
				</div>
			</div>
			<div class="form-group">
				<label for="form_email">Email</label> <input type="email"
					name="email" class="form-control" id="form_email" value="${user.email}" required>
			</div>
			<div class="form-group">
				<label for="form_address1">Address 1</label> <input type="text"
					name="address1" class="form-control" id="form_address1" value="${user.address1}" required>
			</div>
			<div class="form-group">
				<label for="form_address2">Address 2</label> <input type="text"
					name="address2" class="form-control" id="form_address2" value="${user.address2}">
			</div>
			<div class="form-row">
				<div class="form-group col-md-8">
					<label for="form_city">City</label> <input type="text" name="city"
						class="form-control" id="form_city" value="${user.city}">
				</div>
				<div class="form-group col-md-4">
					<label for="form_province">Province</label> <input type="text"
						name="province" class="form-control" id="form_province" value="${user.province}">
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-8">
					<label for="form_country">Country</label> <input type="text"
						name="country" class="form-control" id="form_country" value="${user.country}">
				</div>
				<div class="form-group col-md-4">
					<label for="postal_code">Postal Code</label> <input type="text"
						name="postal_code" class="form-control" id="form_postal_code" value="${user.postalCode}">
				</div>
			</div>
			<button id="updateProfile" data-id="${user.userid}" class="btn btn-block btn-primary"> Save
				Changes</button>
	</div>


	<div class="game-container">
		<div class="game-header">
			<div class="game-header-title">
				<h2>Favorites</h2>
			</div>
		</div>
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
					</div></li>
			</c:forEach>
		</ul>
	</div>

</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#updateProfile").click(function(e) {
			e.preventDefault();
			var fname = $("#form_firstname").val();
			var lname = $("#form_lastname").val();
			var email = $("#form_email").val();
			var address1 = $("#form_address1").val();
			var address2 = $("#form_address2").val();
			var city = $("#form_city").val();
			var province = $("#form_province").val();
			var country = $("#form_country").val();
			var postalCode = $("#form_postal_code").val();		
			var userId = e.target.dataset.id;
			
			$.ajax({
				url : "/app/user",
				type : "PUT",
				contentType : 'application/json',
				data : JSON.stringify({
					'id' : userId,
					'fname' : fname,
					'lname' : lname,
					'email' : email,
					'address1' : address1,
					'address2' : address2,
					'city' : city,
					'province' : province,
					'country': country,
					'postalCode': postalCode
				}),
				success : function() {
					console.log('Update User Success');
					//window.location = "/app/favorites";
				},
				error : function(e) {
					alert(JSON.stringify(e))
				}
			});
		});
	});
</script>
<jsp:include page="./WEB-INF/shared/footer.jsp" />
</html>