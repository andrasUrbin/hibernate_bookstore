<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>Bookstore HomePage</title>

</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<h3>${message}</h3>
	</div>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>