<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Whole Orbit Data Weekly Dumps (Under development)</h2>
<h3>To download a Comma Separated Variable (.csv) file, please click on the link.</h3>

<ul id = "weeklywod">
	<c:forEach var="o" items="${fileList}">
		<li>
		<a href="/downloads/wodweekly/${o}">${o}</a>
		</li>
	</c:forEach>
</ul>