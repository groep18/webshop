<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li><a href="Controller?action=products">Products</a></li>
                <li><a href="Controller?action=overview">Users</a></li>
                <li id="actual"><a href="signUp.jsp">Sign up</a></li>
            </ul>
        </nav>
        <h2>
            Sign Up
        </h2>
    </header>
    <main>

        <c:if test="${errorlist != null && errorlist.size() != 0}">
            <div class="alert-danger">
                <ul>
                    <c:forEach var="error" items="${errorlist}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>


        <form method="post" action="Controller?action=signUpAction" novalidate>
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="userid">User id</label><input type="text" id="userid" name="userid"
                                                         required value=""></p>
            <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                                                               required value=""></p>
            <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
                                                             required value=""></p>
            <p><label for="email">Email</label><input type="email" id="email" name="email" required value=""></p>
            <p><label for="password">Password</label><input type="password" id="password" name="password"
                                                            required></p>
            <p><input type="submit" id="signUp" value="Sign Up"></p>

        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
