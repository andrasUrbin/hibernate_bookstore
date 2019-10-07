<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>Customer Registration - Bookstore Admin Page</title>
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<h2>
		    Register as a Customer!
		</h2>
	</div>
	<div align="center">
        <form action="register_customer" method="post" id="customerForm">
        	<table class="form">
        		<tr>
        			<td align="right">Email:</td>
        			<td align="left"><input type="text" id="email" name="email" size="50"></td>
        		</tr>
        		<tr>
        			<td align="right">Full Name:</td>
        			<td align="left"><input type="text" id="fullName" name="fullName" size="50"></td>
        		</tr>
                <tr>
                    <td align="right">Password:</td>
                    <td align="left"><input type="password" id="password" name="password" size="50"></td>
                </tr>
                <tr>
                    <td align="right">Confirm Password:</td>
                    <td align="left"><input type="password" id="confirmPassword" name="confirmPassword" size="50"></td>
                </tr>
                <tr>
                    <td align="right">Phone Number:</td>
                    <td align="left"><input type="text" id="phone" name="phone" size="20"></td>
                </tr>
                <tr>
                    <td align="right">Address:</td>
                    <td align="left"><input type="text" id="address" name="address" size="128"></td>
                </tr>
                <tr>
                    <td align="right">City:</td>
                    <td align="left"><input type="text" id="city" name="city" size="50"></td>
                </tr>
                <tr>
                    <td align="right">Zip Code:</td>
                    <td align="left"><input type="text" id="zipCode" name="zipCode" size="10"></td>
                </tr>
                <tr>
                    <td align="right">Country:</td>
                    <td align="left"><input type="text" id="country" name="country" size="50"></td>
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
        $("#customerForm").validate({
            rules: {
                email: {
                    required: true,
                    email: true
                },
                fullName: "required",
                password: "required",
                confirmPassword: {
                    required: true,
                    equalTo: "#password"
                },
                phone: "required",
                address: "required",
                city: "required",
                zipCode: "required",
                country: "required",
            },

            messages: {
                email: {
                    required: "Please enter your email address!",
                    email: "Please enter a valid email address!"
                },

                fullName: "Please enter your full name!",
                password: "Please enter your password",
                confirmPassword: {
                    required: "Please confirm your password",
                    equalTo: "The two passwords don't match!"
                },

                phone: "Please enter your phone number!",
                address: "Please enter your address!",
                city: "Please enter your city!",
                zipCode: "Please enter your zip code!",
                country: "Please enter your country!",
            }
        });
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

    function showImageThumbnail(fileInput){
        var file = fileInput.files[0];
        var reader = new FileReader();
        
        reader.onload = function(e){
            $('#thumbnail').attr('src', e.target.result);
        };

        reader.readAsDataURL(file);
    }
</script>
</html>