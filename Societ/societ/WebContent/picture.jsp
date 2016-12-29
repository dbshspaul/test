<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>Enjoy the societatis photo gallery...</title>

<style type="text/css">
body {
	background: #222;
	color: #eee;
	margin-top: 20px;
	font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
}

a {
	color: #FFF;
}

a:hover {
	color: yellow;
	text-decoration: underline;
}

.thumbnails img {
	height: 80px;
	border: 4px solid #555;
	padding: 1px;
	margin: 0 10px 10px 0;
}

.thumbnails img:hover {
	border: 4px solid #00ccff;
	cursor: pointer;
}

.preview img {
	border: 4px solid #444;
	padding: 1px;
	width: 800px;
}
</style>

</head>
<body>

	<div class="gallery" align="center">
		<c:set var="pics"
			value="${sessionScope['picBean'].getAllPictures(sessionScope['albumId'])}" />
		<c:set var="album"
			value="${sessionScope['picBean'].getAlbumbyId(sessionScope['albumId'])}" />
		<h2>
			Album : <a href="viewAlbum?albumId=${album.albumId}">${album.albumName}</a>
		</h2>
		<br />

		<div class="thumbnails" style="width: 240px">
			<table>
				<tr>
					<c:forEach items="${pics}" var="pic">
						<td>
							<div align="center">
								<a href="#">${pic.title}</a>
							</div>
							<div align="center">
								<fmt:formatDate value="${pic.uploadDate}"
									pattern=" hh:mma dd/MM/YY" />
							</div>
							<div>
								<img onmouseover="preview.src=${pic.title}.src"
									name="${pic.title}"
									src="data:image/jpeg;base64,${pic.mediaAsString}" alt="" />
							</div> <c:set var="last"
								value="data:image/jpeg;base64,${pic.mediaAsString}" />
							<div align="center">
								<u><a href="l5.jsp?id=${pic.mediaId}">(${sessionScope['picBean'].getTotalNumberOfLikes(pic.mediaId)})like</a></u>
								<c:choose>
									<c:when test="${sessionScope['picBean'].isLiked(pic.mediaId)}">
										<a id="like" href="unlike?id=${pic.mediaId}">unlike</a>
									</c:when>
									<c:otherwise>
										<a id="unlike" href="like?id=${pic.mediaId}">like</a>
									</c:otherwise>
								</c:choose>
								<a href="deletePicture?picId=${pic.mediaId}">delete</a>
							</div>
						</td>
					</c:forEach>
				</tr>
			</table>
		</div>
		<br />
		<div style="float: left;">
			<h1 align="left">Add new...</h1>
			<form action="addPicture" enctype="multipart/form-data" method="post">
				<table>
					<tr>
						<td><input type="text" name="title"
							placeholder="Enter an title" required="" /></td>
					</tr>
					<tr>
						<td><input type="file" name="file" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="upload" /></td>
					</tr>
				</table>
			</form>
			<h3>${requestScope['msg']}</h3>
		</div>
		<div>
			<div class="preview" align="center">
				<img name="preview" src="${last}" alt="" />
			</div>



		</div>
	</div>

</body>
</html>