<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>High Resolution Data</h2>
<p>This page shows the values of all the high resolution parameters
	captured during the latest 60 seconds of data upload.</p>
	
<div id="tabs" style="width: 100%;">
	<ul>
		<li><a href="<c:url value='#tabs-eps'/>" title="Electrical Power Subsystem">EPS</a></li>
		<li><a href="<c:url value='#tabs-asib'/>" title="Sensor Subsystem">ASIB</a></li>
	</ul>
	
	<div id="tabs-eps">
		<br />
		<div style="text-align: center">
			<b>Electrical Power Subsystem</b>
			<h3 style="text-align: left;">Latest update at: ${createdDate}</h3>
		</div>
	</div>
	
	<div id="tabs-asib">
		<br />
		<div style="text-align: center">
			<b>Sensor Subsystem</b>
			<h3 style="text-align: left;">Latest update at: ${createdDate}</h3>
		</div>
	</div>
	
</div>