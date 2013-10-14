<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Fitter Messages</h2>
<p>
	The following list shows the fitter messages received from the satellite. 
	<br>Some messages are wider than the web page, these are indicated by an ellipsis &hellip;
	and can be viewed by hovering the cursor over the line.
</p>
<table style="width: 100%;">
	<tr>
		<td style="text-align: left; white-space: nowrap;">Message</td>
		<td style="text-align: center; white-space: nowrap;">Date last received</td>
	</tr>
	<c:forEach var="o" items="${fitter}">
		<tr>
		    <c:choose>
		    	<c:when test="${o.min != null}">
		            <td style="text-align: left; white-space: nowrap; font-size: small;"><a title="${o.name}">${o.min}&hellip;</a></td>
				</c:when>
				<c:otherwise>
		            <td style="text-align: left; white-space: nowrap; font-size: small;">${o.name}</td>
				</c:otherwise>
			</c:choose>
			<td style="text-align: center; white-space: nowrap; font-size: small;">${o.value}</td>
		</tr>
	</c:forEach>

</table>