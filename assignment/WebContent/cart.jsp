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
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cart.getItems()}" var="game">
							<tr>
								<td>${game.getItem().gameid}</td>
								<td><a href="/app/game?id=<c:out value="${game.getItem().gameid}"/>">${game.getItem().gameTitle}</a></td>
								<td> 
									<a class="updateQuantity" data-id="${game.getItem().gameid}" data-action="0" href="#"> - </a>
									${game.getQuantity()} 
									<a class="updateQuantity" data-id="${game.getItem().gameid}" data-action="1" href="#"> + </a>
								</td>
								<td>$ ${game.getItem().price}</td>
								<td>$ ${game.getTotal()}</td>
								<td>
									<a id="removeItem" data-id="${game.getItem().gameid}" href="#"> Remove </a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5"></td>
							<td>	<b>Sub-total:</b> $ <c:out value="${cart.getSubTotal()}"/></td>
						</tr>
					</tfoot>
				</table>
				<c:choose>
					<c:when test="${cart.getCartSize() > 0}">
						<a role="button" class="btn btn-primary btn-lg" href="/app/checkout.jsp"> Go To Checkout</a>
					</c:when>
					<c:otherwise>
						<a role="button" class="btn btn-primary btn-lg disabled" href="/app/checkout"> Go To Checkout</a>
					</c:otherwise> 
				</c:choose>
			</div>			
			</main>
			<div class="col-sm-2 col-md-2"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#removeItem").click(function(e) {
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
					console.log('delete success');
					window.location = "/app/cart";
				},
				error : function(e) {
					alert(JSON.stringify(e))
				}
			});
		});
		
		$(".updateQuantity").click(function(e) {
			e.preventDefault();
			var itemId = e.target.dataset.id;
			var action = e.target.dataset.action;
			$.ajax({
				url : "/app/cart/item",
				type : "PUT",
				contentType : 'application/json',
				data : JSON.stringify({
					'itemId' : itemId,
					'action' : action
				}),
				success : function() {
					console.log('update success');
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