<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Manage Users - Bookstore Admin Page</title>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<h2>
		    <c:if test="${user != null}">
		        Edit User
		    </c:if>
		    <c:if test="${user == null}">
                Create User
            </c:if>
		</h2>
	</div>
	<div align="center">
		<c:if test="${user != null}">
	        <form action="update_user" method="post" onsubmit="return validateFormInput()">
	        <input type="hidden" name="userId" value="${user.userId}">
	    </c:if>
	    <c:if test="${user == null}">
            <form action="create_user" method="post" onsubmit="return validateFormInput()">
        </c:if>
        	<table>
        		<tr>
        			<td align="right">Email:</td>
        			<td align="left"><input type="text" id="email" name="email" size="20" value="${user.email}"></td>
        		</tr>
        		<tr>
        			<td align="right">Full Name:</td>
        			<td align="left"><input type="text" id="fullname" name="fullname" size="20" value="${user.fullName}"></td>
        		</tr>
        		<tr>
        			<td align="right">Password:</td>
        			<td align="left"><input type="password" id="password" name="password" size="20" value="${user.password}"></td>
        		</tr>
        		<tr><td>&nbsp;</td></tr>
        		<tr>
        			<td colspan="2" align="center">
        				<input type="submit" value="Save">
        				<input type="button" value="Cancel" onclick="javascript:history.go(-1);">
        			</td>
        		</tr>
        	</table>
        </form>
	</div>
	<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">
	function validateFormInput(){
		var emailField = document.getElementById("email");
		var fullNameField = document.getElementById("fullname");
		var passwordField = document.getElementById("password");

		if(emailField.value.length == 0){
			alert("Email is required!");
			emailField.focus();
			return false;
		}

		if(fullNameField.value.length == 0){
			alert("Full name is required!");
			fullNameField.focus();
			return false;
		}

		if(passwordField.value.length == 0){
			alert("Password is required!");
			passwordField.focus();
			return false;
		}

		return true;
	}
</script>
</html>