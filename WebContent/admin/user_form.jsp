<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Manage Users - Bookstore Admin Page</title>
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
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
	        <form action="update_user" method="post" id="userForm">
	        <input type="hidden" name="userId" value="${user.userId}">
	    </c:if>
	    <c:if test="${user == null}">
            <form action="create_user" method="post" id="userForm">
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
        				<input type="button" value="Cancel" id="buttonCancel">
        			</td>
        		</tr>
        	</table>
        </form>
	</div>
	<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#userForm").validate({
            rules: {
                email: {
                    required: true,
                    email: true
                },

                fullname: "required",

                <c:if test="${user == null}">
                password: "required"
                </c:if>
            },

            messages: {
                email: {
                    required: "Please enter email",
                    email: "Please enter an valid email address"
                },

                fullname: "Please enter full name",

                <c:if test="${user == null}">
                password: "Please enter password"
                </c:if>
            }
        });

        $("#buttonCancel").click(function() {
            history.go(-1);
        });
	});
</script>
</html>