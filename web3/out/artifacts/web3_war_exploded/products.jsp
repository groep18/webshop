<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<jsp:include page="head.jsp">
    <jsp:param name="title" value="Product Overview" />
</jsp:include>

<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Product Overview" />
    </jsp:include>
    <main>
        <%@include file="errors.jsp" %>


        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th></th>
                <th></th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${products}" >
                <tr>
                    <td><a href="Controller?action=productFormUpdate&id=<c:out value="${product.id}"/>"><c:out value="${product.id}"/></a></td>
                    <td><c:out value="${product.name}"></c:out></td>
                    <td><c:out value="${product.description}"/></td>
                    <td><c:out value="${product.price}"/></td>
                    <td>
                        <form action="Controller?action=shopCartAdd" class="inline-form" method="post" id="shopCartAdd-<c:out value="${product.id}"/>" novalidate></form>
                    </td>
                    <td>
                        <button type="submit" name="id" value="<c:out value="${product.id}"/>" form="shopCartAdd-<c:out value="${product.id}"/>">Add To Cart</button>
                    </td>
                    <td><a href="Controller?action=productConfirmDelete&id=<c:out value="${product.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
            <caption>Product Overview</caption>
        </table>
    </main>

    <jsp:include page="footer.jsp">
        <jsp:param name="currentPage" value="productOverview" />
    </jsp:include>
</div>
</body>
</html>