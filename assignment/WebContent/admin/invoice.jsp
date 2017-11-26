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
				Orders History for <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/>
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
								<td>${invoice.subTotal}</td>
								<td>${invoice.tax}</td>
								<td>${invoice.total}</td>
								<td>View</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</main>
		</div>
	</div>
</body>
</html>