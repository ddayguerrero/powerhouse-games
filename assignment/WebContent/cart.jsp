<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="./WEB-INF/shared/head.jsp" />
<link rel="stylesheet" href="./stylesheets/login.css">
<link rel="stylesheet" href="./stylesheets/sidebar.css">
</head>
<jsp:include page="./WEB-INF/shared/nav.jsp" />
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-2 col-md-2"></div>
			<main role="main" class="col-sm-8 ml-sm-auto col-md-8 pt-3">
			<h1>Shopping Cart</h1>
			
			<h5>
				# of item(s) : <c:out value="${cart.getCartSize()}"/>
			</h5>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>Title</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Total</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cart.getItems()}" var="game">
							<tr>
								<td>${game.getItem().gameid}</td>
								<td><a href="/app/game?id=<c:out value="${game.getItem().gameid}"/>">${game.getItem().gameTitle}</a></td>
								<td> - ${game.getQuantity()} + </td>
								<td>${game.getItem().price}</td>
								<td>${game.getTotal()}</td>
								<td>
									<a class="removeItem" data-id="${game.getItem().gameid}" href="#"> Remove </a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>			
			</main>
			<div class="col-sm-2 col-md-2"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$(".removeItem").click(function(e) {
			e.preventDefault();
			var itemId = e.target.dataset.id;
			$.ajax({
				url : "/app/cart/item",
				type : "DELETE",
				contentType : 'application/json',
				data : JSON.stringify({
					'itemId' : itemId
				}),
				success : function() {
					console.log('success');
					window.location = "/app/cart";
				},
				error : function(e) {
					alert(JSON.stringify(e))
				}
			});
		});
	});
</script>
</html>