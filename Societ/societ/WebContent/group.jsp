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
		<c:set var="mygroups"
			value="${sessionScope['userBean'].getAllGroup(sessionScope['email'])}" />
		<c:set var="memberOfGroups"
			value="${sessionScope['userBean'].getGroupsOfTheMember()}" />
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Create and Search</a></li>
				<li><a href="#tabs-2">Admin of</a></li>
				<li><a href="#tabs-3">Member of</a></li>
			</ul>
			<div id="tabs-1">
				<hr>
				<h1>Create new group</h1>
				<form action="createGroup" method="post"
					enctype="multipart/form-data">
					<table>
						<tr>
							<td>Name:</td>
							<td><input type="text" name="grpnm" required=""></td>
						</tr>
						<tr>
							<td>Description of the group:</td>
							<td><textarea rows="6" cols="13" name="grpdesc" required=""></textarea></td>
						</tr>
						<tr>
							<td>Type of the group:</td>
							<td><input type="text" name="grptype" required=""></td>
						</tr>
						<tr>
							<td>Group picture</td>
							<td><input type="file" name="grpimg"></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit"
								value="Create Group"></td>
						</tr>
					</table>
				</form>
				<hr>
				<h1>Search a group</h1>
				<form action="searchGroup" method="get">
					<table bgcolor="gray">
						<tr>
							<td><input type="text" name="groupName"
								placeholder="Type group name" required=""></td>
							<td><input type="submit" value="Search group"></td>
						</tr>
					</table>
				</form>
				<hr>
			</div>
			<div id="tabs-2">
				<hr>
				<c:forEach items="${mygroups}" var="mygroup">
					<h1>
						Group : <a href="groupPost?groupId=${mygroup.groupId}"><u>${mygroup.groupName}</u></a>
					</h1>
					<table>
						<tr>
							<td rowspan="3"><a
								href="groupPost?groupId=${mygroup.groupId}"><img alt=""
									src="data:image/jpeg;base64,${mygroup.imageAsString}"
									height="100"></a></td>
							<td><b>Type : </b>${mygroup.groupType}</td>
						</tr>
						<tr>
							<td><b>Created on : </b> <fmt:formatDate
									value="${mygroup.groupCreationDate}"
									pattern="hh:mm:ssa dd/MM/yyyy" /></td>
						</tr>
						<tr>
							<td><b><u>Description</u> : </b>${mygroup.groupDesc}</td>
						</tr>
					</table>
					<br>
					<a href="editGroup.jsp?groupId=${mygroup.groupId}"><u>edit</u></a>
		&nbsp &nbsp &nbsp
		<a href="deleteGroup?groupId=${mygroup.groupId}"><u>delete</u></a>
		&nbsp &nbsp &nbsp
		<a href="displayGroup?groupId=${mygroup.groupId}"><u>View
							group request and members</u></a>
					<hr>
				</c:forEach>
			</div>
			<div id="tabs-3">
				<hr>
				<c:forEach items="${memberOfGroups}" var="memgroups">
					<h1>
						<td>Group : <a href="groupPost?groupId=${memgroups.groupId}"><u>${memgroups.groupName}</u></a>
					</h1>

					<table>
						<tr>
							<td rowspan="4"><a
								href="groupPost?groupId=${memgroups.groupId}"> <img alt=""
									src="data:image/jpeg;base64,${memgroups.imageAsString}"
									height="100">
							</a></td>
							<td>Admin : <u><a
									href="viewFriend?friendid=${memgroups.admin.emailId}">
										${memgroups.admin.name}</a></u></td>

						</tr>
						<tr>
							<td><b>Type : </b>${memgroups.groupType}</td>
						</tr>
						<tr>
							<td><b>Created on : </b> <fmt:formatDate
									value="${memgroups.groupCreationDate}"
									pattern="hh:mm:ssa dd/MM/yyyy" /></td>
						</tr>
						<tr>
							<td><b><u>Description</u> : </b>${memgroups.groupDesc}</td>
						</tr>
					</table>
					<hr>
				</c:forEach>
			</div>
		</div>
	</div>

	<%@include file="footer.jsp"%>