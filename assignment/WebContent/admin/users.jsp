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
				Users <span>- ${fn:length(users)} rows</span>
			</h5>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>Name</th>
							<th>Last Login</th>
							<th>Purchase History</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<tr>
								<td>${user.userid}</td>
								<td>${user.firstName} ${user.lastName}</td>
								<td>${user.lastLogin}</td>
								<td>Orders</td>
								<td>
									<c:choose>
										<c:when test="${user.locked}">
											<a href="#" data-id="${user.userid}"
												class="badge badge-success badge-unblock">Unblock User</a>
										</c:when>
										<c:otherwise>
											<a href="#" data-id="${user.userid}"
												class="badge badge-danger badge-block">Block User</a>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</main>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$(".badge-block").click(function(e) {
			e.preventDefault();
			var userId = e.target.dataset.id;
			$.ajax({
				url : "/app/admin/users/" + userId,
				type : "PUT",
				contentType : 'application/json',
				data : JSON.stringify({
					'locked' : 1
				}),
				success : function() {
					console.log('success');
					window.location = "/app/admin/users";
				},
				error : function(e) {
					alert(JSON.stringify(e))
				}
			});
		});

		$(".badge-unblock").click(function(e) {
			e.preventDefault();
			var userId = e.target.dataset.id;
			$.ajax({
				url : "/app/admin/users/" + userId,
				type : "PUT",
				contentType : 'application/json',
				data : JSON.stringify({
					'locked' : 0
				}),
				success : function() {
					console.log('success');
					window.location = "/app/admin/users";
				},
				error : function(e) {
					alert(JSON.stringify(e))
				}
			});
		});
	});
</script>
</html>