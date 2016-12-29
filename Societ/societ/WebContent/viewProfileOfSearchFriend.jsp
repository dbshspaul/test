<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="header1.jsp"%>

<div class="art-layout-wrapper">
	<div class="art-content-layout">
		<div class="art-content-layout-row">
			<div class="art-layout-cell art-sidebar1">



				<div class="art-block clearfix" style="margin-top: 0px">
					<div class="art-blockcontent">
						<h1>Societatis</h1>
						<center>Connect with the world...</center>
					</div>
				</div>



				<div class="art-vmenublock clearfix">
					<ul class="navigation">
						<li><a href="userHome.jsp">Home<span class="ui_icon home"></span></a></li>
						<li><a class="myprofile" href="myProfile.jsp">My Profile<span
								class="ui_icon profile"></span></a></li>
						<li><a class="selected" href="friend.jsp">Friends<span
								class="ui_icon friend"></span></a></li>
						<li><a href="message.jsp">Message<span
								class="ui_icon chat"></span></a></li>
						<li><a class="wall" href="wall.jsp">Wall<span
								class="ui_icon wall"></span></a></li>
						<li><a href="group.jsp" id="group">group<span
								class="ui_icon group"></span></a></li>
						<li><a href="email.jsp">Email<span class="ui_icon email"></span></a></li>
						<li><a href="page.jsp">Page and Ad<span
								class="ui_icon ad"></span></a></li>
						<li><a href="gallery.jsp">Gallery<span
								class="ui_icon gallery"></span></a></li>
						<li><a href="notes.jsp">Notes<span class="ui_icon note"></span></a></li>
						<li><a href="help.jsp">help<span class="ui_icon help"></span></a></li>
						<li><a href="settings.jsp">settings<span
								class="ui_icon setting"></span></a></li>
						<li><a href="logout">Log Out<span class="ui_icon logout"></span></a></li>
					</ul>
				</div>
			</div>
			<div class="art-layout-cell art-content">
				<div class="art-block clearfix" style="margin-top: 0px">
					<div class="art-layout-cell art-sidebar2">
						<div class="art-blockcontent">
							<table>
								<tr>
									<td>
										<!-- <img src="images/Default.png" height="53px"
										width="53px" /> --> <img alt=""
										src="data:image/jpeg;base64,${sessionScope['userBean'].getUserProfileDetailsById(sessionScope['email']).imageAsString}"
										height="53" width="53px" />
									</td>
									<td>
										<%-- <c:set property="email" target="${sessionScope['userBean']}"
										value="${sessionScope['email']}" /> 
										<c:set var="userOb" value="${sessionScope['userBean']['userDetails']}" />--%>
										<c:set var="userOb"
											value="${sessionScope['userBean'].getUserDetailsById(sessionScope['email'])}" />
										<h1 style="text-align: center; color: white;">Hi
											${userOb.name}...</h1>
									</td>
								</tr>
							</table>

						</div>
					</div>
					<div class="art-layout-cell art-sidebar2">
						<div class="art-blockcontent">
						<c:set var="userOb"	value="${sessionScope['userBean'].getUserDetailsById(param.fid)}" />
							<h1>&nbsp ${userOb.name} 's Profile</h1>
							<center>View</center>
						</div>
					</div>

				</div>
				<div style="margin-left: 25px; margin-top: 25px; margin-right: 25px">
					<%-- <c:set property="email" target="${sessionScope['userBean']}" value="${requestScope['friendId']}"/> --%>
					
					<c:set var="userOb"	value="${sessionScope['userBean'].getUserDetailsById(param.fid)}" />
					<c:set var="userProObj"	value="${sessionScope['userBean'].getUserProfileDetailsById(param.fid)}"/>
						<c:set var="p" value="${sessionScope['userBean'].getPrivacyById(param.fid)}"/>

					<table>
						<tr>
							<td><div>
									<table>
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
						<tr>
							<td><a href="addFriend?frndid=${userOb.emailId}">Add Friend</a></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="footer.jsp"%>