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
			<li><a class="selected" href="friend.jsp">Friends<span
					class="ui_icon friend"></span></a></li>
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
				<h1>&nbsp &nbsp Friends</h1>
				<center>View, Search, Connect, Enjoy, etc.</center>
			</div>
		</div>

	</div>
	<div style="margin-left: 25px; margin-top: 25px; margin-right: 25px">
		<c:set var="reqfrndlst"
			value="${sessionScope['friendBean'].getFriendsRequest()}" />
		<c:set var="frndlst"
			value="${sessionScope['friendBean'].getFriendslist()}" />
		<!-- <div align="center">
			<a href="searchFriend.jsp"><input type="button"
				value="Seach Friend"></a>
		</div> -->

		<div id="tabs">
			<ul>
				<li><a href="#tabs-3">Search Friend</a></li>
				<li><a href="#tabs-1">Friend request</a></li>
				<li><a href="#tabs-2">Friend List</a></li>
			</ul>
			<div id="tabs-1">
				<table>
					<c:forEach items="${reqfrndlst}" var="friend">
						<tr>
							<td><a
							href="viewRequestFriendProfile.jsp?fid=${friend.emailId}" target="_blank"><img src="img?email=${friend.emailId}" width="100"></a></td>
							<td><table>
									<tr>
										<td><a
							href="viewRequestFriendProfile.jsp?fid=${friend.emailId}" target="_blank">${friend.name}</a></td>
									</tr>
									<tr>
										<td>${friend.gender}</td>
									</tr>
									<tr>
										<td><a href="acceptRequest?frndid=${friend.emailId}">Accept
												</a></td>
										<td><a href="rejectRequest?frndid=${friend.emailId}">Reject
												</a></td>
									</tr>
								</table></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div id="tabs-2">
				<table>
					<c:forEach items="${frndlst}" var="frnd">
						<tr>
							<td><a href="viewFriend?friendid=${frnd.emailId}"><img
									src="img?email=${frnd.emailId}" width="100"></a></td>
							<td><table>
									<tr>
										<td><a href="viewFriend?friendid=${frnd.emailId}">${frnd.name}</a></td>
									</tr>
									<tr>
										<td>${frnd.gender}</td>
									</tr>
									<tr>
										<td><a href="removeFriend?frndid=${frnd.emailId}">Remove
												Friend</a></td>
									</tr>
								</table></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div id="tabs-3">
				<c:set var="friendlst" value="${requestScope['frndlst']}" />
				<div>
					<form action="search" method="post">
						<input type="text" name="name" value=""
							placeholder="Type the name" required=""> <input
							type="submit" value="Search" name="submit">
					</form>
				</div>
				<table>
					<c:forEach items="${friendlst}" var="friend">
						<tr>
							<td><a
								href="viewProfileOfSearchFriend.jsp?fid=${friend.emailId}"><img
									src="img?email=${friend.emailId}" width="100"></a></td>
							<td><table>
									<tr>
										<td><a
											href="viewProfileOfSearchFriend.jsp?fid=${friend.emailId}">${friend.name}</a></td>
									</tr>
									<tr>
										<td>${friend.gender}</td>
									</tr>
									<tr>
										<td><a href="addFriend?frndid=${friend.emailId}">Add
												Friend</a></td>
									</tr>
								</table></td>
						</tr>
					</c:forEach>
				</table>
			</div>

		</div>
	</div>
</div>
<%@include file="footer.jsp"%>