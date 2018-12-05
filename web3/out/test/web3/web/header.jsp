<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
	<h1>
		<span>Web shop</span>
	</h1>
	<nav>
		<ul>
			<li><a href="Controller">Home</a></li>
			<li><a href="Controller?action=productOverview">Product Overview</a></li>
			<li><a href="Controller?action=userOverview">User Overview</a></li>
			<li><a href="Controller?action=userFormSignUp">Sign up</a></li>
		</ul>
	</nav>
	<h2>${param.title}</h2>

</header>