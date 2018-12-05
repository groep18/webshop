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
							<c:forEach var=""  >
								<tr>
									<td><c:out value=""/></td>
									<td><c:out value=""/></td>
									<td><c:out value=""/></td>
									<td>
										<input type="number" class="inline-form" name="quantity" value="<c:out value=""/>">
									</td>
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td>Total:</td>
								<td>/td>
							</tr>
						</tfoot>
						<caption>Cart Overview</caption>
					</table>
		</main>
		
		<jsp:include page="footer.jsp">
			<jsp:param name="currentPage" value="cartOverview" />
		</jsp:include>
	</div>
</body>
</html>