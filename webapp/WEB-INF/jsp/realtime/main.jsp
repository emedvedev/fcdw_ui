<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Real Time Data</h2>


<br>

<div id="tabs" style="width: 100%;">
	<ul>

		<li><a href="<c:url value='#tabs-eps'/>"
			title="Electrical Power Subsystem">EPS</a></li>
		<li><a href="<c:url value='#tabs-asib'/>"
			title="Sensor Subsystem">ASIB</a></li>
		<li><a href="<c:url value='#tabs-rf'/>"
			title="Communication Subsystem">RF</a></li>
		<li><a href="<c:url value='#tabs-pa'/>"
			title="RF Power Amplifier">PA</a></li>
		<li><a href="<c:url value='#tabs-ants'/>"
			title="Antenna Subsystem">ANTS</a></li>
		<li><a href="<c:url value='#tabs-sw'/>" title="Software State">SW</a></li>

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
					<td id="eps_sol_volts_x_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_sol_volts_x_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_sol_volts_x_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_sol_volts_x_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				<tr>
					<td id="eps_sol_volts_y_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_sol_volts_y_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_sol_volts_y_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_sol_volts_y_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="eps_sol_volts_z_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_sol_volts_z_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_sol_volts_z_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_sol_volts_z_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="eps_total_photo_current_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_total_photo_current_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_total_photo_current_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_total_photo_current_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="eps_battery_volts_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_battery_volts_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_battery_volts_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_battery_volts_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="eps_total_system_current_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_total_system_current_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_total_system_current_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_total_system_current_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="eps_reboot_count_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_reboot_count_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_reboot_count_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_reboot_count_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="eps_software_errors_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_software_errors_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_software_errors_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_software_errors_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="eps_boost_temp_x_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_boost_temp_x_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_boost_temp_x_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_boost_temp_x_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="eps_boost_temp_y_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_boost_temp_y_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_boost_temp_y_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_boost_temp_y_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="eps_boost_temp_z_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_boost_temp_z_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_boost_temp_z_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_boost_temp_z_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="eps_battery_temp_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_battery_temp_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_battery_temp_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_battery_temp_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="eps_latch_up_5_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_latch_up_5_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_latch_up_5_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_latch_up_5_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="eps_latch_up_3_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_latch_up_3_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_latch_up_3_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_latch_up_3_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="eps_reset_cause_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_reset_cause_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_reset_cause_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_reset_cause_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="eps_ppt_mode_name" style="text-align: center;">$nbsp;</td>
					<td id="eps_ppt_mode_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_ppt_mode_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="eps_ppt_mode_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
			</table>
		</div>
		<br />
	</div>
	<!-- End tabs-eps -->

	<div id="tabs-asib">
		<br />
		<div style="text-align: center">
			<h3>Sensor Subsystem</h3>

			<h3 id="asib_lat_long" style="text-align: left;">Satellite Latitude, Longitude:</h3>
			<h3 id="asib_created_date" style="text-align: left;">Latest
				update at:</h3>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
					<td style="text-align: center; white-space: nowrap;">Min.</td>
					<td style="text-align: center; white-space: nowrap;">Max.</td>
				</tr>
				<tr>
					<td id="asib_sun_sensor_x_name" style="text-align: center;">$nbsp;</td>
					<td id="asib_sun_sensor_x_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_sun_sensor_x_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_sun_sensor_x_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				<tr>
					<td id="asib_sun_sensor_y_name" style="text-align: center;">$nbsp;</td>
					<td id="asib_sun_sensor_y_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_sun_sensor_y_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_sun_sensor_y_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="asib_sun_sensor_z_name" style="text-align: center;">$nbsp;</td>
					<td id="asib_sun_sensor_z_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_sun_sensor_z_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_sun_sensor_z_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="asib_sol_temp_x_plus_name" style="text-align: center;">$nbsp;</td>
					<td id="asib_sol_temp_x_plus_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_sol_temp_x_plus_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_sol_temp_x_plus_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="asib_sol_temp_x_minus_name" style="text-align: center;">$nbsp;</td>
					<td id="asib_sol_temp_x_minus_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_sol_temp_x_minus_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_sol_temp_x_minus_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="asib_sol_temp_y_plus_name" style="text-align: center;">$nbsp;</td>
					<td id="asib_sol_temp_y_plus_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_sol_temp_y_plus_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_sol_temp_y_plus_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="asib_sol_temp_y_minus_name" style="text-align: center;">$nbsp;</td>
					<td id="asib_sol_temp_y_minus_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_sol_temp_y_minus_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_sol_temp_y_minus_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="asib_bus_volts_three_name" style="text-align: center;">$nbsp;</td>
					<td id="asib_bus_volts_three_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_bus_volts_three_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_bus_volts_three_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="asib_bus_current_three_name" style="text-align: center;">$nbsp;</td>
					<td id="asib_bus_current_three_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_bus_current_three_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_bus_current_three_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="asib_bus_volts_five_name" style="text-align: center;">$nbsp;</td>
					<td id="asib_bus_volts_five_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_bus_volts_five_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="asib_bus_volts_five_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

			</table>
		</div>
		<br />
	</div>
	<!-- End tabs-bob -->

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
	<!-- End tabs-pa -->

	<div id="tabs-ants">
		<br />
		<div style="text-align: center">
			<h3>Antenna Subsystem</h3>

			<h3 id="ants_lat_long" style="text-align: left;">Satellite Latitude, Longitude:</h3>
			<h3 id="ants_created_date" style="text-align: left;">Latest
				update at:</h3>
			<table style="width: 100%;">
				<tr>
					<td style="text-align: center;">Name</td>
					<td style="text-align: center; white-space: nowrap;">Value</td>
					<td style="text-align: center; white-space: nowrap;">Min</td>
					<td style="text-align: center; white-space: nowrap;">Max</td>
				</tr>
				<tr>
					<td id="ants_temp_zero_name" style="text-align: center;">$nbsp;</td>
					<td id="ants_temp_zero_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="ants_temp_zero_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="ants_temp_zero_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				<tr>
					<td id="ants_temp_one_name" style="text-align: center;">$nbsp;</td>
					<td id="ants_temp_one_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="ants_temp_one_min"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td id="ants_temp_one_max"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="ants_deployment_vhf_a_name" style="text-align: center;">$nbsp;</td>
					<td id="ants_deployment_vhf_a_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td style="text-align: center; white-space: nowrap;"></td>
					<td style="text-align: center; white-space: nowrap;"></td>
				</tr>

				<tr>
					<td id="ants_deployment_vhf_b_name" style="text-align: center;">$nbsp;</td>
					<td id="ants_deployment_vhf_b_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td style="text-align: center; white-space: nowrap;"></td>
					<td style="text-align: center; white-space: nowrap;"></td>
				</tr>

				<tr>
					<td id="ants_deployment_uhf_a_name" style="text-align: center;">$nbsp;</td>
					<td id="ants_deployment_uhf_a_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td style="text-align: center; white-space: nowrap;"></td>
					<td style="text-align: center; white-space: nowrap;"></td>
				</tr>

				<tr>
					<td id="ants_deployment_uhf_b_name" style="text-align: center;">$nbsp;</td>
					<td id="ants_deployment_uhf_b_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
					<td style="text-align: center; white-space: nowrap;"></td>
					<td style="text-align: center; white-space: nowrap;"></td>
				</tr>
			</table>
		</div>
		<br />
	</div>
	<!-- End tabs-ants -->

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
					<td id="sw_sequence_number_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_sequence_number_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>


				<tr>
					<td id="sw_command_count_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_command_count_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="sw_last_command_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_last_command_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="sw_command_success_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_command_success_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="sw_valid_asib_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_valid_asib_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="sw_valid_eps_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_valid_eps_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				<tr>
					<td id="sw_valid_pa_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_valid_pa_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				<tr>
					<td id="sw_valid_rf_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_valid_rf_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="sw_valid_mse_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_valid_mse_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="sw_valid_ants_bus_a_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_valid_ants_bus_a_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="sw_valid_ants_bus_b_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_valid_ants_bus_b_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="sw_eclipse_mode_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_eclipse_mode_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				<tr>
					<td id="sw_safe_mode_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_safe_mode_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
				<tr>
					<td id="sw_hardware_abf_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_hardware_abf_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="sw_software_abf_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_software_abf_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>

				<tr>
					<td id="sw_deployment_wait_name" style="text-align: center;">$nbsp;</td>
					<td id="sw_deployment_wait_value"
						style="text-align: center; white-space: nowrap;">$nbsp;</td>
				</tr>
			</table>
		</div>
		<br />
	</div>
	<!-- End tabs-sw -->
</div>