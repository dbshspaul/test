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
			<li><a href="page.jsp">Page and Ad<span class="ui_icon ad"></span></a></li>
			<li><a href="gallery.jsp">Gallery<span
					class="ui_icon gallery"></span></a></li>
			<li><a href="notes.jsp">Notes<span class="ui_icon note"></span></a></li>
			<li><a class="selected" href="help.jsp">help<span
					class="ui_icon help"></span></a></li>
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
				<h1>Help</h1>
				<center>Quary/ feedback/ complaint/ any assistence.</center>
			</div>
		</div>
	</div>

	<div style="margin-left: 25px; margin-top: 25px; margin-right: 25px">
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Help/Feedback</a></li>
				<li><a href="#tabs-2">Inconvenience complaint</a></li>
				<li><a href="#tabs-3">Violation of a Profile</a></li>
			</ul>
			<div id="tabs-1">
				<h1>Send us message for any help or query...</h1>
				<form method="post" name="contact" action="feedback">
					<label for="author">Subject:</label>
					<input type="text" id="sub" name="sub" class="required input_field" />
					<div class="cleaner_h10"></div>

					<label for="email">Contact Number:</label> 
					<input type="text" id="email" name="email" class="validate-email required input_field" />
					<div class="cleaner_h10"></div>

					<label for="text">Message:</label>
					<textarea id="text" name="text" rows="0" cols="0" class="required"></textarea>
					<div class="cleaner_h10"></div>

					<input type="submit" class="submit_btn" name="submit" id="submit"
						value="Send" /> <input type="reset" class="submit_btn"
						name="reset" id="reset" value="Reset" />
				</form>
			</div>
			
			<div id="tabs-2">
				<h1>Complain for any inconvenience</h1>
				<form method="post" name="contact" action="inconvenienceComplain">
					<label for="author">Inconvenience Subject:</label> <input
						type="text" id="sub" name="sub" class="required input_field" required/>
					<div class="cleaner_h10"></div>

					<label for="text">Details:</label>
					<textarea id="text" name="text" rows="0" cols="0" class="required" required></textarea>
					<div class="cleaner_h10"></div>

					<input type="submit" class="submit_btn" name="submit" id="submit"
						value="Send" /> <input type="reset" class="submit_btn"
						name="reset" id="reset" value="Reset" />
				</form>
			</div>
			<div id="tabs-3">
				<h1>Let us know about violation of any profile</h1>
				<form method="post" name="contact" action="violationProfile">
					<label for="author">Profile Name:</label> <input type="text"
						id="author" name="author" class="required input_field" required/>
					<div class="cleaner_h10"></div>

					<label for="email">Profile Email:</label> <input type="email"
						id="email" name="email"
						class="validate-email required input_field" required/>
					<div class="cleaner_h10"></div>

					<label for="text">Details:</label>
					<textarea id="text" name="text" rows="0" cols="0" class="required"
						required=""></textarea>
					<div class="cleaner_h10"></div>

					<input type="submit" class="submit_btn" name="submit" id="submit"
						value="Send" /> <input type="reset" class="submit_btn"
						name="reset" id="reset" value="Reset" />
				</form>
			</div>
		</div>
	</div>

</div>

<%@include file="footer.jsp"%>