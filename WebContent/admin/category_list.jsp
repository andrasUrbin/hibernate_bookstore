<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Manage Categories - Bookstore Admin Page</title>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<h2>Category Management</h2>
		<a href="category_form.jsp">Create New Category</a>
	</div>

	<div align="center">
		<h4>${message}</h4>		
	</div>

	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="category" items="${listCategory}" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${category.categoryId}</td>
				<td>${category.name}</td>
				<td>
					<a href="edit_category?id=${category.categoryId}">Edit</a>
					<a href="javascript:confirmDelete(${category.categoryId})">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp"/>
	<script type="text/javascript">
		function confirmDelete(categoryId){
			if(confirm("Are you sure you want to delete the category with the ID of " + categoryId + "?")){
				window.location = "delete_category?id=" + categoryId;
			}
		}
	</script>
</body>
</html>