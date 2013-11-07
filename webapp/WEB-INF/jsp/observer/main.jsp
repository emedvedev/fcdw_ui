<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/googlemaps.tld" prefix="googlemaps" %> 
<h2>Observers</h2>
<googlemaps:map id="map" width="250" height="300" version="2" type="STREET" 
		zoom="12">
  	<googlemaps:key domain="localhost" key="xxxx"/>
  	<googlemaps:point id="point1" address="74 Connors Lane" city="Elkton" 
  			state="MD" zipcode="21921" country="US"/>
	<googlemaps:marker id="marker1" point="point1"/>
</googlemaps:map>