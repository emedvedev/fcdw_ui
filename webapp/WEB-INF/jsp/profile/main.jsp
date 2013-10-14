<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h2>User Profile</h2>
<p>This page allows you to update certain attributes of your profile.</p>
<form:form id="profileForm" commandName="request" method="POST" action="" enctype="multipart/form-data">
	<ol>
		<li>
			<label>Site Name:</label>
 			<form:input  id="siteName" path="siteName" cssErrorClass="errorControl"/>
 			<form:errors class="errors" path="siteName"/>
		</li>
	</ol>
</form:form>