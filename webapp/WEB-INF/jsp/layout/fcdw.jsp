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

<meta name="keywords"
	content="funcube, live tracking, current position, ham radio, amateur radio, passes prediction, mission data, telemetry, beacon, space center, amsat-uk, amsat-nl" />
<meta name="description"
	content="FUNcube is a CubeSat built by ISIS, AMSAT-UK and AMSAT-NL. This Web site allows the public, the press and the amateur radio operators to obtain live information about the satellite." />
<meta name="copyright" content="© 2013 AMSAT-UK" />
<meta name="publisher" content="AMSAT-UK / AMSAT-NL - FUNcube" />
<meta name="author" content="David A Johnson, G4DPZ, AMSAT-UK" />
<meta name="verify-v1" content="" />

<tiles:insertAttribute name="head-scripts" />

<!-- jQuery -->
<script src="<c:url value='/js/jquery-1.10.2.min.js'/>"
	type="text/javascript"></script>
<script
	src="<c:url value='/js/jquery-ui-1.8.16.min.js'/>"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/jquery-ui-1.8.16.css'/>" />

<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/css/fcdw.css'/>">

<script type="text/javascript">
	$(function() {
		$('#tabs').tabs();
	});
</script>

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
								<li>High Resolution Data</li>
								<li>Whole Orbit Data</li>
								<li><a href="<c:url value='/fitter.html?satelliteId=2'/>">Fitter Messages</a></li>
								<li>Amateur Radio Info</li>
								<li>Observer Map</li>
								<li><a href="<c:url value='/ranking.html?satelliteId=2'/>">Upload Ranking</a></li>
								<li><a href="<c:url value='/home.html?satelliteId=2'/>">About</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="<c:url value='/realtime.html?satelliteId=${satelliteId}'/>">Real Time Data</a></li>
								<li>High Resolution Data</li>
								<li>Whole Orbit Data</li>
								<li><a href="<c:url value='/fitter.html?satelliteId=${satelliteId}'/>">Fitter Messages</a></li>
								<li>Amateur Radio Info</li>
								<li>Observer Map</li>
								<li><a href="<c:url value='/ranking.html?satelliteId=${satelliteId}'/>">Upload Ranking</a></li>
								<li><a href="<c:url value='/home.html?satelliteId=${satelliteId}'/>">About</a></li>
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
				<c:if test="${fn:length(siteList) > 0}">
					<li>
						<h2>Data Providers</h2>
						<ul>
							<c:forEach var="site" items="${siteList}">
								<li>${site}</li>
							</c:forEach>
						</ul>
					</li>
				</c:if>
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
	<!-- End footer -->
</body>
</html>
