<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>CCT Debug</h2>
<h3 style="text-align: left;">Latest update at: ${createdDate}</h3>
<div id="tabs" style="width: 100%;">
	<ul>

		<li><a href="#tabs-misc" title="Miscellaneous">MISC</a></li>
		<li><a href="#tabs-ants" title="Antenna Subsystem">ANTS</a></li>
		<li><a href="#tabs-state" title="Software State">STATE</a></li>
		<li><a href="#tabs-eclipse" title="Eclipse State">ECLIPSE</a></li>
		<li><a href="#tabs-safe" title="Safe State">SAFE</a></li>
		<li><a href="#tabs-burn" title="Burn State">BURN</a></li>
		<li><a href="#tabs-auto" title="Auto State">AUTO</a></li>

	</ul>

	<div id="tabs-misc">
		<br />
		<div style="text-align: center">
			<b>Miscellaneous</b>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
				</tr>
				<c:forEach var="o" items="${miscValues}">
					<tr>
						<td style="text-align: center;">${o.name}</td>
						<td style="text-align: center; white-space: nowrap;">${o.value}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<div id="tabs-ants">
		<br />
		<div style="text-align: center">
			<b>Antenna Subsystem</b>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
				</tr>
				<c:forEach var="o" items="${antsValues}">
					<tr>
						<td style="text-align: center;">${o.name}</td>
						<td style="text-align: center; white-space: nowrap;">${o.value}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<div id="tabs-state">
		<br />
		<div style="text-align: center">
			<b>Software State</b>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
				</tr>
				<c:forEach var="o" items="${stateValues}">
					<tr>
						<td style="text-align: center;">${o.name}</td>
						<td style="text-align: center; white-space: nowrap;">${o.value}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<div id="tabs-eclipse">
		<br />
		<div style="text-align: center">
			<b>Eclipse State</b>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
				</tr>
				<c:forEach var="o" items="${eclipseValues}">
					<tr>
						<td style="text-align: center;">${o.name}</td>
						<td style="text-align: center; white-space: nowrap;">${o.value}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<div id="tabs-safe">
		<br />
		<div style="text-align: center">
			<b>Safe State</b>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
				</tr>
				<c:forEach var="o" items="${safeValues}">
					<tr>
						<td style="text-align: center;">${o.name}</td>
						<td style="text-align: center; white-space: nowrap;">${o.value}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<div id="tabs-burn">
		<br />
		<div style="text-align: center">
			<b>Burn State</b>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
				</tr>
				<c:forEach var="o" items="${burnValues}">
					<tr>
						<td style="text-align: center;">${o.name}</td>
						<td style="text-align: center; white-space: nowrap;">${o.value}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<div id="tabs-auto">
		<br />
		<div style="text-align: center">
			<b>Auto State</b>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
				</tr>
				<c:forEach var="o" items="${autoValues}">
					<tr>
						<td style="text-align: center;">${o.name}</td>
						<td style="text-align: center; white-space: nowrap;">${o.value}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>