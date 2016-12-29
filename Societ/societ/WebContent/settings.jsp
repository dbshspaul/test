<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="header1.jsp"%>
<script type="text/javascript">
	jQuery(function() {
		$("#submit")
				.click(
						function() {
							$(".error").hide();
							var hasError = false;
							var passwordVal = $("#password").val();
							var checkVal = $("#password-check").val();
							if (passwordVal == '') {
								$("#password")
										.after(
												'<span class="error">Please enter a password.</span>');
								hasError = true;
							} else if (checkVal == '') {
								$("#password-check")
										.after(
												'<span class="error">Please re-enter your password.</span>');
								hasError = true;
							} else if (passwordVal != checkVal) {
								$("#password-check")
										.after(
												'<span class="error">Passwords did not match.</span>');
								hasError = true;
							}
							if (hasError == true) {
								return false;
							}
						});
	});
</script>

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
			<li><a href="help.jsp">help<span class="ui_icon help"></span></a></li>
			<li><a class="selected" href="settings.jsp">settings<span
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
				<h1>&nbsp &nbsp &nbsp Settings</h1>
				<center>Change password, set privacy.</center>
			</div>
		</div>
	</div>

	<div style="margin-left: 25px; margin-top: 25px; margin-right: 25px">
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Change Password</a></li>
				<li><a href="#tabs-2">Set Privacy</a></li>
			</ul>
			<div id="tabs-1">
				<form action="changepass" method="post">
					<label></label> <input type="password" id="" name="pass"
						class="required input_field" placeholder="Current Password"
						required="" />
					<div class="cleaner_h10"></div>
					<label></label> <input type="password" id="password" name="newpass"
						class="required input_field" placeholder="New Password" required="" />
					<div class="cleaner_h10"></div>
					<label></label> <input type="password" id="password-check"
						name="conpass" class="required input_field"
						placeholder="Confirm Password" required="" />
					<div class="cleaner_h10"></div>
					<input type="submit" class="submit_btn" name="submit" id="submit"
						value="Change password" /> <input type="reset" class="submit_btn"
						name="reset" id="reset" value="Reset" />
				</form>
			</div>
			<c:set var="p"
				value="${sessionScope['userBean'].getOwnPrivacy()}" />

			<div id="tabs-2">
				<form action="setprivacy" method="post"
					enctype="multipart/form-data">
					<table cellpadding="3">
						<tr>
							<td>Contact No</td>
							<td><select name="con">
									<c:choose>
										<c:when test="${p.contactNo ==0}">
											<option value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option selected value="0">Show to all</option>
										</c:when>
										<c:when test="${p.contactNo ==1}">
											<option selected value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:when test="${p.contactNo ==2}">
											<option value="1">Hide from others</option>
											<option selected value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:otherwise>
											<option>Some problem is there.</option>
										</c:otherwise>
									</c:choose>
							</select></td>
						</tr>
						<tr>
							<td>Address</td>
							<td><select name="addr">
									<c:choose>
										<c:when test="${p.address ==0}">
											<option value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option selected value="0">Show to all</option>
										</c:when>
										<c:when test="${p.address ==1}">
											<option selected value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:when test="${p.address ==2}">
											<option value="1">Hide from others</option>
											<option selected value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:otherwise>
											<option>Some problem is there.</option>
										</c:otherwise>
									</c:choose>
							</select></td>
						</tr>
						<tr>
							<td>State</td>
							<td><select name="state">
									<c:choose>
										<c:when test="${p.state ==0}">
											<option value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option selected value="0">Show to all</option>
										</c:when>
										<c:when test="${p.state ==1}">
											<option selected value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:when test="${p.state ==2}">
											<option value="1">Hide from others</option>
											<option selected value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:otherwise>
											<option>Some problem is there.</option>
										</c:otherwise>
									</c:choose>
							</select></td>
						</tr>
						<tr>
							<td>Country</td>
							<td><select name="country">
									<c:choose>
										<c:when test="${p.country ==0}">
											<option value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option selected value="0">Show to all</option>
										</c:when>
										<c:when test="${p.country ==1}">
											<option selected value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:when test="${p.country ==2}">
											<option value="1">Hide from others</option>
											<option selected value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:otherwise>
											<option>Some problem is there.</option>
										</c:otherwise>
									</c:choose>
							</select></td>
						</tr>
						<tr>
							<td>Mother Tongue</td>
							<td><select name="mt">
									<c:choose>
										<c:when test="${p.motherTongue ==0}">
											<option value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option selected value="0">Show to all</option>
										</c:when>
										<c:when test="${p.motherTongue ==1}">
											<option selected value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:when test="${p.motherTongue ==2}">
											<option value="1">Hide from others</option>
											<option selected value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:otherwise>
											<option>Some problem is there.</option>
										</c:otherwise>
									</c:choose>
							</select></td>
						</tr>
						<tr>
							<td>Languages known</td>
							<td><select name="lk">
									<c:choose>
										<c:when test="${p.languagesKnown ==0}">
											<option value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option selected value="0">Show to all</option>
										</c:when>
										<c:when test="${p.languagesKnown ==1}">
											<option selected value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:when test="${p.languagesKnown ==2}">
											<option value="1">Hide from others</option>
											<option selected value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:otherwise>
											<option>Some problem is there.</option>
										</c:otherwise>
									</c:choose>
							</select></td>
						</tr>
						<tr>
							<td>Date of Birth</td>
							<td><select name="dob">
									<c:choose>
										<c:when test="${p.dob ==0}">
											<option value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option selected value="0">Show to all</option>
										</c:when>
										<c:when test="${p.dob ==1}">
											<option selected value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:when test="${p.dob ==2}">
											<option value="1">Hide from others</option>
											<option selected value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:otherwise>
											<option>Some problem is there.</option>
										</c:otherwise>
									</c:choose>
							</select></td>
						</tr>
						<tr>
							<td></td>
							<td><select name="addrp">
									<option selected value="0">--------</option>
									<option value="1">Show Date & Month</option>
									<option value="0">Show all</option>
							</select></td>
						</tr>
						<tr>
							<td>Education</td>
							<td><select name="edu">
									<c:choose>
										<c:when test="${p.education ==0}">
											<option value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option selected value="0">Show to all</option>
										</c:when>
										<c:when test="${p.education ==1}">
											<option selected value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:when test="${p.education ==2}">
											<option value="1">Hide from others</option>
											<option selected value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:otherwise>
											<option>Some problem is there.</option>
										</c:otherwise>
									</c:choose>
							</select></td>
						</tr>
						<tr>
							<td>Hobbies</td>
							<td><select name="hob">
									<c:choose>
										<c:when test="${p.hobbies ==0}">
											<option value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option selected value="0">Show to all</option>
										</c:when>
										<c:when test="${p.hobbies ==1}">
											<option selected value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:when test="${p.hobbies ==2}">
											<option value="1">Hide from others</option>
											<option selected value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:otherwise>
											<option>Some problem is there.</option>
										</c:otherwise>
									</c:choose>
							</select></td>
						</tr>
						<tr>
							<td>About me</td>
							<td><select name="abm">
									<c:choose>
										<c:when test="${p.aboutUser ==0}">
											<option value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option selected value="0">Show to all</option>
										</c:when>
										<c:when test="${p.aboutUser ==1}">
											<option selected value="1">Hide from others</option>
											<option value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:when test="${p.aboutUser ==2}">
											<option value="1">Hide from others</option>
											<option selected value="2">Show only to friends</option>
											<option value="0">Show to all</option>
										</c:when>
										<c:otherwise>
											<option>Some problem is there.</option>
										</c:otherwise>
									</c:choose>
							</select></td>
						</tr>
						<tr>
							<td colspan="4" style="text-align: center;"><input
								type="submit" name="" value="Set Privacy"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>

</div>

<%@include file="footer.jsp"%>