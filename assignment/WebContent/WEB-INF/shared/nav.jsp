<%@ page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">
	<a class="navbar-brand" href="#">Power House Games</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarCollapse" aria-controls="navbarCollapse"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarCollapse">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="/app/search.jsp">Home</a></li>
			<li class="nav-item"><a class="nav-link" href="/app/search.jsp">Search</a></li>
			<% if(session != null && session.getAttribute("email") != null) { %>
			<li class="nav-item"><a class="nav-link" href="/app/specials">Specials</a></li>
			<li class="nav-item"><a class="nav-link" href="/app/favorites">Profile</a></li>
			<% } %>
		</ul>
		<ul class="navbar-nav">
			<% if(session != null && session.getAttribute("email") != null) { %>
				<li class="nav-item"><span class="nav-link">Greetings, <c:out value="${sessionScope.firstname}"/></span></li>
				<li class="nav-item"><a id="nav-logout" class="nav-link">Logout</a></li>
			<% } else { %>
				<li class="nav-item"><a class="nav-link" href="/app/login">Login</a></li>
				<li class="nav-item"><a class="nav-link" href="/app/register">Register Now!</a></li>
			<% } %>
			
			<% if(session != null && session.getAttribute("cart") != null) { %>
			<li class="nav-item">
			  <a class="nav-link" href="/app/cart"><span class="oi oi-cart" title="cart" aria-hidden="true"></span> <c:out value="${sessionScope.cart.getCartSize()}"/> item(s)</a>
			</li>
			<% } else { %>
			<li class="nav-item">
			  <a class="nav-link" href=""><span class="oi oi-cart" title="cart" aria-hidden="true"></span> My Cart </a>
			</li>
			<% } %>
		</ul>
	</div>
</nav>
				<c:forEach items="${sessionScope}" var="attr">
			    ${attr.key}=${attr.value}<br>
			</c:forEach>
<script type="text/javascript" >
$(document).ready(function() {
    $("#nav-logout").click(function(e) {
    		e.preventDefault();
    		$.ajax({
    			url:"/app/logout",
    			type: "POST",
    			contentType: false,
    			cache: false,
    			processData: false,
    			success: function(){
    				 window.location="/app/login.jsp";
    			},
    			error: function(){}
 			});
    });
});
</script>