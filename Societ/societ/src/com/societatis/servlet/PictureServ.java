package com.societatis.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.societatis.bean.PicBean;
import com.societatis.bean.UserBean;
import com.societatis.model.Album;
import com.societatis.model.Media;
import com.societatis.model.MediaComment;
import com.societatis.model.MediaLike;
import com.societatis.model.MediaLikePK;

@WebServlet({ "/addPicture", "/createAlbum", "/viewAlbum", "/deleteAlbum",
		"/deletePicture", "/like", "/unlike", "/flike", "/funlike","/comment","/gallery", "/viewfAlbum" })
@MultipartConfig
public class PictureServ extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	PicBean pbean;
	@EJB
	UserBean ubean;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession hs = req.getSession();
		String url = (req.getRequestURL().toString());
		url = url.substring(url.lastIndexOf("/") + 1);
		Album albm;
		Date date;
		String page = "";
		String msg = "";
		Part p;
		Media media;
		InputStream is;
		byte[] buff;
		MediaLike ml;
		MediaLikePK mlpk;
		MediaComment mc;

		try {
			switch (url) {

			case "createAlbum":
				page = "gallery.jsp";
				albm = new Album();
				date = new Date();

				albm.setAlbumName(req.getParameter("name"));
				albm.setCreationDate(date);
				albm.setUser(ubean.getUserDetailsById(hs.getAttribute("email")
						.toString()));
				pbean.createAlbum(albm);
				msg = "Album created";
				break;

			case "addPicture":
				page = "picture.jsp";
				media = new Media();
				date = new Date();
				p = req.getPart("file");
				is = p.getInputStream();
				buff = new byte[is.available()];
				is.read(buff);
				is.close();

				media.setAlbum(pbean.getAlbumbyId(Integer.parseInt(hs
						.getAttribute("albumId").toString())));
				media.setContentType(p.getContentType());
				media.setFileContent(buff);
				media.setUploadDate(date);
				media.setTitle(req.getParameter("title"));
				pbean.addPicture(media);
				msg = "Uploaded";
				break;
			case "viewAlbum":
				page = "picture.jsp";
				hs.setAttribute("albumId", req.getParameter("albumId"));
				msg = "";
				break;
			case "deleteAlbum":
				page = "gallery.jsp";
				pbean.deleteAlbumById(Integer.parseInt(req
						.getParameter("albumId")));
				msg = "Deleted";
				break;
			case "deletePicture":
				page = "picture.jsp";
				pbean.deletePictureById(Integer.parseInt(req
						.getParameter("picId")));
				msg = "Deleted";
				break;
			case "like":
				page="picture.jsp";
				ml=new MediaLike();
				mlpk=new MediaLikePK();
				mlpk.setEmailId(hs.getAttribute("email").toString());
				mlpk.setMediaId(Integer.parseInt(req.getParameter("id")));
				ml.setId(mlpk);
				ml.setMedia(pbean.getMediaById(Integer.parseInt(req.getParameter("id"))));
				ml.setUser(ubean.getUserDetailsById(hs.getAttribute("email").toString()));
				
				pbean.like(ml);
				msg="Liked";
				break;
			case "unlike":
				page="picture.jsp";
				mlpk=new MediaLikePK();
				mlpk.setEmailId(hs.getAttribute("email").toString());
				mlpk.setMediaId(Integer.parseInt(req.getParameter("id")));
				
				pbean.unlike(mlpk);
				msg="Unliked";
				break;
			case "flike":
				page="fmedia.jsp";
				ml=new MediaLike();
				mlpk=new MediaLikePK();
				mlpk.setEmailId(hs.getAttribute("email").toString());
				mlpk.setMediaId(Integer.parseInt(req.getParameter("id")));
				ml.setId(mlpk);
				ml.setMedia(pbean.getMediaById(Integer.parseInt(req.getParameter("id"))));
				ml.setUser(ubean.getUserDetailsById(hs.getAttribute("email").toString()));
				
				pbean.like(ml);
				msg="liked";
				break;
			case "funlike":
				page="fmedia.jsp";
				mlpk=new MediaLikePK();
				mlpk.setEmailId(hs.getAttribute("email").toString());
				mlpk.setMediaId(Integer.parseInt(req.getParameter("id")));
				
				pbean.unlike(mlpk);
				msg="unliked";
				break;
			case "comment":
				page="picture.jsp";
				mc=new MediaComment();
				date=new Date();
				mc.setCommentDate(date);
				mc.setComments(req.getParameter("comment"));
				mc.setMedia(pbean.getMediaById(Integer.parseInt(req.getParameter("id"))));
				mc.setUser(ubean.getUserDetailsById(hs.getAttribute("email").toString()));
				pbean.commentOnMedia(mc);
				msg="comment";
				break;
			case "gallery":
				page="falbum.jsp";
				req.setAttribute("fid", req.getParameter("friendId"));				
				msg="";
				break;
			case "viewfAlbum":
				page = "fmedia.jsp";
				hs.setAttribute("albumId", req.getParameter("albumId"));
				msg = "";
				break;
			default:
				break;
			}
		} catch (Exception e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		req.setAttribute("msg", msg);
		req.getRequestDispatcher(page).forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
