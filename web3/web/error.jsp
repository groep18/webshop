<%--
  Created by IntelliJ IDEA.
  User: NCMN
  Date: 11/10/2018
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<header>
    <h1><span>Web shop</span></h1>
    <nav>
        <ul>
            <li><a href="Controller">Home</a></li>
            <li><a href="Controller?action=products">Products</a></li>
            <li><a href="Controller?action=overview">Users</a></li>
            <li><a href="../../../Webshop/web/signUp.jsp">Sign up</a></li>
        </ul>
    </nav>
    <h2>
        ErrorList
    </h2>
    <c:forEach var="error" items="${errorlist.getAll()}">
        <li>${error.getMassage()}</li>
    </c:forEach>
</header>
</body>
</html>
