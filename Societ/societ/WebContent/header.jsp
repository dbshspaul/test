<!DOCTYPE html>
<html dir="ltr" lang="en-US">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Societatis...</title>
<meta name="viewport"
	content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

<!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<link rel="stylesheet" href="style.css" media="screen">
<!--[if lte IE 7]><link rel="stylesheet" href="style.ie7.css" media="screen" /><![endif]-->
<link rel="stylesheet" href="style.responsive.css" media="all">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="social, social networking, societatis, connecting friends, connecting world" />
<meta name="description"
	content="Societatis is social networking portal..." />
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="css/coda-slider.css" type="text/css"
	media="screen" charset="utf-8" />

<script src="js/jquery-1.2.6.js" type="text/javascript"></script>
<script src="js/jquery.scrollTo-1.3.3.js" type="text/javascript"></script>
<script src="js/jquery.localscroll-1.2.5.js" type="text/javascript"
	charset="utf-8"></script>
<script src="js/jquery.serialScroll-1.2.1.js" type="text/javascript"
	charset="utf-8"></script>
<script src="js/coda-slider.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.easing.1.3.js" type="text/javascript"
	charset="utf-8"></script>

<script src="jquery.js"></script>
<script src="script.js"></script>
<script src="script.responsive.js"></script>

<script>
	jQuery(function($) {
		'use strict';
		if ($.fn.slider) {
			$(".art-slidecontainer79").each(
					function() {
						var slideContainer = $(this), tmp;
						var inner = $(".art-slider-inner", slideContainer);
						var helper = null;

						if ($.support.transition) {
							helper = new BackgroundHelper();
							helper.init("fade", "next", $(".art-slide-item",
									inner).first().css(
									$.support.transition.prefix
											+ "transition-duration"));
							inner.children().each(function() {
								helper.processSlide($(this));
							});

						}

						inner.children().eq(0).addClass("active");
						slideContainer.slider({
							pause : 2600,
							speed : 600,
							repeat : true,
							animation : "fade",
							direction : "next",
							navigator : slideContainer
									.siblings(".art-slidenavigator79"),
							helper : helper
						});
					});
		}
	});
</script>
<style>
.art-content .art-postcontent-0 .layout-item-0 {
	padding-right: 10px;
	padding-left: 10px;
}

.art-content .art-postcontent-0 .layout-item-1 {
	color: #DCE3EA;
	background:;
	border-spacing: 10px 0px;
	border-collapse: separate;
}

.art-content .art-postcontent-0 .layout-item-2 {
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-color: #CFD8E2;
	border-right-color: #CFD8E2;
	border-bottom-color: #CFD8E2;
	border-left-color: #CFD8E2;
	color: #DCE3EA;
	padding-top: 10px;
	padding-right: 10px;
	padding-bottom: 10px;
	padding-left: 10px;
	border-radius: 30px;
}

.ie7 .art-post .art-layout-cell {
	border: none !important;
	padding: 0 !important;
}

.ie6 .art-post .art-layout-cell {
	border: none !important;
	padding: 0 !important;
}

.art-slidecontainer79 {
	position: relative;
	width: 298px;
	height: 183px;
}

.art-slidecontainer79 .art-slide-item {
	
}

.art-slidecontainer79 .art-slide-item {
	-webkit-transition: 600ms ease-in-out opacity;
	-moz-transition: 600ms ease-in-out opacity;
	-ms-transition: 600ms ease-in-out opacity;
	-o-transition: 600ms ease-in-out opacity;
	transition: 600ms ease-in-out opacity;
	position: absolute;
	display: none;
	left: 0;
	top: 0;
	opacity: 0;
	width: 100%;
	height: 100%;
}

.art-slidecontainer79 .active, .art-slidecontainer79 .next,
	.art-slidecontainer79 .prev {
	display: block;
}

.art-slidecontainer79 .active {
	opacity: 1;
}

.art-slidecontainer79 .next, .art-slidecontainer79 .prev {
	width: 100%;
}

.art-slidecontainer79 .next.forward, .art-slidecontainer79 .prev.back {
	opacity: 1;
}

.art-slidecontainer79 .active.forward {
	opacity: 0;
}

.art-slidecontainer79 .active.back {
	opacity: 0;
}

.art-slide790 {
	background-image: url('images/slide790.jpg');
	background-size: 100%;
	background-position: 0 0;
	background-repeat: no-repeat;
}

.art-slide791 {
	background-image: url('images/slide791.jpg');
	background-size: 100%;
	background-position: 0 0;
	background-repeat: no-repeat;
}

.art-slide792 {
	background-image: url('images/slide792.jpg');
	background-size: 100%;
	background-position: 0 0;
	background-repeat: no-repeat;
}

/* .art-slide793 {
	background-image: url('images/slide793.jpg');
	background-size: 100%;
	background-position: 0 0;
	background-repeat: no-repeat;
}

.art-slide794 {
	background-image: url('images/slide794.jpg');
	background-size: 100%;
	background-position: 0 0;
	background-repeat: no-repeat;
}

.art-slide795 {
	background-image: url('images/slide795.jpg');
	background-size: 100%;
	background-position: 0 0;
	background-repeat: no-repeat;
}

.art-slide796 {
	background-image: url('images/slide796.jpg');
	background-size: 100%;
	background-position: 0 0;
	background-repeat: no-repeat;
}

.art-slide797 {
	background-image: url('images/slide797.jpg');
	background-size: 100%;
	background-position: 0 0;
	background-repeat: no-repeat;
}

.art-slide798 {
	background-image: url('images/slide798.jpg');
	background-size: 100%;
	background-position: 0 0;
	background-repeat: no-repeat;
}
 */
.art-slidenavigator79 {
	display: inline-block;
	position: absolute;
	direction: ltr !important;
	top: 163px;
	left: 39.6%;
	z-index: 101;
	line-height: 0 !important;
	-webkit-background-origin: border !important;
	-moz-background-origin: border !important;
	background-origin: border-box !important;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	text-align: center;
	white-space: nowrap;
}

.art-slidenavigator79 {
	background: #B9C2CB;
	background: rgba(185, 194, 203, 0.6);
	padding: 5px;
}

.art-slidenavigator79>a {
	background: #728597;
	-webkit-border-radius: 10%;
	-moz-border-radius: 10%;
	border-radius: 10%;
	margin: 0 10px 0 0;
	width: 10px;
	height: 10px;
}

.art-slidenavigator79>a.active {
	background: #FA681E;
	-webkit-border-radius: 10%;
	-moz-border-radius: 10%;
	border-radius: 10%;
	margin: 0 10px 0 0;
	width: 10px;
	height: 10px;
}

.art-slidenavigator79>a:hover {
	background: #455B73;
	-webkit-border-radius: 10%;
	-moz-border-radius: 10%;
	border-radius: 10%;
	margin: 0 10px 0 0;
	width: 10px;
	height: 10px;
}
</style>
</head>
<body>
	<div id="art-main">
		<div class="art-sheet clearfix">
			<header class="art-header">
				<div class="art-slider art-slidecontainerheader" data-width="900"
					data-height="250">
					<div class="art-slider-inner">
						<div class="art-slide-item art-slideheader0"></div>
						<!-- <div class="art-slide-item art-slideheader1"></div> -->
						<div class="art-slide-item art-slideheader2"></div>
						<div class="art-slide-item art-slideheader3"></div>

					</div>
				</div>
				<div class="art-slidenavigator art-slidenavigatorheader"
					data-left="91.11">
					<a href="#" class="art-slidenavigatoritem"></a>
					<!-- <a href="#"
						class="art-slidenavigatoritem"></a> -->
					<a href="#" class="art-slidenavigatoritem"></a><a href="#"
						class="art-slidenavigatoritem"></a>
				</div>



				<div class="art-shapes"></div>
				<h1 class="art-headline" data-left="1.2%">
					<a href="#">SOCIETATIS</a>
				</h1>
				<h2 class="art-slogan" data-left="17.17%">CONNECT WITH THE
					WORLD...</h2>

			</header>
			<div class="art-layout-wrapper">
				<div class="art-content-layout">
					<div class="art-content-layout-row">