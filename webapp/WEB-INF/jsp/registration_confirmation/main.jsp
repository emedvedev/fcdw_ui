<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h2>Registration Confirmation</h2>
<p>Thank you for registering to take part in the data upload for the FUNcube project
<br>We have sent an 'authorisation code' to the email address you have entered. 
<br>When you receive this, please log in giving the:
<ul>
	<li>the Callsign/School/Name you chose</li>
	<li>the password you chose</li>
	<li>authorisation code you received</li>
</ul>
Once this has been done, please set up the PC Dashboard in the File&rarr;Settings&rarr;Warehouse menu:

<p><img src='<c:url value="/css/images/warehouse_setup.png" />'>
<br><strong>Site Id:</strong> enter the Callsign/School/Name you chose
<br><strong>Auth Code:</strong> enter the authorisation code you received
<br><strong>URL:</strong> enter <strong>http://data.funcube.org.uk</strong>
<br><strong>Stream data to warehouse:</strong> check the box

<p><strong>Please use the menu on the left side to continue with use of the site</strong></p>

