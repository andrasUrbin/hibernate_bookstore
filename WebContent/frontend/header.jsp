<div>
	<div align="center">
		<img src="images/bookstorelogo.jpeg">
	</div>
	<div align="center">
		<input type="text" name="keyword" size="50">
		<input type="button" name="Search">
		<a href="Login">Sign In</a>
		<a href="register">Register</a>
		<a href="view_cart">Cart</a>
	</div>
	<div>&nbsp;</div>
	<div align="center">
		<c:forEach var="category" items="${listCategory}">
			<a href="view_category?id=${category.categoryId}">
				<b><c:out value="${category.name}"/></b>
			</a>
			<c:if test="${not status.last}">
			&nbsp; | &nbsp;
			</c:if>
		</c:forEach>
	</div>
</div>
