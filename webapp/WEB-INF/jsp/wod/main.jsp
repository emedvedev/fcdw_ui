<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Whole Orbit Data</h2>
<p>This page shows the values of all the WOD parameters
	captured during the latest 104 minutes when the satellite was in range of a ground station.</p>
<h3>To download a .csv file which contains 24 hours of WOD please click <strong><a href="/downloads/wod.csv">here</a></strong>. This data is up-to-date to 23:59 UTC yesterday</h3>
<br>
<h3>To download weekly data in a .csv file go to the <strong><a href="<c:url value='/wodweekly.html?satelliteId=${satelliteId}'/> ">downloads</a></strong> page</h3>
<br>
<div id="wod-container" style="min-width: 310px; height: 500px; margin: 0 auto"></div>
