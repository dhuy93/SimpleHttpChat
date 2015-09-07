<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new friend</title>
</head>
<body>
<h2>Add new friend</h2>
<form:form method="post" action="doAddFriend">
	<table>
		<tr>
			<td><form:label path="email">Email of friend:</form:label></td>
			<td><form:input path="email"></form:input></td>
		</tr>
		<tr>
			<td colspan="2"><input name="submit" type="submit" value="Add"></input></td>
		</tr>
	</table>
</form:form>
</body>
</html>