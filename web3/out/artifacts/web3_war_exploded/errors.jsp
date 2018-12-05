<c:if test="${errors != null}">
	<div>
		<ul>
			<c:forEach  var="e" items="${errors}">
				<li><c:out value="${e.value}"/></li>
			</c:forEach>
		</ul>
	</div>
</c:if>