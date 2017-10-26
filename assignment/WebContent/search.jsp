<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<jsp:include page="./WEB-INF/shared/head.html" />
</head>
<jsp:include page="./WEB-INF/shared/nav.jsp" />    
<body>
<div class="search">
    <div class="search-box">
    		<div class="text-center mb-4">
    			
    		</div>
        <form action="http://localhost:8080/app/game" method="GET">
            <div class="input-group">
                <input type="text" class="form-control" name="title" placeholder="Enter game title...">
                <span class="input-group-btn">
                    <button class="btn btn-primary" type="submit">Search</button>
                </span>
            </div>
        </form>
    </div>
</div>

</body>
<jsp:include page="./WEB-INF/shared/footer.html" />
</html>