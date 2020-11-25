<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>
<title>CRM - Login</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body style="background-color:#ededed;">
	<div style="background-color:#337ab7;height:50px;"></div>
	<div class="container-fluid">
		<div class="row col-lg-4 col-lg-offset-4" style="margin-top: 80px;background-color:#fff;padding:20px;border:solid 1px #ddd;">
			<!-- <img th:src="@{/images/login.jpg}" class="img-responsive center-block" width="300" height="300" alt="Logo" /> -->
			<form action="/login" method="POST" class="form-signin">
				<h3 class="form-signin-heading">Login</h3>
				<br /> <input type="text" id="email" name="email"  class="form-control" /> <br /> <input type="password"  id="password" name="password" class="form-control" /> <br />
				<c:if test="${param.error!=null}">
				<div align="center">
					<p style="font-size: 20; color: #FF1C19;">Email or Password is invalid.</p>
				</div>
				</c:if>
				<button class="btn btn-lg btn-primary" name="Submit" value="Login" type="Submit" style="margin-right:10px;">Login</button>
				<a href="/recover-password">Forgot password?</a>
			</form>
		</div>
	</div>
</body>
</html>