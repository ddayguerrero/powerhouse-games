<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<jsp:include page="./WEB-INF/shared/head.html" />  
<body>
	<div class="container">
	    <div class="form-container">
	        <div class="h5">
	            Register
	        </div>
	        <form action="http://localhost:8080/app/user" method="POST">
	            <div class="form-group">
	                <label for="form_firstname">First Name</label>
	                <input type="text" name="firstname" class="form-control" id="form_firstname">
	            </div>
	            <div class="form-group">
	                <label for="form_lastname">Last Name</label>
	                <input type="text" name="lastname" class="form-control" id="form_lastname">
	            </div>
	            <div class="form-group">
	                <label for="form_email">Email</label>
	                <input type="email" name="email" class="form-control" id="form_email">
	            </div>
	            <div class="form-group">
	                <label for="form_address1">Address 1</label>
	                <input type="text" name="address1" class="form-control" id="form_address1">
	            </div>
	            <div class="form-group">
	                <label for="form_address2">Address 2</label>
	                <input type="text" name="address2" class="form-control" id="form_address2">
	            </div>
	            <div class="form-group">
	                <label for="form_city">City</label>
	                <input type="text" name="city" class="form-control" id="form_city">
	            </div>
	            <div class="form-group">
	                <label for="form_province">Province</label>
	                <input type="text" name="province" class="form-control" id="form_province">
	            </div>
	            <div class="form-group">
	                <label for="postal_code">Postal Code</label>
	                <input type="text" name="postal_code" class="form-control" id="form_postal_code">
	            </div>
	            <div class="form-group">
	                <label for="form_country">Country</label>
	                <input type="text" name="country" class="form-control" id="form_country">
	            </div>
	            <div class="form-group">
	                <label for="form_password">Password</label>
	                <input type="password" name="password" class="form-control" id="form_password">
	            </div>
	            <button class="btn btn-block btn-primary" type="submit">Register</button>
	        </form>
	    </div>
	</div>
</body>
<jsp:include page="./WEB-INF/shared/footer.html" />
</html>