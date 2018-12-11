<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<jsp:include page="head.jsp">
	<jsp:param name="title" value="Cart Overview" />
</jsp:include>

<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Cart Overview" />
		</jsp:include>
		<main>
			<%@include file="errors.jsp" %>
			<c:choose>
				<c:when test="${sessionScope.cart.productsOrdered eq null}">
					<p>There are no orders in your cart yet.</p>
				</c:when>
				<c:otherwise>
					<table>
						<thead>
							<tr>
								<th>ID</th>
								<th>Description</th>
								<th>Price/Unit</th>
								<th>Quantity</th>
								<th>Total Price</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="order" items="${sessionScope.cart.productsOrdered}" >
								<tr>
									<td><c:out value="${order.product.id}"/></td>
									<td><c:out value="${order.product.description}"/></td>
									<td><c:out value="${order.product.price}"/></td>
									<td>
										<input type="number" class="inline-form" name="quantity" value="<c:out value="${order.quantity}"/>">
									</td>
									<td>${order.totalPrice}</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td><strong>Total:</strong></td>
								<td><strong>${sessionScope.cart.totalPrice}</strong></td>
							</tr>
						</tfoot>
						<caption>Cart Overview</caption>
					</table>
				</c:otherwise>
			</c:choose>
		</main>
		
		<jsp:include page="footer.jsp">
			<jsp:param name="currentPage" value="cartOverview" />
		</jsp:include>
	</div>
</body>
</html>