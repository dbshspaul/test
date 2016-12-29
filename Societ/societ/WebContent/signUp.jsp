<%@include file="dtHeader.jsp"%>

<div class="art-layout-cell art-sidebar1">
	<%@include file="login.jsp"%>
	<div class="art-vmenublock clearfix">
		<ul class="navigation">
			<li><a href="forgetPassword.jsp">Forgot Password?<span
					class="ui_icon passrecov"></span></a></li>
			<li><a class="selected" href="signUp.jsp">Register Here<span
					class="ui_icon services"></span></a></li>
			<li><a href="index.jsp">About Us<span
					class="ui_icon aboutus"></span></a></li>
			<li><a href="contactUs.jsp">Contact Us<span
					class="ui_icon contactus"></span></a></li>
		</ul>
	</div>
</div>

<div class="art-layout-cell art-content">
	<div style="margin-left: 50px;">
		<h1>New user Registration...</h1>
		<div id="contact_form">
			<form method="post" name="" action="signup">
				<label></label> <input type="text" id="" name="name"
					class="required input_field" placeholder="Name" required="" />
				<div class="cleaner_h10"></div>

				<label></label> <input type="email" id="" name="email"
					class="validate-email required input_field" placeholder="Email Id"
					required="" />
				<div class="cleaner_h10"></div>

				<!-- <label></label> <input type="email" id="" name="email"
					class="validate-email required input_field"
					placeholder="Re-enter Email Id" data-validation="confirmation" />
				<div class="cleaner_h10"></div> -->

				<label></label> <input type="password" id="password" name="pass"
					class="required input_field" placeholder="Password" required="" />
				<div class="cleaner_h10"></div>
				
				<label></label> <input type="password" id="password-check" name="conpass"
					class="required input_field" placeholder="Re-enter Password" required="" />
				<div class="cleaner_h10"></div>

				<label></label> <input type="text" id="" name="state"
					class="required input_field" placeholder="State" />
				<div class="cleaner_h10"></div>

				<label></label>
				<%@include file="country.jsp"%>
				<br /> <label></label>
				<%@include file="motherTongue.jsp"%>
				<br /> <label></label> <input type="text" id="datepicker"
					name="dob" class="required input_field" placeholder="Date of Birth"
					required="" />
				<div class="cleaner_h10"></div>

				<label></label>
				<table>
					<tr>
						<td>Gender :</td>
						<td><input type="radio" value="Male" name="gender">Male
							<input type="radio" value="Female" name="gender">Female <input
							type="radio" value="Others" name="gender">Others</td>
					</tr>
				</table>
				<br> <label><input type="checkbox" required="">
					Accept <a href="#"> Terms and conditions</a> </label><br>
				<input type="submit" class="submit_btn" name="submit" id="submit"
					value="Sign Up" /> <input type="reset" class="submit_btn"
					name="reset" id="reset" value="Reset" />
			</form>
		</div>
	</div>
</div>

<%@include file="footer.jsp"%>