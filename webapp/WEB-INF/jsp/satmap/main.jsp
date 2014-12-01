<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Current Satellite Position</h2>
<br>
<c:choose>
	<c:when test="${satelliteId == 1}">
		<script>
			var norad = '40074';
			var size = 'small';
		</script>
		<script type="text/javascript"
			src="http://www.n2yo.com/js/webtracker.js"></script>
	</c:when>
	<c:otherwise>
		<script>
			var norad = '39444';
			var size = 'small';
		</script>
		<script type="text/javascript"
			src="http://www.n2yo.com/js/webtracker.js"></script>
	</c:otherwise>
</c:choose>
<p>
	<strong>We are indebted to Chip, N2YO for the supply of this
		functionality. For more information on this widget and his other web
		based tracking activites, please click on the link on the map.</strong>
</p>
