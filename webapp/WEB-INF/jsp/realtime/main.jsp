<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Real Time Data</h2>
<p>
	This page shows the latest value of all the housekeeping parameters in
	the spacecraft. <br>The data has been collected by one or more of the
	ground stations who are submitting this data from all around the world.
</p>

<div id="tabs" style="width: 100%;">
	<ul>

		<li><a href="<c:url value='#tabs-eps'/>" title="Electrical Power Subsystem">EPS</a></li>
		<li><a href="<c:url value='#tabs-asib'/>" title="Sensor Subsystem">ASIB</a></li>
		<li><a href="<c:url value='#tabs-rf'/>" title="Communication Subsystem">RF</a></li>
		<li><a href="<c:url value='#tabs-pa'/>" title="RF Power Amplifier">PA</a></li>
		<li><a href="<c:url value='#tabs-ants'/>" title="Antenna Subsystem">ANTS</a></li>
		<li><a href="<c:url value='#tabs-sw'/>" title="Software State">SW</a></li>

	</ul>

	<div id="tabs-eps">
		<br />
		<div style="text-align: center">
			<b>Electrical Power Subsystem</b>
			<h3 style="text-align: left;">Latest update at: ${createdDate}</h3>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: left;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
					<td style="text-align: center; white-space: nowrap;">Min.</td>
					<td style="text-align: center; white-space: nowrap;">Max.</td>
				</tr>
				<c:forEach var="o" items="${epsValues}">
					<tr>
						<td>${o.name}</td>
						<td style="text-align: center; white-space: nowrap;">${o.value}</td>
						<td style="text-align: center; white-space: nowrap;">${o.min}</td>
						<td style="text-align: center; white-space: nowrap;">${o.max}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br />
	</div>
	<!-- End tabs-eps -->

	<div id="tabs-asib">
		<br />
		<div style="text-align: center">
			<b>Sensor Subsystem</b>
			<h3 style="text-align: left;">Latest update at: ${createdDate}</h3>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: left;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
					<td style="text-align: center; white-space: nowrap;">Min.</td>
					<td style="text-align: center; white-space: nowrap;">Max.</td>
				</tr>
				<c:forEach var="o" items="${asibValues}">
					<tr>
						<td>${o.name}</td>
						<td style="text-align: center; white-space: nowrap;">${o.value}</td>
						<td style="text-align: center; white-space: nowrap;">${o.min}</td>
						<td style="text-align: center; white-space: nowrap;">${o.max}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br />
	</div>
	<!-- End tabs-bob -->

	<div id="tabs-rf">
		<br />
		<div style="text-align: center">
			<b>Communication Subsystem</b>
			<h3 style="text-align: left;">Latest update at: ${createdDate}</h3>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: left;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
					<td style="text-align: center; white-space: nowrap;">Min.</td>
					<td style="text-align: center; white-space: nowrap;">Max.</td>
				</tr>
				<c:forEach var="o" items="${rfValues}">
					<tr>
						<td>${o.name}</td>
						<td style="text-align: center; white-space: nowrap;">${o.value}</td>
						<td style="text-align: center; white-space: nowrap;">${o.min}</td>
						<td style="text-align: center; white-space: nowrap;">${o.max}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br />
	</div>
	<!-- End tabs-rf -->

	<div id="tabs-pa">
		<br />
		<div style="text-align: center">
			<b>RF Power Amplifier</b>
			<h3 style="text-align: left;">Latest update at: ${createdDate}</h3>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: left;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
					<td style="text-align: center; white-space: nowrap;">Min.</td>
					<td style="text-align: center; white-space: nowrap;">Max.</td>
				</tr>
				<c:forEach var="o" items="${paValues}">
					<tr>
						<td>${o.name}</td>
						<td style="text-align: center; white-space: nowrap;">${o.value}</td>
						<td style="text-align: center; white-space: nowrap;">${o.min}</td>
						<td style="text-align: center; white-space: nowrap;">${o.max}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br />
	</div>
	<!-- End tabs-pa -->

	<div id="tabs-ants">
		<br />
		<div style="text-align: center">
			<b>Antenna Subsystem</b>
			<h3 style="text-align: left;">Latest update at: ${createdDate}</h3>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: left;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
				</tr>
				<c:forEach var="o" items="${antsValues}">
					<tr>
						<td>${o.name}</td>
						<td style="text-align: center; white-space: nowrap;">${o.value}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br />
	</div>
	<!-- End tabs-ants -->

	<div id="tabs-sw">
		<br />
		<div style="text-align: center">
			<b>Software State</b>
			<h3 style="text-align: left;">Latest update at: ${createdDate}</h3>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: left;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
				</tr>
				<c:forEach var="o" items="${swValues}">
					<tr>
						<td>${o.name}</td>
						<td style="text-align: center; white-space: nowrap;">${o.value}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br />
	</div>
	<!-- End tabs-sw -->
</div>