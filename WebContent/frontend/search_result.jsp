<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<title>Results for ${keyword} - Bookstore Website</title>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>

	<div align="center">
		<h2>${category.name}</h2>
	</div>

	<div align="left" style="width: 80%; margin: 0 auto;">
		<c:if test="${fn:length(result) == 0}">
			<h2>No results found for "${keyword}"</h2>
		</c:if>
		<c:if test="${fn:length(result) > 0}">
			<h2>Results found for "${keyword}"</h2>
			<c:forEach items="${result}" var="book">
				<div>
					<div style="display: inline-block; margin: 20px; width: 20%;" align="left">
						<div align="left">
							<h2><a href="view_book?id=${book.bookId}">
								<img src="data:image/jpg;base64,${book.base64Image}" width="128" height="164">
							</a></h2>
						</div>
					</div>
					<div style="display: inline-block; margin: 20px; vertical-align: top; width: 70%;" align="left">
						<div>
							<a href="view_book?id=${book.bookId}"><b>${book.title}</b></a>
						</div>
						<div>Rating *****</div>
						<div><i>by ${book.author}</i></div>
						<div>
							<p>${fn:substring(book.description, 0, 100)}...</p>
						</div>
						<div style="display: inline-block; margin: 20px; vertical-align: top;">
							<h3>$${book.price}</h3>
							<h3><a href="#">Add To Cart</a></h3>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>

	<jsp:directive.include file="footer.jsp"/>
</body>
</html>