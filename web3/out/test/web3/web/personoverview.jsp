<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<jsp:include page="head.jsp">
    <jsp:param name="title" value="User Overview" />
</jsp:include>

<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="User Overview" />
    </jsp:include>

    <main>
        <%@include file="errors.jsp" %>

        <table>
            <thead>
            <tr>
                <th>E-mail</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}" >
                <tr>
                    <td><c:out value="${user.person.email}"/></td>
                    <td><c:out value="${user.person.firstName}"/></td>
                    <td><c:out value="${user.person.lastName}"/></td>
                    <td><a href="Controller?action=userConfirmDelete&id=<c:out value="${user.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
            <caption>User Overview</caption>
        </table>
    </main>

    <jsp:include page="footer.jsp">
        <jsp:param name="currentPage" value="userOverview" />
    </jsp:include>
</div>
</body>
</html>