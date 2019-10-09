<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>Edit Profile - Bookstore</title>
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<h2>
		    Edit Your Profile
		</h2>
	</div>
	<div align="center">
        <form action="update_profile" method="post" id="customerForm">
            <input type="hidden" id="customerId" name="customerId" value="${loggedCustomer.customerId}">
        	<table class="form">
        		<tr>
        			<td align="right">Email:</td>
        			<td align="left">${loggedCustomer.email} This cannot be changed!</td>
        		</tr>
        		<tr>
        			<td align="right">Full Name:</td>
        			<td align="left"><input type="text" id="fullName" name="fullName" size="50" value="${loggedCustomer.fullname}"></td>
        		</tr>
                <tr>
                    <td align="right">Phone Number:</td>
                    <td align="left"><input type="text" id="phone" name="phone" size="20" value="${loggedCustomer.phone}"></td>
                </tr>
                <tr>
                    <td align="right">Address:</td>
                    <td align="left"><input type="text" id="address" name="address" size="128" value="${loggedCustomer.address}"></td>
                </tr>
                <tr>
                    <td align="right">City:</td>
                    <td align="left"><input type="text" id="city" name="city" size="50" value="${loggedCustomer.city}"></td>
                </tr>
                <tr>
                    <td align="right">Zip Code:</td>
                    <td align="left"><input type="text" id="zipCode" name="zipCode" size="10" value="${loggedCustomer.zipcode}"></td>
                </tr>
                <tr>
                    <td align="right">Country:</td>
                    <td align="left"><input type="text" id="country" name="country" size="50" value="${loggedCustomer.country}"></td>
                </tr>
                <tr>
                    <h3>Leave the password fields empty, if you don't want to change it!</h3>
                    <td align="right">Password:</td>
                    <td align="left"><input type="password" id="password" name="password" size="50"></td>
                </tr>
                <tr>
                    <td align="right">Confirm Password:</td>
                    <td align="left"><input type="password" id="confirmPassword" name="confirmPassword" size="50"></td>
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
                confirmPassword: {
                    equalTo: "#password"
                }
            },

            messages: {
                confirmPassword: {
                    equalTo: "The two passwords don't match!"
                }
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