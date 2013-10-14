<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
	<c:choose>
		<c:when test="${satelliteId == 2}">
    		<li class="current_page_item">FUNcube Flight Model</li>
		    <li><a href="/home.html?satelliteId=0">FUNcube Eng. Model</a></li>
		    <li><a href="/home.html?satelliteId=3">FUNcube Test Stack</a></li>
		    <li><a href="/home.html?satelliteId=1">UKube-1 FC Module</a></li>
    	</c:when>
	    <c:when test="${satelliteId == 0}">
    		<li><a href="/home.html?satelliteId=2">FUNcube Flight Model</a></li>
		    <li class="current_page_item">FUNcube Eng. Model</li>
		    <li><a href="/home.html?satelliteId=3">FUNcube Test Stack</a></li>
		    <li><a href="/home.html?satelliteId=1">UKube-1 FC Module</a></li>
    	</c:when>
		<c:when test="${satelliteId == 3}">
    		<li><a href="/home.html?satelliteId=2">FUNcube Flight Model</a></li>
		    <li><a href="/home.html?satelliteId=0">FUNcube Eng. Model</a></li>
		    <li class="current_page_item">FUNcube Test Stack</li>
		    <li><a href="/home.html?satelliteId=1">UKube-1 FC Module</a></li>
    	</c:when>
		<c:when test="${satelliteId == 1}">
    		<li><a href="/home.html?satelliteId=2">FUNcube Flight Model</a></li>
		    <li><a href="/home.html?satelliteId=0">FUNcube Eng. Model</a></li>
		    <li><a href="/home.html?satelliteId=3">FUNcube Test Stack</a></li>
		    <li class="current_page_item">UKube-1 FC Module</li>
    	</c:when>
		<c:otherwise>
    		<li class="current_page_item">FUNcube Flight Model</li>
		    <li><a href="/home.html?satelliteId=0">FUNcube Eng. Model</a></li>
		    <li><a href="/home.html?satelliteId=3">FUNcube Test Stack</a></li>
		    <li><a href="/home.html?satelliteId=1">UKube-1 FC Module</a></li>
	    </c:otherwise>
    </c:choose>
</ul>
