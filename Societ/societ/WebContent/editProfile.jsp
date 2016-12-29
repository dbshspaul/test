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
			<li><a class="selected" class="myprofile" href="myProfile.jsp">My
					Profile<span class="ui_icon profile"></span>
			</a></li>
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
						<td><!-- <img src="images/Default.png" height="53px"
										width="53px" /> --> <img alt=""
							src="data:image/jpeg;base64,${sessionScope['userBean'].getUserProfileDetailsById(sessionScope['email']).imageAsString}"
							height="53" width="53px" /></td>
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
				<h1>&nbsp &nbsp Update Profile</h1>
				<center>Edit and update.</center>
			</div>
		</div>

	</div>
	<div style="margin-left: 25px; margin-top: 25px; margin-right: 25px">
		<c:set property="email" target="${sessionScope['userBean']}"
			value="${sessionScope['email']}" />
		<c:set var="userOb" value="${sessionScope['userBean']['userDetails']}" />
		<c:set var="userProObj"
			value="${sessionScope['userBean']['userProfileDetails']}"></c:set>
		<div align="center" style="color: white;">

			<form action="update" method="post" enctype="multipart/form-data">
				<table cellpadding="0" align="center">
					<tr>
						<td colspan="3"><h1 style="text-align: center">Update Profile</h1></td>
					</tr>
					<tr>
						<td>Name</td>
						<td><input type="text" name="name" value="${userOb.name}"></td>
					</tr>
					<tr>
						<td>Contact No</td>
						<td><input type="text" name="contact"
							value="${userProObj.contactNo}"></td>
					</tr>
					<tr>
						<td>Address</td>
						<td><textarea name="addr">${userProObj.address}</textarea></td>
					</tr>
					<tr>
						<td>State</td>

						<td><input type="text" name="state" value="${userOb.state}"></td>
					</tr>
					<tr>
						<jsp:useBean id="country" class="com.societatis.util.CountryList"></jsp:useBean>
						<c:set var="cont" value="${country.countryList}" />
						<td>Country</td>

						<td><select name="country">
								<c:forEach items="${cont}" var="cntr">
									<c:choose>
										<c:when test="${cntr.equals(userOb.country)}">
											<option selected>${userOb.country}</option>
										</c:when>
										<c:otherwise>
											<option>${cntr}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>Mother Tongue</td>
						<jsp:useBean id="langs"
							class="com.societatis.util.MotherTongueList"></jsp:useBean>
						<c:set var="lang" value="${langs.motherToungueList}" />
						<td><select name="motherTongue">

								<c:forEach items="${lang}" var="ln">
									<c:choose>
										<c:when test="${ln.equals(userOb.motherTongue)}">
											<option selected>${userOb.motherTongue}</option>
										</c:when>
										<c:otherwise>
											<option>${ln}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
					</tr>
					<tr>
						<td>Languages known</td>
						<td><input type="text" name="lanKnown"
							value="${userProObj.languagesKnown}"></td>
					</tr>
					<tr>
						<td>Gender</td>

						<td><input type="radio" value="Male" name="gender">Male
							<input type="radio" value="Female" name="gender">Female
							<input type="radio" value="Others" name="gender">Others</td>
					</tr>
					<tr>
						<td>Date of Birth</td>


						<td><input id="datepicker" type="text" name="dob"
							value='<fmt:formatDate value="${userOb.dob}" pattern="yyyy-MM-dd"/>'></td>
					</tr>
					<tr>
						<td>Education</td>

						<td><input type="text" name="education"
							value="${userProObj.education}">
					</tr>
					<tr>
						<td>Hobbies</td>

						<td><input type="text" name="hoby"
							value="${userProObj.hobbies}">
					</tr>
					<tr>
						<td>About me</td>
						<td><textarea name="about">${userProObj.aboutUser}</textarea></td>
					</tr>

					<tr>
						<td colspan="4" style="text-align: center;"><input
							type="submit" name="" value="Update Profile"></td>
					</tr>
				</table>
			</form>

		</div>
	</div>
</div>


<%@include file="footer.jsp"%>