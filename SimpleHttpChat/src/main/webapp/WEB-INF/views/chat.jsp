<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chat window</title>
</head>
<body>
	<table>
		<c:forEach var="line" items="${conversation}">
			<tr>
				<td><c:out value="${line}"></c:out></td>
			</tr>
		</c:forEach>
	</table>

	<form:form method="POST" action="sendNewMsg">
		<table>
			<tr>
				<td><form:label path="content" style="display:none;">content</form:label></td>
				<td><form:input path="content"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="receiver" style="display:none;">receiver</form:label></td>
				<td><form:input path="receiver" value="${palEmail}"></form:input></td>
			</tr>
			<tr>
				<td colspan="2"><input name="send" type="submit" value="Send" /></td>
			</tr>
		</table>
		<!-- <textarea name="content" rows="3" cols="30"></textarea> -->
	</form:form>

</body>
</html>