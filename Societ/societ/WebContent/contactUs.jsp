<%@include file="header.jsp"%>

<div class="art-layout-cell art-sidebar1">
	<%@include file="login.jsp"%>
	<div class="art-vmenublock clearfix">
		<ul class="navigation">
			<li><a href="forgetPassword.jsp">Forgot Password?<span
					class="ui_icon passrecov"></span></a></li>
			<li><a href="signUp.jsp">Register Here<span
					class="ui_icon services"></span></a></li>
			<li><a href="index.jsp">About Us<span
					class="ui_icon aboutus"></span></a></li>
			<li><a class="selected" href="contactUs.jsp">Contact Us<span
					class="ui_icon contactus"></span></a></li>
		</ul>
	</div>
</div>
<div style="margin-left: 50px;">
	<h1>Feel free to contact us...</h1>
	<div id="contact_form">
		<form method="post" name="contact" action="contactus">
			<label for="author">Your Name:</label> <input type="text" id="author"
				name="author" class="required input_field" required/>
			<div class="cleaner_h10"></div>

			<label for="email">Your Email:</label> <input type="email" id="email"
				name="email" class="validate-email required input_field" required=""/>
			<div class="cleaner_h10"></div>

			<label for="text">Message:</label>
			<textarea id="text" name="text" rows="0" cols="0" class="required" required=""></textarea>
			<div class="cleaner_h10"></div>

			<input type="submit" class="submit_btn" name="submit" id="submit"
				value="Send" /> <input type="reset" class="submit_btn" name="reset"
				id="reset" value="Reset" />
		</form>
	</div>
</div>

<%@include file="footer.jsp"%>