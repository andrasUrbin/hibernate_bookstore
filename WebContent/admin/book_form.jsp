<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>Manage Books - Bookstore Admin Page</title>
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<h2>
		    <c:if test="${book != null}">
		        Edit Book
		    </c:if>
		    <c:if test="${book == null}">
                Create Book
            </c:if>
		</h2>
	</div>
	<div align="center">
		<c:if test="${book != null}">
	        <form action="update_book" method="post" id="bookForm" enctype="multipart/form-data">
	        <input type="hidden" id="bookId" name="bookId" value="${book.bookId}">
	    </c:if>
	    <c:if test="${book == null}">
            <form action="create_book" method="post" id="bookForm" enctype="multipart/form-data">
        </c:if>
        	<table>
        		<tr>
                   <td align="right">Category:</td>
            		<td align="left">
                        <select name="category">
                            <c:forEach items="${listCategory}" var="category">
                                <c:if test="${category.categoryId eq book.category.categoryId}">
                                    <option value="${category.categoryId}" selected></option>
                                </c:if>
                                <c:if test="${category.categoryId ne book.category.categoryId}">
                                    <option value="${category.categoryId}">
                                </c:if>
                                ${category.name}
                                </option>
                            </c:forEach>
                        </select>      
                    </td>
        		</tr>
        		<tr>
        			<td align="right">Title:</td>
        			<td align="left"><input type="text" id="title" name="title" size="20" value="${book.title}"></td>
        		</tr>
        		<tr>
        			<td align="right">Author:</td>
        			<td align="left"><input type="text" id="author" name="author" size="20" value="${book.author}"></td>
        		</tr>
                <tr>
                    <td align="right">ISBN:</td>
                    <td align="left"><input type="text" id="isbn" name="isbn" size="20" value="${book.isbn}"></td>
                </tr>
                <tr>
                    <td align="right">Publish Date:</td>
                    <td align="left"><input type="text" id="publishDate" name="publishDate" size="20" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${book.publishDate}'/>"></td>
                </tr>
                <tr>
                    <td align="right">Book Image:</td>
                    <td align="left">
                        <input type="file" id="bookImage" name="bookImage" size="20"><br>
                        <img id="thumbnail" alt="Image Preview" style="width:20%; margin-top: 10px;"src="data:image/jpg;base64,${book.base64Image}">
                    </td>
                </tr>
                <tr>
                    <td align="right">Price:</td>
                    <td align="left"><input type="text" id="price" name="price" size="20" value="${book.price}"></td>
                </tr>
                <tr>
                    <td align="right">Description:</td>
                    <td align="left">
                        <textarea rows="5" cols="50" id="description" name="description">${book.description}</textarea>
                    </td>
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
        $("#bookImage").change(function(){
            showImageThumbnail(this);
        });
        $("#bookForm").validate({
            rules: {
                category: "required",
                title: "required",
                author: "required",
                isbn: "required",
                publishDate: "required",
                bookImage: "required",
                price: "required",
                description: "required"
            },

            messages: {
                category: "Please select a category for the book!",
                title: "Please enter a title for the book!",
                author: "Please enter an author for the book!",
                isbn: "Please enter an ISBN for the book!",
                publishDate: "Please enter a publish date for the book!",
                bookImage: "Please select an image for the book!",
                price: "Please enter a price for the book!",
                description: "Please enter a description for the book!",
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