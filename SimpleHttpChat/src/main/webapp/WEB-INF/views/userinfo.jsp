<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Information</title>
</head>
<body>
	<table>
		<tr>
			<td>Email</td>
			<td>${email}</td>
		</tr>
		<tr>
			<td>First name</td>
			<td>${firstname}</td>
		</tr>
		<tr>
			<td>Last name</td>
			<td>${lastname}</td>
		</tr>
		<tr>
			<td>Friend list</td>
			<td>
				<ul>
					<c:forEach var="email" items="${emaillist}">
						<li><c:out value="${email}"></c:out></li>
					</c:forEach>
				</ul>
			</td>
		</tr>
	</table>
</body>
</html>