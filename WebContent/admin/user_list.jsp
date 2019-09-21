<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Manage Users - Bookstore Admin Page</title>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<h2>User management</h2>
		<a href="user_form.jsp">Create New User</a>
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
				<th>Actions</th>
			</tr>
			<c:forEach var="user" items="${listUsers}" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${user.userId}</td>
				<td>${user.email}</td>
				<td>${user.fullName}</td>
				<td>
					<a href="edit_user?id=${user.userId}">Edit</a>
					<a href="javascript:confirmDelete(${user.userId})">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp"/>
	<script type="text/javascript">
		function confirmDelete(userId){
			if(confirm("Are you sure you want to delete the user with the ID of " + userId + "?")){
				window.location = "delete_user?id=" + userId;
			}
		}
	</script>
</body>
</html>