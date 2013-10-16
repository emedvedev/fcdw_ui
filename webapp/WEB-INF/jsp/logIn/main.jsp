<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<h2>Login</h2>
<p>Please enter your callsign or nickname and password below.</p>
<p>If this is you first login, enter the authorisation code sent in
	your registration email.</p>
<p>
	If you don't have an account, please <a
		href="<c:url value='/register.html?satelliteId=2'/>">register</a>.
</p>

<form id="login-form"
	action="<c:url value='/j_spring_security_check?satelliteId=2'/>"
	method="post">
	<div>
		<table>
			<tr>
				<td style="width: 70px;">Email address:</td>
				<td><input id="username" name="j_username" type="text" value="" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input id="password" name="j_password" type="password" /></td>
			</tr>
			<c:if test="${param.error == 'true'}">
				<tr>
					<td colspan="2" style="color: red; font-size: small;"><spring:message code="loginPage.authenticationFailure"/></td>
				</tr>
			</c:if>
			<tr>
				<td>Auth. Code:</td>
				<td><input id="authCode" name="authCode" type="text" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input id="rememberMe" name="rememberMe" type="checkbox"
					value="true" /><input name="rememberMe" type="hidden"
					value="false" /> Remember me?</td>
			</tr>
			<tr>
				<td></td>
				<td><button type="submit">Login</button></td>
			</tr>
		</table>
	</div>
</form>
