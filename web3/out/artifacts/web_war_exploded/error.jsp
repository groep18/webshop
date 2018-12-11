<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isErrorPage="true" %>

<!DOCTYPE html>
<html>

<jsp:include page="head.jsp">
	<jsp:param name="title" value="Error" />
</jsp:include>

<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Error" />
		</jsp:include>
		
		<main>
			<p>Je hebt een error veroorzaakt!</p>
			<p>${pageContext.exception}</p>
		</main>
		
		<jsp:include page="footer.jsp">
			<jsp:param name="currentPage" value="Error" />
		</jsp:include>
		
	</div>
</body>
</html>