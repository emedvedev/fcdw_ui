<script type="text/javascript">
	$(document).ready(function(){
		displaygraph();
		displaygraph(refreshDiv, 300000); 
	});
	
	
	var options = {
        chart: {
            renderTo: 'highres-container',
            type: 'line'
        },
		title: {
			text: 'High Resolution Data',
			style: {
				margin: '10px 100px 0 0' // center it
			}
		},
        series: [{}]
    };

    $.getJSON('/fcdw_ui/ui/highres/${satelliteId}.json', function(data) {
        options.series = data.series;
        var chart = new Highcharts.Chart(options);
    });
	
/*
	var chart;
	function displaygraph() {
	 $.getJSON("/fcdw_ui/ui/wod/${satelliteId}.json", function(json) {
	  if(chart) chart.destroy();
	  	chart = new Highcharts.Chart({
	  		chart: {
				renderTo: 'wod-container',
				defaultSeriesType: 'line',
				margin: [50, 150, 60, 80]
			},
			title: {
				text: 'Whole Orbit Data',
				style: {
					margin: '10px 100px 0 0' // center it
				}
			},
			xAxis: {
				title: {
					text: 'Minutes'
				}
			},
			yAxis: {
				title: {
					text: 'Temperature (°C)'
				},
				plotLines: [{
					value: 0,
					width: 1,
					color: '#808080'
				}]
			},
			tooltip: {
				formatter: function() {
		                return '<b>'+ this.series.name +'</b><br/>'+
						this.x +': '+ this.y +'°C';
				}
			},
			legend: {
				layout: 'vertical',
				style: {
					left: 'auto',
					bottom: 'auto',
					right: '10px',
					top: '100px'
				}
			}
	  	});
	  	chart.series = json.series;
	 })};
	  	*/
</script>