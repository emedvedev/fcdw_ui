<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="banner">
    <div>
    <c:choose>
    	<c:when test="${pageContext['request'].userPrincipal == null}">
                [ <a href="<c:url value='/register.html?satelliteId=2'/>">Register</a> ]
		</c:when>
		<c:otherwise>
                [ <a href="<c:url value='/j_spring_security_logout?satelliteId=2'/>">Logout</a> ]
		</c:otherwise>
	</c:choose>
    </div>
</div>
