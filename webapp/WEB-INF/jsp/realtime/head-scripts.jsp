<script>
	var handleRealtime = function (data) {
		$("#info_seqno").text("Seq. No.: " + data.sequenceNumber);
		$("#eps_created_date").text("Latest update at: " + data.lastUpdated);
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
		
		$("#asib_created_date").text("Latest update at: " + data.lastUpdated);
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
		
	}
	
	function refreshDiv(){
		
		$.ajax({
			  url: "/fcdw_ui/ui/realtime/${satelliteId}.json",
		      type: 'GET',
		      success : handleRealtime
			 });
	}
</script>