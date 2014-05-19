<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
	<c:choose>
		<c:when test="${satelliteId == 2}">
    		<li class="current_page_item">AMSAT P/L - Test Stack</li>
		    <li><a href="<c:url value='/realtime.html?satelliteId=0'/>">AMSAT P/L - Engineering Model</a></li>
		    <li>AMSAT P/L - Flight Model</li>
    	</c:when>
	    <c:when test="${satelliteId == 0}">
    		<li><a href="<c:url value='/realtime.html?satelliteId=2'/>">AMSAT P/L - Test Stack</a></li>
		    <li class="current_page_item">AMSAT P/L - Engineering Model</li>
		    <li>AMSAT P/L - Flight Model</li>
    	</c:when>
		<c:when test="${satelliteId == 3}">
    		<li><a href="<c:url value='/realtime.html?satelliteId=2'/>">AMSAT P/L - Test Stack</a></li>
		    <li><a href="<c:url value='/realtime.html?satelliteId=0'/>">AMSAT P/L - Engineering Model</a></li>
		    <li class="current_page_item">AMSAT P/L - Flight Model</li>
    	</c:when>
		<c:when test="${satelliteId == 1}">
    		<li><a href="<c:url value='/realtime.html?satelliteId=2'/>">AMSAT P/L - Test Stack</a></li>
		    <li><a href="<c:url value='/realtime.html?satelliteId=0'/>">AMSAT P/L - Engineering Model</a></li>
		    <li>AMSAT P/L - Flight Model</li>
    	</c:when>
		<c:otherwise>
    		<li class="current_page_item">AMSAT P/L - Test Stack</li>
		    <li><a href="<c:url value='/realtime.html?satelliteId=0'/>">AMSAT P/L - Engineering Model</a></li>
		    <li><a href="<c:url value='/realtime.html?satelliteId=3'/>">AMSAT P/L  - Flight Model</a></li>
	    </c:otherwise>
    </c:choose>
</ul>
