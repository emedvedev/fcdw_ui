<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Real Time Data</h2>
<p>
	This page shows the latest value of all the housekeeping parameters in
	the spacecraft. <br>The data has been collected by one or more of
	the ground stations who are submitting this data from all around the
	world.
</p>

<div id="tabs" style="width: 100%;">
	<ul>

		<li><a href="<c:url value='#tabs-eps'/>"
			title="Electrical Power Subsystem">EPS</a></li>
		<li><a href="<c:url value='#tabs-amac'/>"
			title="Active Magnetic Attitude Control Subsystem">AMAC</a></li>
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
					<td id="eps_sol_1_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_1_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_1_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array +Y Deployable -</td>
					<td id="eps_sol_2_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_2_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_2_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array Y Fixed +</td>
					<td id="eps_sol_3_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_3_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_3_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array Y Fixed -</td>
					<td id="eps_sol_4_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_4_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_4_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array Z Fixed +</td>
					<td id="eps_sol_5_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_5_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_5_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array Z Fixed -</td>
					<td id="eps_sol_6_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_6_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_6_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array -Y Deployable +</td>
					<td id="eps_sol_7_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_7_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_7_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array -Y Deployable -</td>
					<td id="eps_sol_8_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_8_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_8_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array X Fixed +</td>
					<td id="eps_sol_9_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_9_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_9_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array X Fixed -</td>
					<td id="eps_sol_10_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_10_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_10_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array +X Deployable +</td>
					<td id="eps_sol_11_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_11_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_11_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Solar Array +X Deployable -</td>
					<td id="eps_sol_12_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_12_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_sol_12_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 0 Current</td>
					<td id="eps_bat_1_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_1_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_1_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 0 Voltage</td>
					<td id="eps_bat_2_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_2_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_2_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 0 Temperature</td>
					<td id="eps_bat_3_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_3_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_3_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 1 Current</td>
					<td id="eps_bat_4_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_4_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_4_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 1 Voltage</td>
					<td id="eps_bat_5_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_5_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_5_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 1 Temperature</td>
					<td id="eps_bat_6_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_6_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_6_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 2 Current</td>
					<td id="eps_bat_7_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_7_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_7_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 2 Voltage</td>
					<td id="eps_bat_8_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_8_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_8_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Battery 2 Temperature</td>
					<td id="eps_bat_9_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_9_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="eps_bat_9_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Auto Thermal Control</td>
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
					<td style="text-align: center;">Controller Mode</td>
					<td id="amac_1_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="amac_1_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="amac_1_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Mag. Flux Density X</td>
					<td id="amac_2_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="amac_2_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="amac_2_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Mag. Flux Density Y</td>
					<td id="amac_3_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="amac_3_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="amac_3_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Mag. Flux Density Z</td>
					<td id="amac_4_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="amac_4_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="amac_4_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td style="text-align: center;">Mag. Flux Magnitude</td>
					<td id="amac_5_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="amac_5_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="amac_5_max"
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
					<td id="rf_receive_doppler_name" style="text-align: center;">Receive Doppler</td>
					<td id="rf_receive_doppler_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="rf_receive_doppler_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="rf_receive_doppler_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td id="rf_receive_rssi_name" style="text-align: center;">Receiver RSSI</td>
					<td id="rf_receive_rssi_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="rf_receive_rssi_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="rf_receive_rssi_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>

				<tr>
					<td id="rf_temperature_name" style="text-align: center;">Temperature</td>
					<td id="rf_temperature_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="rf_temperature_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="rf_temperature_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>

				<tr>
					<td id="rf_receive_current_name" style="text-align: center;">Receive Current</td>
					<td id="rf_receive_current_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="rf_receive_current_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="rf_receive_current_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>

				<tr>
					<td id="rf_transmit_current_three_name" style="text-align: center;">Transmit Current 3.3V bus</td>
					<td id="rf_transmit_current_three_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="rf_transmit_current_three_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="rf_transmit_current_three_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>

				<tr>
					<td id="rf_transmit_current_five_name" style="text-align: center;">Transmit Current 5.0V bus</td>
					<td id="rf_transmit_current_five_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="rf_transmit_current_five_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="rf_transmit_current_five_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
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
					<td id="pa_forward_power_name" style="text-align: center;">Forward Power</td>
					<td id="pa_forward_power_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="pa_forward_power_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="pa_forward_power_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td id="pa_reverse_power_name" style="text-align: center;">Reverse Power</td>
					<td id="pa_reverse_power_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="pa_reverse_power_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="pa_reverse_power_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>

				<tr>
					<td id="pa_device_temperature_name" style="text-align: center;">Device Temperature</td>
					<td id="pa_device_temperature_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="pa_device_temperature_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="pa_device_temperature_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>

				<tr>
					<td id="pa_bus_current_name" style="text-align: center;">Bus Current</td>
					<td id="pa_bus_current_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="pa_bus_current_min"
						style="text-align: center; white-space: nowrap;">N/A</td>
					<td id="pa_bus_current_max"
						style="text-align: center; white-space: nowrap;">N/A</td>
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
				
				<tr>
					<td id="sw_value_1_name" style="text-align: center;">Eclipsed</td>
					<td id="sw_value_1_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>


				<tr>
					<td id="sw_value_2_name" style="text-align: center;">Mode Manager Mode</td>
					<td id="sw_value_2_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>

				<tr>
					<td id="sw_value_3_name" style="text-align: center;">Mode Manager Comms. Nominal</td>
					<td id="sw_value_3_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>

				<tr>
					<td id="sw_value_4_name" style="text-align: center;">Mode Manager Comms. State</td>
					<td id="sw_value_4_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>

				<tr>
					<td id="sw_value_5_name" style="text-align: center;">TMTC Mode Idle</td>
					<td id="sw_value_5_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>

				<tr>
					<td id="sw_value_6_name" style="text-align: center;">TMTC Event Forwarding</td>
					<td id="sw_value_6_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td id="sw_value_7_name" style="text-align: center;">TC Buffer RX Enable</td>
					<td id="sw_value_7_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td id="sw_value_8_name" style="text-align: center;">TC Buffer TX Enable</td>
					<td id="sw_value_8_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>

				<tr>
					<td id="sw_value_9_name" style="text-align: center;">OBC Soft Reset Count</td>
					<td id="sw_value_9_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>

				<tr>
					<td id="sw_value_10_name" style="text-align: center;">EPS Hard Reset Count</td>
					<td id="sw_value_10_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>

				<tr>
					<td id="sw_value_11_name" style="text-align: center;">Sequence Number</td>
					<td id="sw_value_11_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>

				<tr>
					<td id="sw_value_12_name" style="text-align: center;">Command Count</td>
					<td id="sw_value_12_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td id="sw_value_13_name" style="text-align: center;">Last Command</td>
					<td id="sw_value_13_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
				<tr>
					<td id="sw_value_14_name" style="text-align: center;">Command Success</td>
					<td id="sw_value_14_value"
						style="text-align: center; white-space: nowrap;">N/A</td>
				</tr>
			</table>
		</div>
	</div>
	
</div>
