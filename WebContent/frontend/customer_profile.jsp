<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>Manage Profile - Bookstore User Profile Page</title>
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<h2>Customer Profile</h2>
	</div>

	<div align="center">
		<h4>${message}</h4>
	</div>

	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>ID</th>
				<th>Email</th>
				<th>Full Name</th>
				<th>Address</th>
				<th>City</th>
				<th>Zip Code</th>
				<th>Country</th>
				<th>Phone Number</th>
			</tr>
			<tr>
				<td>${loggedCustomer.customerId}</td>
				<td>${loggedCustomer.email}</td>
				<td>${loggedCustomer.fullname}</td>
				<td>${loggedCustomer.address}</td>
				<td>${loggedCustomer.city}</td>
				<td>${loggedCustomer.zipcode}</td>
				<td>${loggedCustomer.country}</td>
				<td>${loggedCustomer.phone}</td>
				<td>
					<a href="edit_profile">Edit My Profile</a>
				</td>
			</tr>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>