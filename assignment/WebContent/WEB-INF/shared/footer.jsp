<%@ page session="true" %>

<footer class="footer">
      <div class="container">
		<% if(session != null && session.getAttribute("last_login") != null) { %>
		<span class="text-muted">
			Last login:
			<%= session.getAttribute("last_login") %>
		</span>
		<% } else { %>
		
		<% } %>
      </div>
</footer>
 
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>