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
			<li><a class="selected" href="userHome.jsp">Home<span
					class="ui_icon home"></span></a></li>
			<li><a class="myprofile" href="myProfile.jsp">My Profile<span
					class="ui_icon profile"></span></a></li>
			<li><a href="friend.jsp">Friends<span class="ui_icon friend"></span></a></li>
			<li><a href="message.jsp">Message<span class="ui_icon chat"></span></a></li>
			<li><a class="wall" href="wall.jsp">Wall<span
					class="ui_icon wall"></span></a></li>
			<li><a href="group.jsp" id="group">group<span
					class="ui_icon group"></span></a></li>
			<li><a href="email.jsp">Email<span class="ui_icon email"></span></a></li>
			<li><a href="page.jsp">Page and Ad<span class="ui_icon ad"></span></a></li>
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
				<h1>&nbsp &nbsp &nbsp Home</h1>
				<center>Get notifications, if any.</center>
			</div>
		</div>
	</div>

	<div style="margin-left: 25px; margin-top: 25px; margin-right: 25px">
		<c:set var="reqfrndlst"
			value="${sessionScope['friendBean'].getFriendsRequest()}" />
		<c:set var="groupreq"
			value="${sessionScope['userBean'].showGrouprequest()}" />
		<c:set var="messages"
			value="${sessionScope['messageBean'].getUnreadMessages()}" />
		<hr>
		<br>
		<div>
			<b>You have friend request from : </b><br /> <br>
			<c:if test="${reqfrndlst.size()>0}">
				<h3>
					<a href="friend.jsp#tabs-1" target="_blank">you have ${reqfrndlst.size()} friend request</a>
				</h3>
			</c:if>
		</div>
		
		<hr>
		
		<br>
		
		<div>
			<b>You have group request on :</b><br /> <br>
			<c:if test="${groupreq.size()>0}">
				<h3>
					<a href="#" target="_blank">you have ${groupreq.size()} group request.</a>
				</h3>
			</c:if>
			<table>
				<c:forEach items="${groupreq}" var="gr">
					<tr>
						<td>Group : <a href="displayGroup?groupId=${gr.groupId}">${gr.groupName}</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<hr>
		<br>
		<div>
			<b>You have message(s)...</b><br /> <br>
			<c:if test="${messages.size()>0}">
				<h3>
					<a href="message.jsp" target="_blank">you have received ${messages.size()} message/s.</a>
				</h3>
			</c:if>
			<c:forEach items="${messages}" var="message">
				<table>
					<tr>
						<td><a href="newMessageDisplay?msgid=${message.messageId}">${message.messageHead}</a></td>
						<td><fmt:formatDate value="${message.messageSendDate}"
								pattern=" hh:mm:ssa dd/MMM/yyyy " /><span>Message sent
								by </span><a href="viewFriend?friendid=${message.sender.emailId}">${message.sender.name}</a></td>
					</tr>
				</table>
			</c:forEach>
		</div>
		<hr>
		<br>

		<div>
			<b>Your friend request accepted </b><br /> <br>
			<%-- <table>
				<c:forEach items="${groupreq}" var="gr">
					<tr>
						<td>Group : <a href="displayGroup?groupId=${gr.groupId}">${gr.groupName}</a></td>
					</tr>
				</c:forEach>
			</table> --%>
		</div>
		<hr>
		<br>
		<div>
			<b>Your group request accepted on Group : </b><br /> <br>
			<%-- <table>
				<c:forEach items="${groupreq}" var="gr">
					<tr>
						<td>Group : <a href="displayGroup?groupId=${gr.groupId}">${gr.groupName}</a></td>
					</tr>
				</c:forEach>
			</table> --%>
		</div>
		<hr>
	</div>

</div>

<%@include file="footer.jsp"%>