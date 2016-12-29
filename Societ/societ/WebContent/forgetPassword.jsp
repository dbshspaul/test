<%@include file="header.jsp"%>

<div class="art-layout-wrapper">
	<div class="art-content-layout">
		<div class="art-content-layout-row">
			<div class="art-layout-cell art-sidebar1">
				<%@include file="login.jsp"%>
				<div class="art-vmenublock clearfix">
					<ul class="navigation">
						<li><a class="selected" href="forgetPassword.jsp">Forgot Password?<span
								class="ui_icon passrecov"></span></a></li>
						<li><a href="signUp.jsp">Register Here<span
								class="ui_icon services"></span></a></li>
						<li><a href="index.jsp">About Us<span
								class="ui_icon aboutus"></span></a></li>
						<li><a href="contactUs.jsp">Contact Us<span
								class="ui_icon contactus"></span></a></li>
					</ul>
				</div>
			</div>
			<div class="art-layout-cell art-content">
				<div style="margin-left: 50px; margin-top: 50px">
					<h1>Password recovery...</h1>
					<div id="contact_form">
						<form method="post" name="" action="forgetPassword">

							<label for="email">Your Email:</label> <input type="email"
								id="" name="email"
								class="validate-email required input_field" required=""/>
							<div class="cleaner_h10"></div>

							<input type="submit" class="submit_btn" name="submit" id="submit"
								value="Retrive Password" /> <input type="reset" class="submit_btn"
								name="reset" id="reset" value="Reset" />

						</form>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>

<%@include file="footer.jsp"%>