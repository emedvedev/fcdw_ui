<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
	<c:choose>
		<c:when test="${satelliteId == 2}">
    		<li class="current_page_item">FUNcube-1 Flight Model</li>
		    <li><a href="<c:url value='/realtime.html?satelliteId=0'/>">FC1 Engineering model</a></li>
		    <li><a href="<c:url value='/realtimeFC2.html'/>">UKube FC2 Payload</a></li>
    	</c:when>
	    <c:when test="${satelliteId == 0}">
    		<li><a href="<c:url value='/realtime.html?satelliteId=2'/>">FUNcube-1 Flight Model</a></li>
		    <li class="current_page_item">FC1 Engineering model</li>
		    <li><a href="<c:url value='/realtimeFC2.html'/>">UKube FC2 Payload</a></li>
    	</c:when>
		<c:when test="${satelliteId == 1}">
    		<li><a href="<c:url value='/realtime.html?satelliteId=2'/>">FUNcube-1 Flight Model</a></li>
		    <li><a href="<c:url value='/realtime.html?satelliteId=0'/>">FC1 Engineering model</a></li>
		    <li class="current_page_item">UKube FC2 Payload</li>
    	</c:when>
		<c:otherwise>
    		<li class="current_page_item">FUNcube-1 Flight Model</li>
		    <li><a href="<c:url value='/realtime.html?satelliteId=0'/>">FC1 Engineering model</a></li>
		    <li><a href="<c:url value='/realtime.html?satelliteId=1'/>">UKube FC2 Payload</a></li>
	    </c:otherwise>
    </c:choose>
</ul>
