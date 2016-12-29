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
				<h1>&nbsp Group Wall</h1>
				<center>Post, comment, like, view, etc.</center>
			</div>
		</div>

	</div>
	<div style="margin-left: 25px; margin-top: 25px; margin-right: 25px">
		<c:set var="group"
			value="${sessionScope['userBean'].getGroupById(sessionScope['groupId'])}" />
		<c:set var="groupPosts"
			value="${sessionScope['postBean'].getAllGroupPosts(sessionScope['groupId'])}" />
			<c:set var="memberlst" value="${sessionScope['userBean'].getMemberOfTheGroup(group.groupId)}" />
		<div>
		<table><tr><td>
			<h1>
				Group : <a href="groupPost?groupId=${group.groupId}">${group.groupName}</a>
			</h1></td>
			<td><b>Created on : </b><fmt:formatDate value="${group.groupCreationDate}"
				pattern="hh:mm:ssa dd/MM/yyyy" /></td></tr>
			<tr><td rowspan="3"><a href="groupPost?groupId=${group.groupId}"><img alt=""
				src="data:image/jpeg;base64,${group.imageAsString}" height="150"></a></td>
				<td><b>Admin : </b><a href="viewFriend?friendid=${group.admin.emailId}">
				${group.admin.name}</a></td></tr>
			 <tr><td><b>Type : </b>${group.groupType}</td></tr>
			<tr><td><b><u>Description</u> : </b>${group.groupDesc}</td></tr>
		</table></div>
		<div style="float: right;"><a href="groupMemberName.jsp?id=${group.groupId}"><u>Group members(${memberlst.size()})</u></a></div><br>
		<hr>
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Text Post</a></li>
				<li><a href="#tabs-2">Image post</a></li>
				<li><a href="#tabs-3">Media Post</a></li>
			</ul>
			<div id="tabs-1">
				<form action="grouptextpost">
					<textarea name="grouptextpost"></textarea>
					<input type="hidden" value="${group.groupId}" name="groupId">
					<input type="submit" value="Post">
				</form>
			</div>
			<div id="tabs-2">
				<form action="groupimgpost" enctype="multipart/form-data"
					method="post">
					<input type="file" name="groupimagepost"><input
						type="hidden" value="${group.groupId}" name="groupId"> <input
						type="submit" value="Post an image">
				</form>
			</div>
			<div id="tabs-3">
				<form action="groupimgpost" enctype="multipart/form-data"
					method="post">
					<input type="file" name="groupimagepost"> <input
						type="hidden" value="${group.groupId}" name="groupId"><input
						type="submit" value="Post a media">
				</form>
			</div>
		</div>
		<br>
		<div id="accordion">
			<c:forEach items="${groupPosts}" var="gppost">
				<h1>
					Posted on
					<fmt:formatDate value="${gppost.groupPostDate}"
						pattern="dd/MMM/yyyy hh:mm:ssa" />

				</h1>
				<div>
					<a href="viewFriend?friendid=${gppost.user.emailId}"><u>${gppost.user.name}</u></a>
					<h3>
						<c:choose>
							<c:when test="${gppost.groupPostType.equals('image/jpeg')}">
								<img alt=""
									src="data:image/jpeg;base64,${gppost.groupPostImage}"
									height="100">
							</c:when>
							<c:when test="${gppost.groupPostType.equals('video/mp4')}">
								<video width="400" controls preload="auto">
									<source src="data:video/mp4;base64,${gppost.groupPostImage}"
										type="video/mp4" />
							</c:when>
							<c:when test="${gppost.groupPostType.equals('audio/mpeg')}">
								<audio controls preload="auto">
									<source src="data:audio/mpeg;base64,${gppost.groupPostImage}"
										type="audio/mpeg" />
							</c:when>
							<c:otherwise>
									${gppost.groupPostText}
								</c:otherwise>
						</c:choose>
					</h3>
					<br> <u><a href="l4.jsp?id=${gppost.groupPostId}"
						target="_blank">(${sessionScope['postBean'].getTotalNumberOfGroupPostLike(gppost.groupPostId)})like</a></u>
					&nbsp &nbsp &nbsp &nbsp&nbsp &nbsp &nbsp &nbsp
					<c:choose>
						<c:when
							test="${sessionScope['postBean'].isGroupPostLiked(gppost.groupPostId)}">
							<a id="like" href="unlikeGroupPost?postid=${gppost.groupPostId}"><u>unlike</u></a>
						</c:when>
						<c:otherwise>
							<a id="unlike" href="likeGroupPost?postid=${gppost.groupPostId}"><u>like</u></a>
						</c:otherwise>
					</c:choose>
					<br>
					<br>
					<form action="grouppostcomment" method="post">
						<input type="text" name="comment">
						<input type="hidden" name="grouppostid"
							value="${gppost.groupPostId}"> <input type="submit"
							value="comment">
					</form>
					<hr>
					<c:forEach
						items="${sessionScope['postBean'].getAllGroupPostComments(gppost.groupPostId)}"
						var="cmnt">
						<a href="viewFriend?friendid=${cmnt.user.emailId}"><u>${cmnt.user.name}</u></a>
						&nbsp &nbsp 
						<font color="gray">commented on &nbsp &nbsp <fmt:formatDate
								value="${cmnt.commentDate}" pattern="dd/MMM/yyyy hh:mm:ssa" /></font>
						<br>
						<br>
										${cmnt.groupPostComment}																														
<hr>
					</c:forEach>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<%@include file="footer.jsp"%>