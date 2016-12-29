<%@include file="header.jsp"%>

<div class="art-layout-cell art-sidebar1">
	<%@include file="login.jsp"%>
	<div class="art-vmenublock clearfix">
		<ul class="navigation">
			<li><a href="forgetPassword.jsp">Forgot Password?<span
					class="ui_icon passrecov"></span></a></li>
			<li><a href="signUp.jsp">Register Here<span
					class="ui_icon services"></span></a></li>
			<li><a class="selected" href="index.jsp">About Us<span
					class="ui_icon aboutus"></span></a></li>
			<li><a href="contactUs.jsp">Contact Us<span
					class="ui_icon contactus"></span></a></li>
		</ul>
	</div>
</div>
<div class="art-layout-cell art-content">
	<%@include file="aboutUs.jsp"%>
</div>

<%@include file="footer.jsp"%>