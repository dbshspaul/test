<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header1.jsp"%>
<div style="margin-left: 150px; margin-top: 25px; margin-right: 25px">
	<h1>Who Liked</h1>
	<c:forEach
		items="${sessionScope['postBean'].nemeOfTheUser_PagePostLikeBy(param.id)}"
		var="name">
		<table>
			<tr>
				<td>${name}</td>
			</tr>
		</table>
	</c:forEach>
</div>
<%@include file="footer.jsp"%>