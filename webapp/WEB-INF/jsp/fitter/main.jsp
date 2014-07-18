<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Fitter Messages</h2>
<p>
	The following list shows the fitter messages received from the satellite. 
	<br>Some messages are wider than the web page, these are indicated by an ellipsis &hellip;
	and can be viewed by hovering the cursor over the line.
</p>
<c:choose>
	<c:when test="${satelliteId != 1}">
		<p>A text file which contains ALL the Fitter Messages that have been captured by groundstations 
			up to 7 days ago can be downloaded <a href="/downloads/fitter.txt">here</a>
		</p>
	</c:when>
</c:choose>
<table style="width: 100%;">
	<tr>
		<td style="text-align: left; white-space: nowrap;">Message</td>
		<td style="text-align: center; white-space: nowrap;">Last received</td>
		<td style="text-align: center; white-space: nowrap;">Slot</td>
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
			<td style="text-align: center; white-space: nowrap; font-size: small;">${o.max}</td>
		</tr>
	</c:forEach>

</table>