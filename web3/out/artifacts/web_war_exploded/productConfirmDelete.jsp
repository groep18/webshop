<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<jsp:include page="head.jsp">
	<jsp:param name="title" value="Confirm Delete Product" />
</jsp:include>

<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Confirm Delete Product" />
		</jsp:include>
		
		<main>
			<p>You are about to delete product "<c:out value="${product.description}"/>". Are you sure?</p>
			<form action="Controller?action=productDelete" method="post">
				<input type="hidden" name="id" value="<c:out value="${product.id}"/>" />
				<input id="deleteProduct" type="submit" value="Delete Product"/>
				<a href="Controller?action=productOverview">Cancel</a>
			</form>
		</main>
		
		<jsp:include page="footer.jsp">
			<jsp:param name="currentPage" value="productConfirmDelete" />
		</jsp:include>
	</div>
</body>
</html>