<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<h1>Please login to see the content!</h1>
		<form>
			Email: <input type="text" name="email" size="10"><br>
			Password: <input type="password" name="password" size="10"><br>
			<input type="submit" name="Login">
		</form>
	</div>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>