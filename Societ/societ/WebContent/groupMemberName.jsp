<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:set var="memberlst"
		value="${sessionScope['userBean'].getMemberOfTheGroup(param.id)}" />
	<c:forEach items="${memberlst}" var="mem">
		<a href="viewFriend?friendid=${mem.emailId}"><u>${mem.name}</u></a>
	</c:forEach>
</body>
</html>