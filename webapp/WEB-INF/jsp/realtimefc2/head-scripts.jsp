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
		
		$("#site_list").html('');
	    var items = [];
	    $.each(data.siteList, function (id, site) {
	        items.push('<li>' + site + '</li>');
	    });  
	    $("#site_list").append(items.join(''));
		
		
	}
	
	function refreshDiv(){
		
		$.ajax({
			  url: "/fcdw_ui/ui/realtimefc2/${satelliteId}.json",
		      type: 'GET',
		      success : handleRealtime
			 });
	}
</script>