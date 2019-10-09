<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Customer Login</title>
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<div align="center">
		<h2>Customer login</h2>

		<c:if test="${message != null}">
			<div align="center">
				<h4 class="message">${message}</h4>		
			</div>
		</c:if>

		<form id="loginForm" action="login" method="post">
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" id="email" size="20"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="password" size="20"></td>
				</tr>
				<tr>
					<td><input type="submit" name="submit" value="Login"></td>
					<td><input type="button" value="Cancel" id="buttonCancel"></td>

				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#loginForm").validate({
			rules: {
				email: "required",
				password: "required",
			},

			messages: {
				email: "Please enter your email address!",
				password: "Please enter your password!",
			}
		});

		$("#buttonCancel").click(function(){
			history.go(-1);
		});
	});
</script>
</body>
</html>