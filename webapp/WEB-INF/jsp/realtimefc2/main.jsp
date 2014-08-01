<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Real Time Data</h2>
<p>
	This page will show the latest value of all the housekeeping parameters in
	the spacecraft. <br>The data will have been collected by one or more of
	the ground stations who will be submitting this data from all around the
	world.
</p>

<div id="tabs" style="width: 100%;">
	<ul>

		<li><a href="<c:url value='#tabs-eps'/>"
			title="Electrical Power Subsystem">EPS</a></li>
		<li><a href="<c:url value='#tabs-amac'/>"
			title="Attitude Control Subsystem">AMAC</a></li>
		<li><a href="<c:url value='#tabs-rf'/>"
			title="Communication Subsystem">RF</a></li>
		<li><a href="<c:url value='#tabs-pa'/>"
			title="RF Power Amplifier">PA</a></li>
		<li><a href="<c:url value='#tabs-sw'/>"
			title="Software State">SW</a></li>
	</ul>
	<div id="tabs-eps">
		<br />
		<div style="text-align: center">
			<h3>Electrical Power Subsystem</h3>
			<h3 id="eps_lat_long" style="text-align: left;">Satellite Latitude, Longitude:</h3>
			<h3 id="eps_created_date" style="text-align: left;">Latest
				update at:</h3>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
					<td style="text-align: center; white-space: nowrap;">Min.</td>
					<td style="text-align: center; white-space: nowrap;">Max.</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array +Y Deployable +</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array +Y Deployable -</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array Y Fixed +</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array Y Fixed -</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array Z Fixed +</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array Z Fixed -</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array -Y Deployable +</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array -Y Deployable -</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array X Fixed +</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array X Fixed -</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array +X Deployable +</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array +X Deployable -</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 0 Current</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 0 Voltage</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 0 Temprature</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 1 Current</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 1 Voltage</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 1 Temprature</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 2 Current</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 2 Voltage</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 2 Temprature</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
			</table>
		</div>
		<br>
	</div>
	
	<div id="tabs-amac">
	<br />
		<div style="text-align: center">
			<h3>Attitide Control Subsystem</h3>
	
			<h3 id="amac_lat_long" style="text-align: left;">Satellite Latitude, Longitude:</h3>
			<h3 id="amac_created_date" style="text-align: left;">Latest
				update at:</h3>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
					<td style="text-align: center; white-space: nowrap;">Min.</td>
					<td style="text-align: center; white-space: nowrap;">Max.</td>
				</tr>
				<tr>
					<td style="text-align: center;">Array Temperature</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Controller Mode</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Mag. Flux Density X</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Mag. Flux Density Y</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Mag. Flux Density Z</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
			</table>
		</div>
		<br />
	</div>

	<div id="tabs-rf">
		<br />
		<div style="text-align: center">
			<h3>Communication Subsystem</h3>

			<h3 id="rf_lat_long" style="text-align: left;">Satellite Latitude, Longitude:</h3>
			<h3 id="rf_created_date" style="text-align: left;">Latest update
				at:</h3>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
					<td style="text-align: center; white-space: nowrap;">Min.</td>
					<td style="text-align: center; white-space: nowrap;">Max.</td>
				</tr>
				<tr>
					<td id="rf_receive_doppler_name" style="text-align: center;">$nbsp;</td>
					<td id="rf_receive_doppler_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="rf_receive_doppler_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="rf_receive_doppler_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				<tr>
					<td id="rf_receive_rssi_name" style="text-align: center;">$nbsp;</td>
					<td id="rf_receive_rssi_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="rf_receive_rssi_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="rf_receive_rssi_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="rf_temperature_name" style="text-align: center;">$nbsp;</td>
					<td id="rf_temperature_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="rf_temperature_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="rf_temperature_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="rf_receive_current_name" style="text-align: center;">$nbsp;</td>
					<td id="rf_receive_current_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="rf_receive_current_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="rf_receive_current_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="rf_transmit_current_three_name" style="text-align: center;">$nbsp;</td>
					<td id="rf_transmit_current_three_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="rf_transmit_current_three_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="rf_transmit_current_three_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="rf_transmit_current_five_name" style="text-align: center;">$nbsp;</td>
					<td id="rf_transmit_current_five_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="rf_transmit_current_five_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="rf_transmit_current_five_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
			</table>
		</div>
		<br />
	</div>
	<!-- End tabs-rf -->

	<div id="tabs-pa">
		<br />
		<div style="text-align: center">
			<h3>RF Power Amplifier</h3>

			<h3 id="pa_lat_long" style="text-align: left;">Satellite Latitude, Longitude:</h3>
			<h3 id="pa_created_date" style="text-align: left;">Latest update
				at:</h3>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
					<td style="text-align: center; white-space: nowrap;">Min.</td>
					<td style="text-align: center; white-space: nowrap;">Max.</td>
				</tr>
				<tr>
					<td id="pa_forward_power_name" style="text-align: center;">$nbsp;</td>
					<td id="pa_forward_power_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="pa_forward_power_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="pa_forward_power_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				<tr>
					<td id="pa_reverse_power_name" style="text-align: center;">$nbsp;</td>
					<td id="pa_reverse_power_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="pa_reverse_power_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="pa_reverse_power_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="pa_device_temperature_name" style="text-align: center;">$nbsp;</td>
					<td id="pa_device_temperature_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="pa_device_temperature_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="pa_device_temperature_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="pa_bus_current_name" style="text-align: center;">$nbsp;</td>
					<td id="pa_bus_current_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="pa_bus_current_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="pa_bus_current_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
			</table>
		</div>
		<br />
	</div>
	<div id="tabs-sw">
		<br />
		<div style="text-align: center">
			<h3>Software State</h3>
			<h3 id="sw_lat_long" style="text-align: left;">Satellite Latitude, Longitude:</h3>
			<h3 id="sw_created_date" style="text-align: left;">Latest update
				at:</h3>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
				</tr>
			</table>
		</div>
	</div>
	
</div>
