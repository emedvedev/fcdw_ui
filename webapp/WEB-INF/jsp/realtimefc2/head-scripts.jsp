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
		
		$("#eps_created_date").text("Updated at: " + data.lastUpdated);
		$("#amac_created_date").text("Updated at: " + data.lastUpdated);
		$("#pa_created_date").text("Updated at: " + data.lastUpdated);
		$("#rf_created_date").text("Updated at: " + data.lastUpdated);
		$("#sw_created_date").text("Updated at: " + data.lastUpdated);
		
		$("#site_list").html('');
	    var items = [];
	    $.each(data.siteList, function (id, site) {
	        items.push('<li>' + site + '</li>');
	    });  
	    $("#site_list").append(items.join(''));
	    
	    $("#eps_sol_1_name").text(data.epsValues[0].name);
		$("#eps_sol_1_value").text(data.epsValues[0].value);
		$("#eps_sol_1_min").text(data.epsValues[0].min);
		$("#eps_sol_1_max").text(data.epsValues[0].max);
	    
	    $("#eps_sol_2_name").text(data.epsValues[1].name);
		$("#eps_sol_2_value").text(data.epsValues[1].value);
		$("#eps_sol_2_min").text(data.epsValues[1].min);
		$("#eps_sol_2_max").text(data.epsValues[1].max);
	    
	    $("#eps_sol_3_name").text(data.epsValues[2].name);
		$("#eps_sol_3_value").text(data.epsValues[2].value);
		$("#eps_sol_3_min").text(data.epsValues[2].min);
		$("#eps_sol_3_max").text(data.epsValues[2].max);
	    
	    $("#eps_sol_4_name").text(data.epsValues[3].name);
		$("#eps_sol_4_value").text(data.epsValues[3].value);
		$("#eps_sol_4_min").text(data.epsValues[3].min);
		$("#eps_sol_4_max").text(data.epsValues[3].max);
	    
	    $("#eps_sol_5_name").text(data.epsValues[4].name);
		$("#eps_sol_5_value").text(data.epsValues[4].value);
		$("#eps_sol_5_min").text(data.epsValues[4].min);
		$("#eps_sol_5_max").text(data.epsValues[4].max);
	    
	    $("#eps_sol_6_name").text(data.epsValues[5].name);
		$("#eps_sol_6_value").text(data.epsValues[5].value);
		$("#eps_sol_6_min").text(data.epsValues[5].min);
		$("#eps_sol_6_max").text(data.epsValues[5].max);
	    
	    $("#eps_sol_7_name").text(data.epsValues[6].name);
		$("#eps_sol_7_value").text(data.epsValues[6].value);
		$("#eps_sol_7_min").text(data.epsValues[6].min);
		$("#eps_sol_7_max").text(data.epsValues[6].max);
	    
	    $("#eps_sol_8_name").text(data.epsValues[7].name);
		$("#eps_sol_8_value").text(data.epsValues[7].value);
		$("#eps_sol_8_min").text(data.epsValues[7].min);
		$("#eps_sol_8_max").text(data.epsValues[7].max);
	    
	    $("#eps_sol_9_name").text(data.epsValues[8].name);
		$("#eps_sol_9_value").text(data.epsValues[8].value);
		$("#eps_sol_9_min").text(data.epsValues[8].min);
		$("#eps_sol_9_max").text(data.epsValues[8].max);
	    
	    $("#eps_sol_10_name").text(data.epsValues[9].name);
		$("#eps_sol_10_value").text(data.epsValues[9].value);
		$("#eps_sol_10_min").text(data.epsValues[9].min);
		$("#eps_sol_10_max").text(data.epsValues[9].max);
	    
	    $("#eps_sol_11_name").text(data.epsValues[10].name);
		$("#eps_sol_11_value").text(data.epsValues[10].value);
		$("#eps_sol_11_min").text(data.epsValues[10].min);
		$("#eps_sol_11_max").text(data.epsValues[10].max);
	    
	    $("#eps_sol_12_name").text(data.epsValues[11].name);
		$("#eps_sol_12_value").text(data.epsValues[11].value);
		$("#eps_sol_12_min").text(data.epsValues[11].min);
		$("#eps_sol_12_max").text(data.epsValues[11].max);
		
		$("#eps_bat_1_name").text(data.epsValues[12].name);
		$("#eps_bat_1_value").text(data.epsValues[12].value);
		$("#eps_bat_1_min").text(data.epsValues[12].min);
		$("#eps_bat_1_max").text(data.epsValues[12].max);
		
		$("#eps_bat_2_name").text(data.epsValues[13].name);
		$("#eps_bat_2_value").text(data.epsValues[13].value);
		$("#eps_bat_2_min").text(data.epsValues[13].min);
		$("#eps_bat_2_max").text(data.epsValues[13].max);
		
		$("#eps_bat_3_name").text(data.epsValues[14].name);
		$("#eps_bat_3_value").text(data.epsValues[14].value);
		$("#eps_bat_3_min").text(data.epsValues[14].min);
		$("#eps_bat_3_max").text(data.epsValues[14].max);
		
		$("#eps_bat_4_name").text(data.epsValues[15].name);
		$("#eps_bat_4_value").text(data.epsValues[15].value);
		$("#eps_bat_4_min").text(data.epsValues[15].min);
		$("#eps_bat_4_max").text(data.epsValues[15].max);
		
		$("#eps_bat_5_name").text(data.epsValues[16].name);
		$("#eps_bat_5_value").text(data.epsValues[16].value);
		$("#eps_bat_5_min").text(data.epsValues[16].min);
		$("#eps_bat_5_max").text(data.epsValues[16].max);
		
		$("#eps_bat_6_name").text(data.epsValues[17].name);
		$("#eps_bat_6_value").text(data.epsValues[17].value);
		$("#eps_bat_6_min").text(data.epsValues[17].min);
		$("#eps_bat_6_max").text(data.epsValues[17].max);
		
		$("#eps_bat_7_name").text(data.epsValues[18].name);
		$("#eps_bat_7_value").text(data.epsValues[18].value);
		$("#eps_bat_7_min").text(data.epsValues[18].min);
		$("#eps_bat_7_max").text(data.epsValues[18].max);
		
		$("#eps_bat_8_name").text(data.epsValues[19].name);
		$("#eps_bat_8_value").text(data.epsValues[19].value);
		$("#eps_bat_8_min").text(data.epsValues[19].min);
		$("#eps_bat_8_max").text(data.epsValues[19].max);
		
		$("#eps_bat_9_name").text(data.epsValues[20].name);
		$("#eps_bat_9_value").text(data.epsValues[20].value);
		$("#eps_bat_9_min").text(data.epsValues[20].min);
		$("#eps_bat_9_max").text(data.epsValues[20].max);
		
		
	}
	
	function refreshDiv(){
		
		$.ajax({
			  url: "/fcdw_ui/ui/realtimefc2/${satelliteId}.json",
		      type: 'GET',
		      success : handleRealtime
			 });
	}
</script>