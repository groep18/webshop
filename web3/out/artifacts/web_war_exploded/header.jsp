<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
	<h1>
		<span>Web shop</span>
	</h1>
	<nav>
		<ul>
			<li ${param.title eq 'Home' ? 'id=\"actual\"' : ''}><a href="Controller">Home</a></li>
			<c:if test="${sessionScope.user.role eq 'CUSTOMER' || sessionScope.user.role eq 'ADMINISTRATOR'}">
				<li ${param.title eq 'Product Overview' ? 'id=\"actual\"' : ''}><a href="Controller?action=productOverview">Product Overview</a></li>
				<li ${param.title eq 'Shopcart Overview' ? 'id=\"actual\"' : ''}><a href="Controller?action=shopCartOverview">Shopping cart (${sessionScope.cart eq null ? 0 : sessionScope.cart.numberOfProductOrdersInCart})</a></li>
			</c:if>
			<c:if test="${sessionScope.user.role eq 'ADMINISTRATOR'}">
				<li ${param.title eq 'Add a new product' ? 'id=\"actual\"' : ''}><a href="Controller?action=productFormAdd">Add a new product</a></li>
				<li ${param.title eq 'User Overview' ? 'id=\"actual\"' : ''}><a href="Controller?action=userOverview">User Overview</a></li>
			</c:if>
			<c:if test="${sessionScope.user eq null }">
				<li ${param.title eq 'Sign Up' ? 'id=\"actual\"' : ''}><a href="Controller?action=userFormSignUp">Sign up</a></li>
			</c:if>
		</ul>
	</nav>
	<h2>${param.title}</h2>

</header>x