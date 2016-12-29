<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="header1.jsp"%>

<c:set var="userOb"
	value="${sessionScope['userBean'].getUserDetailsById(param.fid)}" />

<div style="margin-left: 150px; margin-top: 25px; margin-right: 25px">
	<%-- <c:set property="email" target="${sessionScope['userBean']}" value="${requestScope['friendId']}"/> --%>

	<c:set var="userOb"
		value="${sessionScope['userBean'].getUserDetailsById(param.fid)}" />
	<c:set var="userProObj"
		value="${sessionScope['userBean'].getUserProfileDetailsById(param.fid)}" />
	<c:set var="p"
		value="${sessionScope['userBean'].getPrivacyById(param.fid)}" />

	<table>
		<tr>
			<td><div>
					<table>
						<tr>
							<h1>${userOb.name}'s Profile</h1>
						</tr>
						<tr>
							<td><img src="img?email=${userOb.emailId}" width="200"></td>
						</tr>
					</table>
				</div></td>
			<td><div>
					<table align="center">

						<tr>
							<td>Name:</td>
							<td>${userOb.name}</td>
						</tr>
						<tr>
							<td>Email Id:</td>
							<td>${userOb.emailId}</td>
						</tr>
						<tr>
							<td>Gender:</td>
							<td>${userOb.gender}</td>
						</tr>
						<c:if test="${p.dob ==0}">
							<tr>
								<td>Date of Birth:</td>
								<td><fmt:formatDate value="${userOb.dob}"
										pattern="dd-MMM-yyyy" /></td>
							</tr>
						</c:if>
						<c:if test="${p.motherTongue ==0}">
							<tr>
								<td>Mother Tongue:</td>
								<td>${userOb.motherTongue}</td>
							</tr>
						</c:if>
						<c:if test="${p.state ==0}">
							<tr>
								<td>State:</td>
								<td>${userOb.state}</td>
							</tr>
						</c:if>
						<c:if test="${p.country ==0}">
							<tr>
								<td>Country:</td>
								<td>${userOb.country}</td>
							</tr>
						</c:if>
						<c:if test="${p.contactNo==0}">
							<tr>
								<td>Contact number:</td>
								<td>${userProObj.contactNo}</td>
							</tr>
						</c:if>
						<c:if test="${p.address==0}">
							<tr>
								<td>Address:</td>
								<td>${userProObj.address}</td>
							</tr>
						</c:if>
						<c:if test="${p.languagesKnown ==0}">
							<tr>
								<td>Languages Known</td>
								<td>${userProObj.languagesKnown}</td>
							</tr>
						</c:if>
						<c:if test="${p.education ==0}">
							<tr>
								<td>Education:</td>
								<td>${userProObj.education}</td>
							</tr>
						</c:if>
						<c:if test="${p.hobbies ==0}">
							<tr>
								<td>Hobbies:</td>
								<td>${userProObj.hobbies}</td>
							</tr>
						</c:if>
						<c:if test="${p.aboutUser ==0}">
							<tr>
								<td>About:</td>
								<td>${userProObj.aboutUser}</td>
							</tr>
						</c:if>
					</table>
				</div></td>
		</tr>
	</table>
</div>

<%@include file="footer.jsp"%>