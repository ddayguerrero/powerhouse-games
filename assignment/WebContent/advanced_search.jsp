<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<jsp:include page="./WEB-INF/shared/head.jsp" />
<link rel="stylesheet" href="./stylesheets/advanced_search.css">
</head>
<jsp:include page="./WEB-INF/shared/nav.jsp" />
<body>
	<div class="search">
		<div class="search-box">
			<div class="h2">
	            Advanced Search
	        </div>
			<form action="http://localhost:8080/app/game/advanced" method="GET">
				<div class="form-group">
	                <label for="form_title">Title</label>
	                <input type="text" name="title" class="form-control" id="form_title">
	            </div>
	            <div class="form-group">
	                <label for="form_console">Console</label>
	                <select id="form_console" name="console" class="custom-select">
					  <option selected>Console</option>
					  <option value="Microsoft Xbox 360">Xbox 360</option>
					  <option value="Sony Playstation 3">Playstation 3</option>
					  <option value="Nintendo Wii">Wii</option>
					</select>
	            </div>
	            <div class="form-group">
	                <label for="form_year">Year</label>
	                <select id="form_year" name="year" class="custom-select">
					  <option selected>Year</option>
					  <option value="2015">2015</option>
					  <option value="2014">2014</option>
					  <option value="2013">2013</option>
					  <option value="2012">2012</option>
					  <option value="2011">2011</option>
					  <option value="2010">2010</option>
					  <option value="2009">2009</option>
					  <option value="2008">2008</option>
					  <option value="2007">2007</option>
					</select>
	            </div>
	            <div class="form-group">
	                <label for="form_genre">Genre</label>
	                <input type="text" name="genre" class="form-control" id="form_genre">
	            </div>
	            <div class="form-group">
	                <label for="form_publisher">Publisher</label>
	                <input type="text" name="publisher" class="form-control" id="form_publisher">
	            </div>
	            <button class="btn btn-block btn-secondary" type="submit">Search</button>
			</form>
		</div>
	</div>
</body>
<jsp:include page="./WEB-INF/shared/footer.html" />
</html>