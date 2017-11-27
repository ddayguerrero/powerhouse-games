<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../WEB-INF/shared/head.jsp" />
<link rel="stylesheet" href="../stylesheets/login.css">
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
				Order History for <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/>
			</h5>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Invoice #</th>
							<th>Date</th>
							<th>Sub-total</th>
							<th>Tax</th>
							<th>Total</th>
							<th>Details</th>	
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${invoices}" var="invoice">
							<tr>
								<td>${invoice.id}</td>
								<td>${invoice.date}</td>
								<td>$${invoice.subTotal}</td>
								<td>$${invoice.tax}</td>
								<td>$${invoice.total}</td>
								<td><a class="viewDetails" href="" data-href="/app/admin/details?invoice=<c:out value="${invoice.id}"/>">View</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</main>
		</div>
	</div>
	<div id="myModal" class="modal" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="invoice">Invoice Details - Order #<span id="invoiceNumber"></span></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <table class="table table-hover">
			  <thead>
			    <tr>
			      <th scope="col">Game Id</th>
			      <th scope="col">Quantity</th>
			      <th scope="col">Price</th>
			    </tr>
			  </thead>
			  <tbody id="invoiceDetailsBody">
			  </tbody>
			</table>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
</body>
<jsp:include page="../WEB-INF/shared/admin/footer.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		$(".viewDetails").click(function(e) {
			e.preventDefault();
			var href = e.target.dataset.href;
			console.log(href);
			$("#invoiceDetailsBody").empty();
			$.ajax({
				url : href,
				contentType : 'application/json',
				type : "GET",
				success : function(res) {
					console.log(res);
					$.each(res, function(key,value){
						$("#invoiceNumber").html(value["invoiceId"]);
						var tableRow = $("<tr></tr>");
						var idRow = $("<td></td>").text(value["gameId"]);
						var quantityRow = $("<td></td>").text(value["quantity"]);
						var priceRow = $("<td></td>").text(value["gamePrice"]);
						tableRow.html(idRow);
						tableRow.append(quantityRow);
						tableRow.append(priceRow);
						$("#invoiceDetailsBody").append(tableRow);
					})
					$("#myModal").modal('toggle');
				},
				error : function(e) {
					alert(JSON.stringify(e))
				}
			});
		});
	});
</script>
</html>