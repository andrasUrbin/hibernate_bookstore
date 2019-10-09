<div>
	<div align="center">
		<img src="images/bookstorelogo.jpeg">
	</div>
	<div align="center">
		<form action="search" method="get">
			<input type="text" name="keyword" size="50">
			<input type="submit" name="Search">
			
			<c:if test="${loggedCustomer == null}">
				<a href="login">Sign In</a>
				<a href="register">Register</a>
			</c:if>
			<c:if test="${loggedCustomer != null}">
				<a href="view_profile">Welcome, ${loggedCustomer.fullname}</a>
				<a href="view_cart">My Cart</a>
				<a href="view_orders">My Orders</a>
				<a href="view_profile">View Profile</a>
				<a href="logout">Logout</a>
			</c:if>
		</form>
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
