<script>
	var handleData = function (data) {
		$("#info_seqno").text("Seq. No.: " + data.sequenceNumber);
	}
	
	function refreshDiv(){
	$.ajax({
	  url: "/fcdw_ui/data/info/${satelliteId}.json",
      type: 'GET',
      success : handleData
	 });
	}
</script>