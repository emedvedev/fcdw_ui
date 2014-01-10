<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>High Resolution Data</h2>
<p>This page shows the values of all the high resolution parameters
	captured during the latest 240 seconds of successful upload.</p>
	


<h3>To download a .csv file which contains the latest available High Resolution data please click <strong><a href="/downloads/hires.csv">here</a></strong>.
This data is generated every hour, on the hour and contains the preceding 60 minutes of information</h3>
<p>The date/time in the csv file is 'SatelliteTime' It is based on the number for sequences / frames
it has transmitted since spacecraft initialisation after separation (2013-11-21 07:38:16).
<br>This time will drift as it is based on the MCU clock which is not temperature controlled. In the future we may be able to give realtime if we can model the drift...
</p>	
	
<div id="highres-container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	
<!--
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
-->