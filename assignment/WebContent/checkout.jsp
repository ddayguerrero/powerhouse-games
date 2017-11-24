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
			<div class="table-responsive">
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
								<b>Taxes:</b> $ <c:out value="${cart.getSubTotal()}"/> <br/>
								<b>Total:</b> $ <c:out value="${cart.getSubTotal()}"/>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<h3>Payment</h3>
			<form class="form-horizontal" role="form">
				<fieldset>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="card-holder-name">Name
							on Card</label>
						<div class="col-sm-12">
							<input type="text" class="form-control" name="card-holder-name"
								id="card-holder-name" placeholder="Cardholder's Name">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="card-number">Card
							Number</label>
						<div class="col-sm-12">
							<input type="text" class="form-control" name="card-number"
								id="card-number" placeholder="Card Number">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="expiry-month">Expiration
							Date</label>
						<div class="col-sm-12">
							<div class="row">
								<div class="col-sm-3 col-xs-3">
									<select class="form-control" name="expiry-month"
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
									<select class="form-control" name="expiry-year">
										<option value="17">2017</option>
										<option value="18">2018</option>
										<option value="19">2019</option>
										<option value="20">2020</option>
										<option value="21">2021</option>
										<option value="22">2022</option>
										<option value="23">2023</option>
									</select>
								</div>
								<div class="col-sm-4 col-xs-4">
									<input type="text" class="form-control" name="cvv" id="cvv"
										placeholder="Security Code">
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-9">
							<button type="button" class="btn btn-success">Pay Now</button>
						</div>
					</div>
				</fieldset>
			</form>
			</main>
			<div class="col-sm-2 col-md-2"></div>
		</div>
	</div>
</body>
</html>