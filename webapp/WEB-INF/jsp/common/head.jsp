<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="banner">
    <div>
    <c:choose>
    	<c:when test="${pageContext['request'].userPrincipal == null}">
                [ <a href="<c:url value='/login.html?satelliteId=2'/>">Login</a> | <a href="<c:url value='/register.html?satelliteId=2'/>">Register</a> ]
		</c:when>
		<c:otherwise>
                [ Welcome ${pageContext['request'].userPrincipal.name} | <a href="<c:url value='/j_spring_security_logout?satelliteId=2'/>">Logout</a> ]
		</c:otherwise>
	</c:choose>
    </div>
</div>
