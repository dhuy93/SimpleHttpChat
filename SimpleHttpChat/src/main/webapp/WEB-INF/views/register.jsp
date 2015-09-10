<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register page</title>
</head>
<body>

	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>

	<h2>Register</h2>
	<form:form method="POST" action="register/submit">
		<table>
			<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input path="email"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password"></form:password></td>
			</tr>
			<tr>
				<td><form:label path="firstname">First name</form:label></td>
				<td><form:input path="firstname"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="lastname">Last name</form:label></td>
				<td><form:input path="lastname"></form:input></td>
			</tr>
			<tr>
				<td colspan=2><input type="submit" value="Register"/></td>
			</tr>
		</table>
	</form:form>

</body>
</html>