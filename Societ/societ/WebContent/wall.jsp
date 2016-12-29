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
			<li><a class="selected" class="wall" href="wall.jsp">Wall<span
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
				<h1>&nbsp &nbsp Wall</h1>
				<center>Post, view comment n like, etc.</center>
			</div>
		</div>

	</div>
	<div style="margin-left: 25px; margin-top: 25px; margin-right: 25px">

		<c:set var="userPosts"
			value="${sessionScope['postBean'].getAllPosts()}" />
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Text Post</a></li>
				<li><a href="#tabs-2">Image post</a></li>
				<li><a href="#tabs-3">Media Post</a></li>
			</ul>
			<div id="tabs-1">
				<form action="usertextpost">
					<textarea rows="1" cols="80" name="usertextpost"></textarea>
					<input type="submit" value="Post">
				</form>
			</div>
			<div id="tabs-2">
				<form action="userimgpost" enctype="multipart/form-data"
					method="post">
					<input type="file" name="userimagepost"> <input
						type="submit" value="Post an image">
				</form>
			</div>
			<div id="tabs-3">
				<form action="userimgpost" enctype="multipart/form-data"
					method="post">
					<input type="file" name="userimagepost"> <input
						type="submit" value="Post a media">
				</form>
			</div>
		</div>
		<br>

		<div id="accordion">
			<c:forEach items="${userPosts}" var="usrpost">
				<h1>
					Posted on
					<fmt:formatDate value="${usrpost.userPostDate}"
						pattern="dd/MMM/yyyy hh:mm:ss" />

				</h1>

				<div>
					<h3>
						<c:choose>
							<c:when test="${usrpost.userPostType.equals('image/jpeg')}">
								<img alt=""
									src="data:image/jpeg;base64,${usrpost.imageAsString}"
									height="100">
							</c:when>
							<c:when test="${usrpost.userPostType.equals('video/mp4')}">
								<video width="400" height="150" controls preload="auto">
									<source src="data:video/mp4;base64,${usrpost.imageAsString}"
										type="video/mp4" />
								</video>
							</c:when>
							<c:when test="${usrpost.userPostType.equals('audio/mpeg')}">
								<audio controls preload="auto">
									<source src="data:audio/mpeg;base64,${usrpost.imageAsString}"
										type="audio/mpeg" />
								</audio>
							</c:when>
							<c:otherwise>
									${usrpost.textPost}
								</c:otherwise>
						</c:choose>
					</h3>
					<br>
					<%-- (${sessionScope['postBean'].getTotalNumberOfLike(usrpost.userPostId)})like --%>
					<u><a href="l1.jsp?id=${usrpost.userPostId}" target="_blank">(${sessionScope['postBean'].getTotalNumberOfLike(usrpost.userPostId)})Like</a></u>
					&nbsp &nbsp &nbsp &nbsp
					<c:choose>
						<c:when
							test="${sessionScope['postBean'].isLiked(usrpost.userPostId)}">
							<a id="like"
								href="unlikeUserPostWall?postid=${usrpost.userPostId}"><u>unlike</u></a>
						</c:when>
						<c:otherwise>
							<a id="unlike"
								href="likeUserPostWall?postid=${usrpost.userPostId}"><u>like</u></a>
						</c:otherwise>
					</c:choose>
					&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
					&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <a
						href="deleteuserpost?id=${usrpost.userPostId}"><u>Delete</u></a> <br>
					<br>

					<form action="postusercommentwall" method="post">
						<input type="text" name="comment" /> <input type="hidden"
							name="userpostid" value="${usrpost.userPostId}"> <input
							type="submit" value="comment" />
					</form>
					<hr>

					<c:forEach
						items="${sessionScope['postBean'].getAllUserPostComments(usrpost.userPostId)}"
						var="cmnt">
						<a href="viewFriend?friendid=${cmnt.user.emailId}"><u><b>${cmnt.user.name}</b></u></a>
										&nbsp &nbsp 
										<font color="gray">commented on &nbsp &nbsp <fmt:formatDate
								value="${cmnt.commentDate}" pattern="dd/MMM/yyyy hh:mm:ss" /></font>
						<br>
						<br>
										${cmnt.userPostComment}																															
<hr>
					</c:forEach>
				</div>
			</c:forEach>

		</div>

	</div>
</div>

<%@include file="footer.jsp"%>