<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Current Satellite Position</h2>
<br>
	<c:choose>
		<c:when test="${satelliteId == 1}">
			<script src="http://www.gmodules.com/ig/ifr?url=http://www.n2yo.com/sat/esa/esatracker.xml&up_satid=40074&up_zoom=1&up_maptype=4&synd=open&w=520&h=400&title=UKUBE 1&border=http%3A%2F%2Fwww.gmodules.com%2Fig%2Fimages%2F&output=js&nocache=1"></script>
		</c:when>
		<c:otherwise>
			<script src="http://www.gmodules.com/ig/ifr?url=http://www.n2yo.com/sat/funcube.xml&amp;synd=open&amp;w=520&amp;h=400&amp;title=FUNCUBE-1&amp;border=http%3A%2F%2Fwww.gmodules.com%2Fig%2Fimages%2F&amp;output=js"></script>
		</c:otherwise>
	</c:choose>
<p><strong>We are indebted to Chip, N2YO for the supply of this functionality. 
For more information on this widget and his other web based tracking activites,
please click on the link on the map.</strong></p>
