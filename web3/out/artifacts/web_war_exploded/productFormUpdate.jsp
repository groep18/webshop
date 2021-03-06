<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<jsp:include page="head.jsp">
	<jsp:param name="title" value="Update Product" />
</jsp:include>

<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Update Product" />
		</jsp:include>
		<main>
			<a href="Controller?action=productOverview">Back to product overview</a>
			
			<%@include file="errors.jsp" %>
			
			<form method="post" action="Controller?action=productUpdate&id=<c:out value="${param.id}"/>" novalidate="novalidate">
				<!-- novalidate in order to be able to run tests correctly -->
				<p>
					<label for="id">Product ID</label>
					<input type="text" id="id" name="id" required 
						value="<c:out value="${param.id}"/>" disabled>
					<input type="hidden" id="id" name="id" required value="<c:out value="${param.id}"/>">
				</p>
				<p>
					<label for="description">Description</label>
					<input type="text" id="description" name="description" required 
						value="<c:out value="${product.description != null ? product.description : ''}"/>" >
				</p>
				<p>
					<label for="price">Price</label>
					<input type="text" id="price" name="price" required 
						value="<c:out value="${product.price != null ? product.price : ''}"/>" >
				</p>
				<p>
					<input type="submit" id="updateProduct" value="Update Product">
				</p>
			</form>
		</main>
		
		<jsp:include page="footer.jsp">
			<jsp:param name="currentPage" value="productFormUpdate" />
		</jsp:include>
	</div>
</body>
</html>


