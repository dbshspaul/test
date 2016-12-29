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
			<li><a class="selected" href="email.jsp">Email<span
					class="ui_icon email"></span></a></li>
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
				<h1>&nbsp &nbsp &nbsp Email</h1>
				<center>Send mail to any mail id and view outbox.</center>
			</div>
		</div>

	</div>

	<div style="margin-left: 25px; margin-top: 25px; margin-right: 25px">
		<h3 align="center">${requestScope['Message']}</h3>
		<hr>
		<br>
		<h1>Send new Mail</h1>
		<div id="contact_form">
			<form method="post" name="" action="SendEmail">
				<label for="recipient">Recipient address:</label> <input type="text"
					id="" name="recipient" class="required input_field" required />
				<div class="cleaner_h10"></div>

				<label for="subject">Subject:</label> <input type="text" id=""
					name="subject" class="required input_field" required />
				<div class="cleaner_h10"></div>

				<label for="content">Content:</label>
				<textarea id="text" name="content" rows="0" cols="0"
					class="required"></textarea>
				<div class="cleaner_h10"></div>

				<input type="submit" class="submit_btn" name="submit" id="submit"
					value="Send" /> <input type="reset" class="submit_btn"
					name="reset" id="reset" value="Reset" />

			</form>
		</div>
		<hr>
		<br>
		<h1>Sent box</h1>
		<c:set var="ams"
			value="${sessionScope['userBean'].getAllMailOutBox()}" />
		<div id="accordion">
			<c:forEach items="${ams}" var="am">
				<h1>
					Recipient address : ${am.recipientAddress}
					<p>Subject : ${am.subject}</p>
					Sent on
					<fmt:formatDate value="${am.sendDate}" pattern="dd/MM/yy hh:mm:ssa" />
				</h1>
				<div>
					Content : ${am.content} <span style="float: right;"><a
						href="deleteMail?id=${am.id}">delete</a></span>
				</div>
			</c:forEach>
		</div>
		<hr>
	</div>
</div>

<%@include file="footer.jsp"%>