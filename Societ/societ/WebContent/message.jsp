<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="header1.jsp"%>

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
						<li><a href="friend.jsp">Friends<span
								class="ui_icon friend"></span></a></li>
						<li><a class="selected" href="message.jsp">Message<span
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
							<h1>&nbsp &nbsp &nbsp Message</h1>
							<center>View and send messages.</center>
						</div>
					</div>

				</div>
				<div style="margin-left: 25px; margin-top: 25px; margin-right: 25px">
		<c:set var="messages"
			value="${sessionScope['messageBean'].getInboxMessages()}" />
		<h3 align="center">${requestScope['Message']}</h3>
		<table cellpadding="5">
			<tr>
				<td><table>
						<tr>
							<td><a href="messageWriteNow.jsp">Write Message</a></td>
						</tr>
						<tr>
							<td><a href="#">Inbox</a></td>
						</tr>
						<tr>
							<td><a href="messageOutbox.jsp">Outbox</a></td>
						</tr>
					</table></td>
				<td><span style="color: silver; font-size: 4ex;">INBOX</span> <c:forEach
						items="${messages}" var="message">
						<table>
							<tr>
								<c:choose>
									<c:when test="${message.readStatus==0}">
										<td><div style="background-color: gray;">
												<a href="newMessageDisplay?msgid=${message.messageId}">${message.messageHead}</a>
											</div></td>
										<td><fmt:formatDate value="${message.messageSendDate}"
												pattern="hh:mm:ssa dd/MMM/yyyy" /><a
											href="deleteInboxMessage?msgid=${message.messageId}">delete</a></td>
									</c:when>
									<c:when test="${message.readStatus==1}">
										<td><a href="messageDisplay?msgid=${message.messageId}">${message.messageHead}</a></td>
										<td><fmt:formatDate value="${message.messageSendDate}"
												pattern="hh:mm:ssa dd/MMM/yyyy " /><a
											href="deleteInboxMessage?msgid=${message.messageId}">delete</a></td>
									</c:when>
									<c:otherwise>
										<td><a>${message.messageHead}</a></td>
										<td><fmt:formatDate value="${message.messageSendDate}"
												pattern="hh:mm:ssa dd/MMM/yyyy" /><span style="color: red;">error
												logic</span></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</table>
					</c:forEach></td>
			</tr>
		</table>
	</div>
			</div>

<%@include file="footer.jsp"%>