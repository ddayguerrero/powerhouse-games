<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.soen387.domain.Game" %>
<%@page import="org.soen387.datasource.mappers.GameMapper"%>
<%@page import="java.util.List"%>

<html>
<jsp:include page="./WEB-INF/shared/head.jsp" />
<link rel="stylesheet" href="./stylesheets/specials.css">
</head>
<jsp:include page="./WEB-INF/shared/nav.jsp" />
<body>
	<% if(session == null || session.getAttribute("email") == null) { %>
		<jsp:forward page="login.jsp"></jsp:forward>
	<% } %>
	
	<div class="specials-container">
		<div class="specials-header">
			<div class="specials-header-title">
				<h2>Power Specials</h2>
				<span> Week of October 23th 2017</span>
			</div>
		</div>
		<c:forEach begin="0" end="${numOfRows}" step="1">
	        <div class="card-deck" style="margin-bottom: 15px;">
	        		<c:forEach begin="0" end="${numOfCols}" step="1">
	        			<div class="card">
						<div class="card-body">
							<img class="" style="margin-bottom: 5px;" src="${games.get(index).front_box_art}" width="150" height="200" alt="Card image cap">
							<h5 class="card-title">${games.get(index).gameTitle}</h5>
							<p class="card-text">${games.get(index).console}</p>
							<h3 class="text-warning">$ ${games.get(index).getDiscountedPrice()}</h3>
							<form action="${pageContext.request.contextPath}/cart" method="post">
		                         <input type="hidden" name="gameId" value="${games.get(index).gameid}">
								<button type="submit" class="btn btn-primary">Add To Cart</button>
							</form>
						</div>
						<div class="card-footer">
							<small class="text-muted"> Regular price: $${games.get(index).price}</small>
						</div>
					</div>
					<c:set var="index" value="${index + 1}" scope="page"/>
	        		</c:forEach>
	        </div>
	    </c:forEach>
	</div>

</body>
<jsp:include page="./WEB-INF/shared/footer.jsp" />
</html>