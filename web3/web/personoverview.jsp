<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Overview</title>
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
                <li id="actual"><a href="Controller?action=overview">Users</a></li>
                <li><a href="signUp.jsp">Sign up</a></li>
            </ul>
        </nav>
        <h2>
            User Overview
        </h2>

    </header>
    <main>
        <table>
            <caption>Users Overview</caption>
            <tr>
                <th>E-mail</th>
                <th>First Name</th>
                <th>Last Name</th>
            </tr>
            <c:forEach var="person" items="${personDB}">
                <tr>
                    <td>${person.email}</td>
                    <td>${person.firstName}</td>
                    <td>${person.lastName}</td>
                </tr>
            </c:forEach>
        </table>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>