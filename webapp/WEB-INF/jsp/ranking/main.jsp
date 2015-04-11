<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Upload Ranking</h2>
<p>
	We should like to thank all the groups and individuals who have
	uploaded data to the FUncube data warehouse. <br>The following
	list gives the site names of those who have uploaded the most data
	frames:
</p>
<table style="width: 60%;">
	<tr>
		<td style="text-align: left; white-space: nowrap; width: 10%">Key:</td>
		<td style="text-align: left; white-space: nowrap; width: 20%; color: LawnGreen">Within 1 week,</td>
		<td style="text-align: left; white-space: nowrap; width: 20%; color: Orange">within 2 weeks,</td>
		<td style="text-align: left; white-space: nowrap; width: 20%">greater than 2 weeks</td>
		<td style="text-align: left; white-space: nowrap; width: 30%">&nbsp;</td>
	</tr>
</table>
<p>
<table style="width: 100%;">
	<tr>
		<td style="text-align: center; white-space: nowrap;">Site Id</td>
		<td style="text-align: center; white-space: nowrap;">Count</td>
		<td style="text-align: center; white-space: nowrap;">Position</td>
	</tr>
	<c:forEach var="o" items="${ranking}">
		<tr>
			<td style="text-align: center; white-space: nowrap;">${o.name}</td>
			<td style="text-align: center; white-space: nowrap; color: ${o.max}">${o.value}</td>
			<td style="text-align: center; white-space: nowrap;">${o.min}</td>
		</tr>
	</c:forEach>

</table>