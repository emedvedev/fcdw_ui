<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h2>Register</h2>
<p>In order to be able to upload data to the warehouse from the FUNcube Dashboard program, you will need to register some details.</p>
<p>As part of the registration process, we will send and 'authorisation key' to the email address you have entered. When you receive this, please log in giving the 'authorisation key' you received.</p>

<c:url var="url" value="/register.html" /> 
<form:form action = "${url}" commandName="registerUserRequest">
	<table>
		<tr>
			<td width="217px">
    			<label>Callsign/School/Name:</label>
			</td>
			<td>
    			<form:input path="siteName" id="siteNameInput" 
    			title="E.g.: My_School_Name"></form:input>
			</td>
			<td>
    			<font color="red"><form:errors path="siteName"></form:errors></font>
			</td>
		</tr>
		<tr>
			<td width="217px">
    			<label>Email:</label>
			</td>
			<td>
    			<form:input path="email1" id="email1Input"></form:input>
			</td>
			<td>
    			<font color="red"><form:errors path="email1"></form:errors></font>
			</td>
		</tr>
		<tr>
			<td width="217px">
    			<label>Email (again):</label>
			</td>
			<td>
    			<form:input path="email2" id="email2Input"></form:input>
			</td>
			<td>
    			<font color="red"><form:errors path="email2"></form:errors></font>
			</td>
		</tr>
		<tr>
			<td width="217px">
    			<label>Latitude (decimal) N(+) S(-):</label>
			</td>
			<td>
    			<form:input path="latitude" id="latitudeInput" 
    			title="E.g.: 52.4670"></form:input>
			</td>
			<td>
    			<font color="red"><form:errors path="latitude"></form:errors></font>
			</td>
		</tr>
		<tr>
			<td width="217px">
    			<label>Longitude (decimal) E(+) W(-):</label>
			</td>
			<td>
    			<form:input path="longitude" id="longitudeInput" 
    			title="E.g.: -2.022"></form:input>
			</td>
			<td>
    			<font color="red"><form:errors path="longitude"></form:errors></font>
			</td>
		</tr>
		<tr>
			<td width="217px">
    			<label>Password:</label>
			</td>
			<td>
    			<form:password path="password1" id="password1Input"></form:password>
			</td>
			<td>
    			<font color="red"><form:errors path="password1"></form:errors></font>
			</td>
		</tr>
		<tr>
			<td width="217px">
    			<label>Password (again):</label>
			</td>
			<td>
    			<form:password path="password2" id="password2Input"></form:password>
			</td>
			<td>
    			<font color="red"><form:errors path="password2"></form:errors></font>
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td></td>
			<td>
    			<input type="submit" value="Submit" style="width: 100px" />
			</td>
			<td></td>
		</tr>
	</table>
</form:form>