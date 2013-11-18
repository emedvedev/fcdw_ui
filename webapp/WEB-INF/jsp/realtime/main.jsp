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
			<h3 id = "eps_created_date" style="text-align: left;">Latest update at:</h3>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
					<td style="text-align: center; white-space: nowrap;">Min.</td>
					<td style="text-align: center; white-space: nowrap;">Max.</td>
				</tr>
				<tr>
					<td id = "eps_sol_volts_x_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_sol_volts_x_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_sol_volts_x_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_sol_volts_x_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				<tr>
					<td id = "eps_sol_volts_y_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_sol_volts_y_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_sol_volts_y_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_sol_volts_y_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "eps_sol_volts_z_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_sol_volts_z_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_sol_volts_z_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_sol_volts_z_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "eps_total_photo_current_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_total_photo_current_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_total_photo_current_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_total_photo_current_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "eps_battery_volts_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_battery_volts_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_battery_volts_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_battery_volts_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "eps_total_system_current_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_total_system_current_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_total_system_current_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_total_system_current_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "eps_reboot_count_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_reboot_count_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_reboot_count_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_reboot_count_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "eps_software_errors_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_software_errors_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_software_errors_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_software_errors_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "eps_boost_temp_x_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_boost_temp_x_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_boost_temp_x_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_boost_temp_x_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "eps_boost_temp_y_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_boost_temp_y_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_boost_temp_y_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_boost_temp_y_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "eps_boost_temp_z_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_boost_temp_z_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_boost_temp_z_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_boost_temp_z_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "eps_battery_temp_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_battery_temp_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_battery_temp_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_battery_temp_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "eps_latch_up_5_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_latch_up_5_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_latch_up_5_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_latch_up_5_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "eps_latch_up_3_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_latch_up_3_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_latch_up_3_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_latch_up_3_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "eps_reset_cause_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_reset_cause_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_reset_cause_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_reset_cause_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "eps_ppt_mode_name" style="text-align: center;">$nbsp;</td>
					<td id = "eps_ppt_mode_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_ppt_mode_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "eps_ppt_mode_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
			</table>
		</div>
		<br />
	</div>
	<!-- End tabs-eps -->

	<div id="tabs-asib">
		<br />
		<div style="text-align: center">
			<b>Sensor Subsystem</b>
			
			<h3 id = "asib_created_date" style="text-align: left;">Latest update at:</h3>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
					<td style="text-align: center; white-space: nowrap;">Min.</td>
					<td style="text-align: center; white-space: nowrap;">Max.</td>
				</tr>
				<tr>
					<td id = "asib_sun_sensor_x_name" style="text-align: center;">$nbsp;</td>
					<td id = "asib_sun_sensor_x_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_sun_sensor_x_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_sun_sensor_x_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				<tr>
					<td id = "asib_sun_sensor_y_name" style="text-align: center;">$nbsp;</td>
					<td id = "asib_sun_sensor_y_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_sun_sensor_y_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_sun_sensor_y_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "asib_sun_sensor_z_name" style="text-align: center;">$nbsp;</td>
					<td id = "asib_sun_sensor_z_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_sun_sensor_z_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_sol_volts_z_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "asib_sol_temp_x_plus_name" style="text-align: center;">$nbsp;</td>
					<td id = "asib_sol_temp_x_plus_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_sol_temp_x_plus_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_sol_temp_x_plus_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "asib_sol_temp_x_minus_name" style="text-align: center;">$nbsp;</td>
					<td id = "asib_sol_temp_x_minus_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_sol_temp_x_minus_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_sol_temp_x_minus_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "asib_sol_temp_y_plus_name" style="text-align: center;">$nbsp;</td>
					<td id = "asib_sol_temp_y_plus_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_sol_temp_y_plus_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_sol_temp_y_plus_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "asib_sol_temp_y_minus_name" style="text-align: center;">$nbsp;</td>
					<td id = "asib_sol_temp_y_minus_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_sol_temp_y_minus_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_sol_temp_y_minus_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "asib_bus_volts_three_name" style="text-align: center;">$nbsp;</td>
					<td id = "asib_bus_volts_three_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_bus_volts_three_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_bus_volts_three_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "asib_bus_current_three_name" style="text-align: center;">$nbsp;</td>
					<td id = "asib_bus_current_three_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_bus_current_three_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_bus_current_three_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
				<tr>
					<td id = "asib_bus_volts_five_name" style="text-align: center;">$nbsp;</td>
					<td id = "asib_bus_volts_five_value" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_bus_volts_five_min" style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id = "asib_bus_volts_five_max" style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				
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