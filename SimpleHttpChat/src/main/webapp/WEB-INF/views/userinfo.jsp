<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Information</title>
</head>
<body>
	<h2>Basic information</h2>
	<table>
		<tr>
			<td>Email</td>
			<td>${chatter.email}</td>
		</tr>
		<tr>
			<td>First name</td>
			<td>${chatter.firstname}</td>
		</tr>
		<tr>
			<td>Last name</td>
			<td>${chatter.lastname}</td>
		</tr>
		<tr>
			<td>Friend list</td>
			<td>
				<ul>
					<c:forEach var="email" items="${chatter.emailList}">
						<li><a href="chatWindow/${email}">${email}</a></li>
					</c:forEach>
				</ul>
			</td>
		</tr>
	</table>




	<!-- Logout form -->
	<c:url value="/j_spring_security_logout" var="logoutUrl" />

	<!-- csrt for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm"></form>


	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>


	<h2>
		Welcome : ${pageContext.request.userPrincipal.name} | <a
			href="javascript:formSubmit()"> Logout</a>
	</h2>

</body>
</html>