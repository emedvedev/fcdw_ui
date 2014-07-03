<script type="text/javascript">
	$(function() {
		$('#tabs').tabs();
	});
</script>

<script>
	$(document).ready(function(){
		refreshDiv();
	    setInterval(refreshDiv, 10000); 
	});
</script>

<script>
	var handleRealtime = function (data) {
		$("#info_seqno").text("Seq. No.: " + data.sequenceNumber);
		$("#info_packet_count").text("Packets: " + data.packetCount);
		$("#info_satellite_mode").text("Mode switching: " + data.satelliteMode);
		$("#info_transponder_state").text("Transponder state: " + data.transponderState);
		$("#eps_created_date").text("Uploaded at: " + data.lastUpdated + ", MinMax from: " + data.minmaxReset);
		$("#eps_lat_long").text("Satellite Latitude: " + data.latitude + ", Longitude: " + data.longitude);
		$("#eps_sol_volts_x_name").text(data.epsValues[0].name);
		$("#eps_sol_volts_x_value").text(data.epsValues[0].value);
		$("#eps_sol_volts_x_min").text(data.epsValues[0].min);
		$("#eps_sol_volts_x_max").text(data.epsValues[0].max);
		$("#eps_sol_volts_y_name").text(data.epsValues[1].name);
		$("#eps_sol_volts_y_value").text(data.epsValues[1].value);
		$("#eps_sol_volts_y_min").text(data.epsValues[1].min);
		$("#eps_sol_volts_y_max").text(data.epsValues[1].max);
		$("#eps_sol_volts_z_name").text(data.epsValues[2].name);
		$("#eps_sol_volts_z_value").text(data.epsValues[2].value);
		$("#eps_sol_volts_z_min").text(data.epsValues[2].min);
		$("#eps_sol_volts_z_max").text(data.epsValues[2].max);
		$("#eps_total_photo_current_name").text(data.epsValues[3].name);
		$("#eps_total_photo_current_value").text(data.epsValues[3].value);
		$("#eps_total_photo_current_min").text(data.epsValues[3].min);
		$("#eps_total_photo_current_max").text(data.epsValues[3].max);
		$("#eps_battery_volts_name").text(data.epsValues[4].name);
		$("#eps_battery_volts_value").text(data.epsValues[4].value);
		$("#eps_battery_volts_min").text(data.epsValues[4].min);
		$("#eps_battery_volts_max").text(data.epsValues[4].max);
		$("#eps_total_system_current_name").text(data.epsValues[5].name);
		$("#eps_total_system_current_value").text(data.epsValues[5].value);
		$("#eps_total_system_current_min").text(data.epsValues[5].min);
		$("#eps_total_system_current_max").text(data.epsValues[5].max);
		$("#eps_reboot_count_name").text(data.epsValues[6].name);
		$("#eps_reboot_count_value").text(data.epsValues[6].value);
		$("#eps_reboot_count_min").text(data.epsValues[6].min);
		$("#eps_reboot_count_max").text(data.epsValues[6].max);
		$("#eps_software_errors_name").text(data.epsValues[7].name);
		$("#eps_software_errors_value").text(data.epsValues[7].value);
		$("#eps_software_errors_min").text(data.epsValues[7].min);
		$("#eps_software_errors_max").text(data.epsValues[7].max);
		$("#eps_boost_temp_x_name").text(data.epsValues[8].name);
		$("#eps_boost_temp_x_value").text(data.epsValues[8].value);
		$("#eps_boost_temp_x_min").text(data.epsValues[8].min);
		$("#eps_boost_temp_x_max").text(data.epsValues[8].max);
		$("#eps_boost_temp_y_name").text(data.epsValues[9].name);
		$("#eps_boost_temp_y_value").text(data.epsValues[9].value);
		$("#eps_boost_temp_y_min").text(data.epsValues[9].min);
		$("#eps_boost_temp_y_max").text(data.epsValues[9].max);
		$("#eps_boost_temp_z_name").text(data.epsValues[10].name);
		$("#eps_boost_temp_z_value").text(data.epsValues[10].value);
		$("#eps_boost_temp_z_min").text(data.epsValues[10].min);
		$("#eps_boost_temp_z_max").text(data.epsValues[10].max);
		$("#eps_battery_temp_name").text(data.epsValues[11].name);
		$("#eps_battery_temp_value").text(data.epsValues[11].value);
		$("#eps_battery_temp_min").text(data.epsValues[11].min);
		$("#eps_battery_temp_max").text(data.epsValues[11].max);
		$("#eps_latch_up_5_name").text(data.epsValues[12].name);
		$("#eps_latch_up_5_value").text(data.epsValues[12].value);
		$("#eps_latch_up_5_min").text(data.epsValues[12].min);
		$("#eps_latch_up_5_max").text(data.epsValues[12].max);
		$("#eps_latch_up_3_name").text(data.epsValues[13].name);
		$("#eps_latch_up_3_value").text(data.epsValues[13].value);
		$("#eps_latch_up_3_min").text(data.epsValues[13].min);
		$("#eps_latch_up_3_max").text(data.epsValues[13].max);
		$("#eps_reset_cause_name").text(data.epsValues[14].name);
		$("#eps_reset_cause_value").text(data.epsValues[14].value);
		$("#eps_reset_cause_min").text(data.epsValues[14].min);
		$("#eps_reset_cause_max").text(data.epsValues[14].max);
		$("#eps_ppt_mode_name").text(data.epsValues[15].name);
		$("#eps_ppt_mode_value").text(data.epsValues[15].value);
		$("#eps_ppt_mode_min").text(data.epsValues[15].min);
		$("#eps_ppt_mode_max").text(data.epsValues[15].max);
		
		$("#asib_created_date").text("Uploaded at: " + data.lastUpdated + ", MinMax from: " + data.minmaxReset);
		$("#asib_lat_long").text("Satellite Latitude: " + data.latitude + ", Longitude: " + data.longitude);
		$("#asib_sun_sensor_x_name").text(data.asibValues[0].name);
		$("#asib_sun_sensor_x_value").text(data.asibValues[0].value);
		$("#asib_sun_sensor_x_min").text(data.asibValues[0].min);
		$("#asib_sun_sensor_x_max").text(data.asibValues[0].max);
		$("#asib_sun_sensor_y_name").text(data.asibValues[1].name);
		$("#asib_sun_sensor_y_value").text(data.asibValues[1].value);
		$("#asib_sun_sensor_y_min").text(data.asibValues[1].min);
		$("#asib_sun_sensor_y_max").text(data.asibValues[1].max);
		$("#asib_sun_sensor_z_name").text(data.asibValues[2].name);
		$("#asib_sun_sensor_z_value").text(data.asibValues[2].value);
		$("#asib_sun_sensor_z_min").text(data.asibValues[2].min);
		$("#asib_sun_sensor_z_max").text(data.asibValues[2].max);
		$("#asib_sol_temp_x_plus_name").text(data.asibValues[3].name);
		$("#asib_sol_temp_x_plus_value").text(data.asibValues[3].value);
		$("#asib_sol_temp_x_plus_min").text(data.asibValues[3].min);
		$("#asib_sol_temp_x_plus_max").text(data.asibValues[3].max);
		$("#asib_sol_temp_x_minus_name").text(data.asibValues[4].name);
		$("#asib_sol_temp_x_minus_value").text(data.asibValues[4].value);
		$("#asib_sol_temp_x_minus_min").text(data.asibValues[4].min);
		$("#asib_sol_temp_x_minus_max").text(data.asibValues[4].max);
		$("#asib_sol_temp_y_plus_name").text(data.asibValues[5].name);
		$("#asib_sol_temp_y_plus_value").text(data.asibValues[5].value);
		$("#asib_sol_temp_y_plus_min").text(data.asibValues[5].min);
		$("#asib_sol_temp_y_plus_max").text(data.asibValues[5].max);
		$("#asib_sol_temp_y_minus_name").text(data.asibValues[6].name);
		$("#asib_sol_temp_y_minus_value").text(data.asibValues[6].value);
		$("#asib_sol_temp_y_minus_min").text(data.asibValues[6].min);
		$("#asib_sol_temp_y_minus_max").text(data.asibValues[6].max);
		$("#asib_bus_volts_three_name").text(data.asibValues[7].name);
		$("#asib_bus_volts_three_value").text(data.asibValues[7].value);
		$("#asib_bus_volts_three_min").text(data.asibValues[7].min);
		$("#asib_bus_volts_three_max").text(data.asibValues[7].max);
		$("#asib_bus_current_three_name").text(data.asibValues[8].name);
		$("#asib_bus_current_three_value").text(data.asibValues[8].value);
		$("#asib_bus_current_three_min").text(data.asibValues[8].min);
		$("#asib_bus_current_three_max").text(data.asibValues[8].max);
		$("#asib_bus_volts_five_name").text(data.asibValues[9].name);
		$("#asib_bus_volts_five_value").text(data.asibValues[9].value);
		$("#asib_bus_volts_five_min").text(data.asibValues[9].min);
		$("#asib_bus_volts_five_max").text(data.asibValues[9].max);
		
		$("#rf_created_date").text("Uploaded at: " + data.lastUpdated + ", MinMax from: " + data.minmaxReset);
		$("#rf_lat_long").text("Satellite Latitude: " + data.latitude + ", Longitude: " + data.longitude);
		$("#rf_receive_doppler_name").text(data.rfValues[0].name);
		$("#rf_receive_doppler_value").text(data.rfValues[0].value);
		$("#rf_receive_doppler_min").text(data.rfValues[0].min);
		$("#rf_receive_doppler_max").text(data.rfValues[0].max);
		$("#rf_receive_rssi_name").text(data.rfValues[1].name);
		$("#rf_receive_rssi_value").text(data.rfValues[1].value);
		$("#rf_receive_rssi_min").text(data.rfValues[1].min);
		$("#rf_receive_rssi_max").text(data.rfValues[1].max);
		$("#rf_temperature_name").text(data.rfValues[2].name);
		$("#rf_temperature_value").text(data.rfValues[2].value);
		$("#rf_temperature_min").text(data.rfValues[2].min);
		$("#rf_temperature_max").text(data.rfValues[2].max);
		$("#rf_receive_current_name").text(data.rfValues[3].name);
		$("#rf_receive_current_value").text(data.rfValues[3].value);
		$("#rf_receive_current_min").text(data.rfValues[3].min);
		$("#rf_receive_current_max").text(data.rfValues[3].max);
		$("#rf_transmit_current_three_name").text(data.rfValues[4].name);
		$("#rf_transmit_current_three_value").text(data.rfValues[4].value);
		$("#rf_transmit_current_three_min").text(data.rfValues[4].min);
		$("#rf_transmit_current_three_max").text(data.rfValues[4].max);
		$("#rf_transmit_current_five_name").text(data.rfValues[5].name);
		$("#rf_transmit_current_five_value").text(data.rfValues[5].value);
		$("#rf_transmit_current_five_min").text(data.rfValues[5].min);
		$("#rf_transmit_current_five_max").text(data.rfValues[5].max);
		
		$("#pa_created_date").text("Uploaded at: " + data.lastUpdated + ", MinMax from: " + data.minmaxReset);
		$("#pa_lat_long").text("Satellite Latitude: " + data.latitude + ", Longitude: " + data.longitude);
		$("#pa_forward_power_name").text(data.paValues[0].name);
		$("#pa_forward_power_value").text(data.paValues[0].value);
		$("#pa_forward_power_min").text(data.paValues[0].min);
		$("#pa_forward_power_max").text(data.paValues[0].max);
		$("#pa_reverse_power_name").text(data.paValues[1].name);
		$("#pa_reverse_power_value").text(data.paValues[1].value);
		$("#pa_reverse_power_min").text(data.paValues[1].min);
		$("#pa_reverse_power_max").text(data.paValues[1].max);
		$("#pa_device_temperature_name").text(data.paValues[2].name);
		$("#pa_device_temperature_value").text(data.paValues[2].value);
		$("#pa_device_temperature_min").text(data.paValues[2].min);
		$("#pa_device_temperature_max").text(data.paValues[2].max);
		$("#pa_bus_current_name").text(data.paValues[3].name);
		$("#pa_bus_current_value").text(data.paValues[3].value);
		$("#pa_bus_current_min").text(data.paValues[3].min);
		$("#pa_bus_current_max").text(data.paValues[3].max);

		
		$("#ants_created_date").text("Uploaded at: " + data.lastUpdated + ", MinMax from: " + data.minmaxReset);
		$("#ants_lat_long").text("Satellite Latitude: " + data.latitude + ", Longitude: " + data.longitude);
		$("#ants_temp_zero_name").text(data.antsValues[0].name);
		$("#ants_temp_zero_value").text(data.antsValues[0].value);
		$("#ants_temp_zero_min").text(data.antsValues[0].min);
		$("#ants_temp_zero_max").text(data.antsValues[0].max);
		$("#ants_temp_one_name").text(data.antsValues[1].name);
		$("#ants_temp_one_value").text(data.antsValues[1].value);
		$("#ants_temp_one_min").text(data.antsValues[1].min);
		$("#ants_temp_one_max").text(data.antsValues[1].max);
		$("#ants_deployment_vhf_a_name").text(data.antsValues[2].name);
		$("#ants_deployment_vhf_a_value").text(data.antsValues[2].value);
		$("#ants_deployment_uhf_a_name").text(data.antsValues[3].name);
		$("#ants_deployment_uhf_a_value").text(data.antsValues[3].value);
		$("#ants_deployment_vhf_b_name").text(data.antsValues[4].name);
		$("#ants_deployment_vhf_b_value").text(data.antsValues[4].value);
		$("#ants_deployment_uhf_b_name").text(data.antsValues[5].name);
		$("#ants_deployment_uhf_b_value").text(data.antsValues[5].value);

		$("#sw_created_date").text("Uploaded at: " + data.lastUpdated + ", MinMax from: " + data.minmaxReset);
		$("#sw_lat_long").text("Satellite Latitude: " + data.latitude + ", Longitude: " + data.longitude);
		$("#sw_sequence_number_name").text(data.swValues[0].name);
		$("#sw_sequence_number_value").text(data.swValues[0].value);
		$("#sw_command_count_name").text(data.swValues[1].name);
		$("#sw_command_count_value").text(data.swValues[1].value);
		$("#sw_last_command_name").text(data.swValues[2].name);
		$("#sw_last_command_value").text(data.swValues[2].value);
		$("#sw_command_success_name").text(data.swValues[3].name);
		$("#sw_command_success_value").text(data.swValues[3].value);
		$("#sw_valid_asib_name").text(data.swValues[4].name);
		$("#sw_valid_asib_value").text(data.swValues[4].value);
		$("#sw_valid_eps_name").text(data.swValues[5].name);
		$("#sw_valid_eps_value").text(data.swValues[5].value);
		$("#sw_valid_pa_name").text(data.swValues[6].name);
		$("#sw_valid_pa_value").text(data.swValues[6].value);
		$("#sw_valid_rf_name").text(data.swValues[7].name);
		$("#sw_valid_rf_value").text(data.swValues[7].value);
		$("#sw_valid_mse_name").text(data.swValues[8].name);
		$("#sw_valid_mse_value").text(data.swValues[8].value);
		$("#sw_valid_ants_bus_b_name").text(data.swValues[9].name);
		$("#sw_valid_ants_bus_b_value").text(data.swValues[9].value);
		$("#sw_valid_ants_bus_a_name").text(data.swValues[10].name);
		$("#sw_valid_ants_bus_a_value").text(data.swValues[10].value);
		$("#sw_eclipse_mode_name").text(data.swValues[11].name);
		$("#sw_eclipse_mode_value").text(data.swValues[11].value);
		$("#sw_safe_mode_name").text(data.swValues[12].name);
		$("#sw_safe_mode_value").text(data.swValues[12].value);
		$("#sw_hardware_abf_name").text(data.swValues[13].name);
		$("#sw_hardware_abf_value").text(data.swValues[13].value);
		$("#sw_software_abf_name").text(data.swValues[14].name);
		$("#sw_software_abf_value").text(data.swValues[14].value);
		$("#sw_deployment_wait_name").text(data.swValues[15].name);
		$("#sw_deployment_wait_value").text(data.swValues[15].value);
		
		$("#site_list").html('');
	    var items = [];
	    $.each(data.siteList, function (id, site) {
	        items.push('<li>' + site + '</li>');
	    });  
	    $("#site_list").append(items.join(''));
		
		
	}
	
	function refreshDiv(){
		
		$.ajax({
			  url: "/fcdw_ui/ui/realtime/${satelliteId}.json",
		      type: 'GET',
		      success : handleRealtime
			 });
	}
</script>