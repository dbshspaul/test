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
				<center>Create page n post ad or Search n view.</center>
			</div>
		</div>

	</div>
	<div style="margin-left: 25px; margin-top: 25px; margin-right: 25px">
		<c:set var="page"
			value="${sessionScope['userBean'].getPageById(param.pageId)}" />

		<form action="editPage" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>page Name:</td>
					<td><input type="text" name="pageName"
						value="${page.pageName}"></td>
				</tr>
				<tr>
					<td>Page Type:</td>
					<td><input type="text" name="pageType"
						value="${page.pageType}"></td>
				</tr>
				<tr>
					<td>Page Description:</td>
					<td><textarea rows="3" cols="20" name="pageDesc">${page.pageDesc}</textarea></td>
				</tr>
				<tr>
					<td><img src="data:image/jpeg;base64,${page.imageAsString}"
						height="50">Change image for page:</td>
					<td><input type="file" name="pageImage"></td>
				</tr>
				<tr>
					<td><input type="hidden" value="${page.pageId}" name="pageId"></td>
					<td><input type="submit" value="Update Page"></td>
				</tr>
			</table>
		</form>
	</div>
</div>

<%@include file="footer.jsp"%>