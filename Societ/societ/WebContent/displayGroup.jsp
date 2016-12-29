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
			<li><a href="friend.jsp">Friends<span class="ui_icon friend"></span></a></li>
			<li><a href="message.jsp">Message<span class="ui_icon chat"></span></a></li>
			<li><a class="wall" href="wall.jsp">Wall<span
					class="ui_icon wall"></span></a></li>
			<li><a class="selected" href="group.jsp" id="group">group<span
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
				<h1>&nbsp Group</h1>
				<center>Create or Search n join, Post, View, etc.</center>
			</div>
		</div>

	</div>
	<div style="margin-left: 25px; margin-top: 25px; margin-right: 25px">
		<c:set var="group"
			value="${sessionScope['userBean'].getGroupById(requestScope['groupId'])}" />
		<c:set var="usrlst"
			value="${sessionScope['userBean'].getRequestGroup(group.groupId)}" />
		<c:set var="memberlst"
			value="${sessionScope['userBean'].getMemberOfTheGroup(group.groupId)}" />
		<div>
			<table>
				<tr>
					<td>
						<h1>
							Group : <a href="groupPost?groupId=${group.groupId}">${group.groupName}</a>
						</h1>
					</td>
					<td><b>Created on : </b> <fmt:formatDate
							value="${group.groupCreationDate}" pattern="hh:mm:ssa dd/MM/yyyy" /></td>
				</tr>
				<tr>
					<td rowspan="4"><a href="groupPost?groupId=${group.groupId}"><img
							alt="" src="data:image/jpeg;base64,${group.imageAsString}"
							height="150"></a></td>
					<td><b>Admin : </b><a
						href="viewFriend?friendid=${group.admin.emailId}">
							${group.admin.name}</a></td>
				</tr>
				<tr>
					<td><b>Type : </b>${group.groupType}</td>
				</tr>
				<tr>
					<td><b><u>Description</u> : </b>${group.groupDesc}</td>
				</tr>
				<tr>
					<td><a href="editGroup.jsp?groupId=${group.groupId}"><u>edit group</u></a>
						&nbsp &nbsp &nbsp <a href="deleteGroup?groupId=${group.groupId}"><u>delete group</u></a></td>
				</tr>
			</table>
		</div>
		<hr>
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Group request</a></li>
				<li><a href="#tabs-2">Members</a></li>
			</ul>
			<div id="tabs-1">
				<c:forEach items="${usrlst}" var="usr">
					<img src="img?email=${usr.emailId}" width="100">
					</td>
						${usr.name}
						${usr.state}
						<a
						href="acceptGroupRequest?uid=${usr.emailId}&gid=${group.groupId}"><u>Accept</u></a>
					<a
						href="cancelGroupRequest?uid=${usr.emailId}&gid=${group.groupId}"><u>Reject</u></a>
				</c:forEach>
				</table>
			</div>
			<div id="tabs-2">
				<c:forEach items="${memberlst}" var="mem">
					<img src="img?email=${mem.emailId}" width="100">
					</td>
						${mem.name}
						${mem.state}
						<a href="deleteMember?uid=${mem.emailId}&gid=${group.groupId}"><u>delete</u></a>
				</c:forEach>
				</table>
			</div>
		</div>
	</div>

	<%@include file="footer.jsp"%>