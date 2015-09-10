<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/chatting_script.js"/>"></script>
<title>Chat window</title>
</head>
<body>

	<c:choose>
		<c:when test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:when>
		<c:otherwise>
			<table id="items">
				<tr id="tr0">
					<td class="time"></td>
					<td class="from"></td>
					<td class="msgContent"></td>
				</tr>
			</table>
			<table>
				<tr>
					<td><label style="display: none;">content</label></td>
					<td><input id="newContent"></input></td>
				</tr>
				<tr>
					<td><input style="display: none;" id="buffer"></input></td>
					<td><input style="display: none;" value="${palEmail}"
						id="palEmailInput"></input></td>
				</tr>
				<tr>
					<td colspan="2"><button id="send" onclick="onClickSendBtn()">Send</button></td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>


</body>
</html>