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
			<h1>Checkout</h1>
			<h5>Order Summary</h5>
			<div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Title</th>
							<th>Quantity</th>
							<th>Total</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cart.getItems()}" var="game">
							<tr>
								<td><a href="/app/game?id=<c:out value="${game.getItem().gameid}"/>">${game.getItem().gameTitle}</a></td>
								<td> 
									${game.getQuantity()} 
								</td>
								<td>$ ${game.getTotal()}</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td>
								<b>Sub-total:</b> $ <c:out value="${cart.getSubTotal()}"/> <br/>
								<b>Taxes:</b> $ <c:out value="${cart.getCalculatedTaxes()}"/> <br/>
								<b>Total:</b> $ <c:out value="${cart.getGrandTotal()}"/>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<h5>Payment</h5>
			<hr>
			<form action="http://localhost:8080/app/checkout" method="POST" class="form-horizontal" role="form">
				<fieldset>
					<div class="form-group">
					<label class="col-sm-3 control-label" for="card-holder-name">Card Association </label>
							<div class="form-check">
						    <label class="form-check-label">
							    <input class="form-check-input" type="radio" name="association" id="exampleRadios1" value="0" checked>
							    Visa
							  </label>
							</div>
							<div class="form-check">
							  <label class="form-check-label">
							    <input class="form-check-input" type="radio" name="association" id="exampleRadios2" value="1">
							    Mastercard
							  </label>
							</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="card-holder-name">Name
							on Card</label>
						<div class="col-sm-12">
							<input type="text" class="form-control" name="cardholdername"
								id="cardholdername" placeholder="Cardholder's Name" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="card-number">Card
							Number</label>
						<div class="col-sm-12">
							<input type="text" class="form-control" name="cardnumber"
								id="cardnumber" placeholder="Card Number" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="expiry-month">Expiration
							Date</label>
						<div class="col-sm-12">
							<div class="row">
								<div class="col-sm-3 col-xs-3">
									<select class="form-control" name="expirymonth"
										id="expiry-month">
										<option value="01">Jan (01)</option>
										<option value="02">Feb (02)</option>
										<option value="03">Mar (03)</option>
										<option value="04">Apr (04)</option>
										<option value="05">May (05)</option>
										<option value="06">June (06)</option>
										<option value="07">July (07)</option>
										<option value="08">Aug (08)</option>
										<option value="09">Sep (09)</option>
										<option value="10">Oct (10)</option>
										<option value="11">Nov (11)</option>
										<option value="12">Dec (12)</option>
									</select>
								</div>
								<div class="col-sm-3 col-xs-3">
									<select class="form-control" name="expiryyear">
										<option value="2017">2017</option>
										<option value="2018">2018</option>
										<option value="2019">2019</option>
										<option value="2020">2020</option>
										<option value="2021">2021</option>
										<option value="2022">2022</option>
										<option value="2023">2023</option>
									</select>
								</div>
								<div class="col-sm-4 col-xs-4">
									<input type="text" class="form-control" name="cvv" id="cvv"
										placeholder="Security Code" required>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-9">
							<button type="submit" class="btn btn-success">Pay Now</button>
						</div>
					</div>
				</fieldset>
			</form>
			</main>
			<div class="col-sm-2 col-md-2"></div>
		</div>
		<div class="row">
		<div class="col-sm-2 col-md-2"></div>
		<div class="col-sm-8 col-md-8">
			<p class="text-danger">
				<c:out value="${requestScope.messages.get(\"error\")}"/>
			</p>
		</div>
		<div class="col-sm-2 col-md-2"></div>
	</div>
</body>
</html>