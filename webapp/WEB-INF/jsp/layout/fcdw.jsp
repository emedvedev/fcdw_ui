<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!--

Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Title      : Harvest
Version    : 1.0
Released   : 20081106
Description: A two-column, fixed-width and lightweight template ideal for 1024x768 resolutions. Suitable for blogs and small websites.
Adapted    : 20130328 - Dave Johnson, G4DPZ, AMSAT-UK

-->

<html>
<head>
<title><tiles:getAsString name="page-title" /></title>

<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="robots" content="index,follow">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Language" content="en" />
<meta http-equiv="PRAGMA" content="NO-CACHE">

<meta name="keywords"
	content="funcube, live tracking, current position, ham radio, amateur radio, passes prediction, mission data, telemetry, beacon, space center, amsat-uk, amsat-nl" />
<meta name="description"
	content="FUNcube is a CubeSat built by ISIS, AMSAT-UK and AMSAT-NL. This Web site allows the public, the press and the amateur radio operators to obtain live information about the satellite." />
<meta name="copyright" content="© 2013 AMSAT-UK" />
<meta name="publisher" content="AMSAT-UK / AMSAT-NL - FUNcube" />
<meta name="author" content="David A Johnson, G4DPZ, AMSAT-UK" />
<meta name="verify-v1" content="" />

<!-- jQuery -->
<script src="<c:url value='/js/jquery-1.10.2.min.js'/>"
	type="text/javascript"></script>
<script
	src="<c:url value='/js/jquery-ui-1.8.16.min.js'/>"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/jquery-ui-1.8.16.css'/>" />
	
<script
	src="<c:url value='/js/highcharts.js'/>"
	type="text/javascript"></script>
<script
	src="<c:url value='/js/exporting.js'/>"
	type="text/javascript"></script>

<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/css/fcdw.css'/>">

<tiles:insertAttribute name="head-scripts" />

</head>
<body>
	
	<tiles:insertAttribute name="header" />

	<div id="menu-wrap">
		<div id="menu">
			<tiles:insertAttribute name="menu" />
		</div>
	</div>

	<div id="page-wrap">
		<div id="page">
			<div id="content-wrap">
				<div id="content">
					<tiles:insertAttribute name="main" />
					<b>&nbsp;</b>
				</div>
			</div>
			<!-- End content-wrap -->
			<!-- end content -->
			<ul id="sidebar">
				<li>
					<h2>Navigation</h2>
					<ul>
						<c:choose>
							<c:when test="${satelliteId == null}">
								<li><a href="<c:url value='/realtime.html?satelliteId=2'/>">Real Time Data</a></li>
								<li><a href="<c:url value='/highres.html?satelliteId=2'/>">High Resolution Data</a></li>
								<li><a href="<c:url value='/wod.html?satelliteId=2'/>">Whole Orbit Data</a></li></li>
								<li><a href="<c:url value='/fitter.html?satelliteId=2'/>">Fitter Messages</a></li>
								<li><a href="<c:url value='/radio.html?satelliteId=2'/>">Amateur Radio Info</a></li>
								<li><a href="<c:url value='/satmap.html?satelliteId=2'/>">Satellite Position</a></li>
								<li><a href="<c:url value='/ranking.html?satelliteId=2'/>">Upload Ranking</a></li>
								<li><a href="http://www.funcube.org.uk" target="_blank">About</a></li>
							</c:when>
							<c:when test="${satelliteId == 1}">
								<li><a href="<c:url value='/realtimefc2.html?satelliteId=1'/>">Real Time Data</a></li>
								<!--
								<li><a href="<c:url value='/highres.html?satelliteId=1'/>">High Resolution Data</a></li>
								<li><a href="<c:url value='/wod.html?satelliteId=1'/>">Whole Orbit Data</a></li></li>
								-->
								<li><a href="<c:url value='/fitter.html?satelliteId=1'/>">Fitter Messages</a></li>
								<!--
								<li><a href="<c:url value='/radio.html?satelliteId=1'/>">Amateur Radio Info</a></li>
								<li><a href="<c:url value='/satmap.html?satelliteId=1'/>">Satellite Position</a></li>
								-->
								<li><a href="<c:url value='/ranking.html?satelliteId=1'/>">Upload Ranking</a></li>
								<!--
								<li><a href="http://www.funcube.org.uk" target="_blank">About</a></li>
								-->
							</c:when>
							<c:otherwise>
								<li><a href="<c:url value='/realtime.html?satelliteId=${satelliteId}'/>">Real Time Data</a></li>
								<li><a href="<c:url value='/highres.html?satelliteId=${satelliteId}'/>">High Resolution Data</a></li>
								<li><a href="<c:url value='/wod.html?satelliteId=${satelliteId}'/>">Whole Orbit Data</a></li>
								<li><a href="<c:url value='/fitter.html?satelliteId=${satelliteId}'/>">Fitter Messages</a></li>
								<li><a href="<c:url value='/radio.html?satelliteId=${satelliteId}'/>">Amateur Radio Info</a></li>
								<!-- <li>Observer Map</li> -->
								<li><a href="<c:url value='/satmap.html?satelliteId=${satelliteId}'/>">Satellite Position</a></li>
								<li><a href="<c:url value='/ranking.html?satelliteId=${satelliteId}'/>">Upload Ranking</a></li>
								<li><a href="http://www.funcube.org.uk" target="_blank">About</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</li>

				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li>
						<h2>Admin</h2>
						<ul>
							<li><a href="<c:url value='/admin/cctDebug.html?satelliteId=${satelliteId}'/>">CCT Debug</a></li>
						</ul>
					</li>
				</sec:authorize>
				<c:choose>
					<c:when test="${showStatus == 1}">
						<li>
							<h2>Data Providers</h2>
							<ul id="site_list">
							</ul>
						</li>
						<li>
							<h2>Warehouse Info</h2>
							<ul>
								<li id="info_seqno"></li>
								<li id="info_packet_count"></li>
							</ul>
						</li>
						<c:if  test="${satelliteId != 1}">
							<li>
								<h2>Satellite Status</h2>
								<ul>
									<li id="info_satellite_mode"></li>
									<li id="info_transponder_state"></li>
								</ul>
							</li>
						</c:if>
					</c:when>
				</c:choose>
			</ul>
			<!-- end sidebar -->
			<br style="clear: both;" />
		</div>
		<!-- End page -->
	</div>
	<!-- End page-wrap -->

	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
<head>
	<meta http-equiv="PRAGMA" content="NO-CACHE">
</head>
</html>
