<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Whole Orbit Data</h2>
<p>This page shows the values of all the WOD parameters
	captured during the latest 105 minutes when the satellite was in range of a ground station.</p>
	
<div id="tabs" style="width: 100%;">
	<ul>
		<li><a href="#tabs-eps" title="Electrical Power Subsystem">EPS</a></li>
		<li><a href="#tabs-asib" title="Sensor Subsystem">ASIB</a></li>
		<li><a href="#tabs-mse" title="Materials Science Experiment">MSE</a></li>
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
	
	<div id="tabs-mse">
		<br />
		<div style="text-align: center">
			<b>Materials Science Experiment</b>
			<h3 style="text-align: left;">Latest update at: ${createdDate}</h3>
		</div>
	</div>
</div>