<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<c:if test="${empty sessionScope['aid']}">
	<c:redirect url="admin.jsp" />
</c:if>

<%-- <c:if test="${sessionScope['adminid']!=null}"></c:if> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Societatis admin panel...</title>
</head>
<body>
	<div>
		<div style="float: left;">
			<form action="adminViewSearchUserList.jsp">
				<input type="text" name="name" value="" placeholder="Type user name"
					required="required"> <input type="submit" value="Search"
					name="submit">
			</form>
		</div>
		<div style="float: right;">
			<a href="alogout">Log Out</a>
		</div>
	</div>
	<hr><br><hr>
	<table cellpadding="3">

		<c:set var="allusers"
			value="${sessionScope['userBean'].getUserByName(param.name)}" />
		<h1>Search result</h1>
		<c:forEach items="${allusers}" var="user">
			<c:set var="userpro"
				value="${sessionScope['userBean'].getUserProfileDetailsById(user.emailId)}" />
			<tr>
				<td rowspan="2"><a href="adminwatch?email=${user.emailId}"><img
						src="data:image/jpeg;base64,${sessionScope['userBean'].getUserProfileDetailsById(userpro.user.emailId).imageAsString}"
						height="53" width="53px" /></a></td>
				<td><a href="adminwatch?email=${user.emailId}" target="_blank">${user.name}</a>
				</td>
				<td rowspan="2"><a href="deleteuser?id=${user.emailId}">Delete</a>
				</td>
			</tr>
			<tr>
				<td>${user.emailId}</td>
			</tr>
		</c:forEach>
	</table>
	<hr>

	<h3>${requestScope['msg']}</h3>
</body>
</html>