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
			<li><a href="group.jsp" id="group">group<span
					class="ui_icon group"></span></a></li>
			<li><a href="email.jsp">Email<span class="ui_icon email"></span></a></li>
			<li><a class="selected" href="page.jsp">Page and Ad<span
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
				<h1>Page & Advertisement</h1>
				<center>Wall for posting advertisement.</center>
			</div>
		</div>

	</div>
	<div style="margin-left: 25px; margin-top: 25px; margin-right: 25px">
		<c:set var="page"
			value="${sessionScope['userBean'].getPageById(sessionScope['pageId'])}" />
		<c:set var="pagePosts"
			value="${sessionScope['postBean'].getAllPagePosts(page.pageId)}" />

		<div>
			<table align="center">
				<tr>
					<td><a href="viewPage?pageId=${page.pageId}"><img src="data:image/jpeg;base64,${page.imageAsString}"
						height="100"></a></td>
					<td><a href="deletePage?pageId=${page.pageId}">Delete Page</a></td>
				</tr>
				<tr>
					<td>Page Name:</td>
					<td><a href="viewPage?pageId=${page.pageId}">${page.pageName}</a></td>
					<td><a href="editPage.jsp?pageId=${page.pageId}">Edit Page</a></td>
				</tr>
				<tr>
					<td>Page Type:</td>
					<td>${page.pageType}</td>
				</tr>
				<tr>
					<td>Page Description:</td>
					<td>${page.pageDesc}</td>
				</tr>
				<tr>
					<td><h5>
							<a href="l3.jsp?id=${page.pageId}" target="_blank">(${sessionScope['postBean'].getTotalNumberOfPageLike(page.pageId)})like</a>
						</h5></td>
					<td><c:choose>
							<c:when
								test="${sessionScope['postBean'].isPageLiked(page.pageId)}">
								<a id="like" href="unlikePage?pageid=${page.pageId}">unlike</a>
							</c:when>
							<c:otherwise>
								<a id="unlike" href="likePage?pageid=${page.pageId}">like</a>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</table>
		</div>
		<hr>
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Text Post</a></li>
				<li><a href="#tabs-2">Image post</a></li>
				<li><a href="#tabs-3">Media Post</a></li>
			</ul>
			<div id="tabs-1">
				<form action="pagetextpost">
					<textarea name="pagetextpost"></textarea>
					<input type="hidden" value="${page.pageId}" name="pageId">
					<input type="submit" value="Post">
				</form>
			</div>
			<div id="tabs-2">
				<form action="pageimgpost" enctype="multipart/form-data"
					method="post">
					<input type="file" name="pageimgpost"> <input type="hidden"
						value="${page.pageId}" name="pageId"> <input type="submit"
						value="Post an image">
				</form>
			</div>
			<div id="tabs-3">
				<form action="pageimgpost" enctype="multipart/form-data"
					method="post">
					<input type="file" name="pageimgpost"><input type="hidden"
						value="${page.pageId}" name="pageId"> <input type="submit"
						value="Post a media">
				</form>
			</div>
		</div>

		<div id="accordion">
			<c:forEach items="${pagePosts}" var="pgpost">
				<h1>
					Posted on
					<fmt:formatDate value="${pgpost.pagePostDate}"
						pattern="dd/MMM/yyyy hh:mm:ss" />

				</h1>
				<div>
					<h3>
						<c:choose>
							<c:when test="${pgpost.pagePostType.equals('image/jpeg')}">
								<img alt="" src="data:image/jpeg;base64,${pgpost.imageAsString}"
									height="100">
							</c:when>
							<c:when test="${pgpost.pagePostType.equals('video/mp4')}">
								<video width="400" controls preload="auto">
									<source src="data:video/mp4;base64,${pgpost.imageAsString}"
										type="video/mp4" />
							</c:when>
							<c:when test="${pgpost.pagePostType.equals('audio/mpeg')}">
								<audio controls preload="auto">
									<source src="data:audio/mpeg;base64,${pgpost.imageAsString}"
										type="audio/mpeg" />
							</c:when>
							<c:otherwise>
									${pgpost.pagePostText}
								</c:otherwise>
						</c:choose>
					</h3>
					<br> <u><a href="l2.jsp?id=${pgpost.pagePostId}"
						target="_blank">(${sessionScope['postBean'].getTotalNumberOfPagePostLike(pgpost.pagePostId)})like</a></u>
					&nbsp &nbsp &nbsp &nbsp
					<c:choose>
						<c:when
							test="${sessionScope['postBean'].isPagePostLiked(pgpost.pagePostId)}">
							<a id="like" href="unlikePagePost?postid=${pgpost.pagePostId}"><u>unlike</u></a>
						</c:when>
						<c:otherwise>
							<a id="unlike" href="likePagePost?postid=${pgpost.pagePostId}"><u>like</u></a>
						</c:otherwise>
					</c:choose>
					&nbsp &nbsp &nbsp &nbsp <a
						href="deletePagePost?postId=${pgpost.pagePostId}"><u>delete</u></a>
					<br> <br>
					<form action="pagepostcomment" method="post">
						<input type="text" name="comment"> <input type="hidden"
							name="pagepostid" value="${pgpost.pagePostId}"> <input
							type="submit" value="comment">
					</form>
					<hr>
					<c:forEach
						items="${sessionScope['postBean'].getAllPagePostComments(pgpost.pagePostId)}"
						var="cmnt">
						<a href="viewFriend?friendid=${cmnt.user.emailId}"><u>${cmnt.user.name}</u></a>
						&nbsp &nbsp 
						<font color="gray">commented on &nbsp &nbsp <fmt:formatDate
								value="${cmnt.commentDate}" pattern="dd/MMM/yyyy hh:mm:ss" /></font>
						<br>
						<br>
										${cmnt.pagePostComment}																														
<hr>
					</c:forEach>
				</div>
			</c:forEach>
		</div>

	</div>
</div>

<%@include file="footer.jsp"%>