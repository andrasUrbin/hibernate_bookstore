<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>Manage Customers - Bookstore Admin Page</title>
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<h2>Customer management</h2>
		<a href="customer_form.jsp">Create New Customer</a>
	</div>

	<div align="center">
		<h4>${message}</h4>
	</div>

	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Email</th>
				<th>Full Name</th>
				<th>City</th>
				<th>Country</th>
				<th>Registration Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="customer" items="${listCustomer}" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${customer.customerId}</td>
				<td>${customer.email}</td>
				<td>${customer.fullname}</td>
				<td>${customer.city}</td>
				<td>${customer.country}</td>
				<td>${customer.registerDate}</td>				
				<td>
					<a href="edit_customer?id=${customer.customerId}">Edit</a>
					<a href="javascript:void(0);" class="deleteLink" id="${customer.customerId}">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp"/>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".deleteLink").each(function(){
				$(this).on("click", function(){
					customerId = $(this).attr("id");
					if(confirm("Are you sure you want to delete the book with the ID of " + customerId + "?")){
					window.location = "delete_customer?id=" + customerId;
					}
				});
			});
		});
	</script>
</body>
</html>