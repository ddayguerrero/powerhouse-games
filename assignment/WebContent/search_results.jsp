<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<jsp:include page="./WEB-INF/shared/head.html" />
</head>
<jsp:include page="./WEB-INF/shared/nav.jsp" />    
<body>

<ul class="list-unstyled">
	<c:forEach items="${games}" var="game">
	  <li class="media">
	    <img class="mr-3" height="150" height="170" src="${game.front_box_art}" alt="Generic placeholder image">
	    <div class="media-body">
	      <h5 class="mt-0 mb-1">${game.gameTitle}</h5>
    	      	<p> by ${game.developer} </p>
    	      	<p> ${game.console} </p>
	      	${game.price}
	    </div>
	  </li>
  	</c:forEach>
</ul>

</body>
<jsp:include page="./WEB-INF/shared/footer.html" />
</html>