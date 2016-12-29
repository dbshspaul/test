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

import com.societatis.bean.PostBean;
import com.societatis.bean.UserBean;
import com.societatis.model.GroupPost;
import com.societatis.model.GroupPostComment;
import com.societatis.model.PagePost;
import com.societatis.model.PagePostComment;
import com.societatis.model.UserPost;
import com.societatis.model.UserPostComment;

@WebServlet({ "/usertextpost", "/userimgpost","/deleteuserpost", "/postusercomment",
		"/postusercommentwall", "/pagetextpost", "/pageimgpost",
		"/pagepostcomment","/fpagepostcomment","/deletePagePost",
		"/groupPost", "/grouptextpost", "/groupimgpost",
		"/grouppostcomment" })
@MultipartConfig
public class PostServ extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	PostBean posbean;
	@EJB
	UserBean ubean;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		url = url.substring(url.lastIndexOf("/") + 1);

		String msg = "";
		String page = "";
		Date dt;
		UserPost up;
		PagePost pp;
		PagePostComment pgpostcmt;
		UserPostComment upc;
		GroupPost grpost;
		Part p;
		byte[] buff;
		InputStream is;
		HttpSession hs = req.getSession();

		try {
			switch (url) {
			case "usertextpost":
				page = "wall.jsp";
				dt = new Date();
				up = new UserPost();
				up.setUser(ubean.getUserDetailsById((String) hs
						.getAttribute("email")));
				up.setUserPostData(req.getParameter("usertextpost").getBytes());
				up.setUserPostDate(dt);
				up.setUserPostType("text/plain");

				posbean.setUserPost(up);
				msg = "Status has been posted successfully...";
				break;
			case "userimgpost":
				page = "wall.jsp";
				dt = new Date();
				up = new UserPost();
				p = req.getPart("userimagepost");
				is = p.getInputStream();
				buff = new byte[is.available()];
				is.read(buff);

				up.setUser(ubean.getUserDetailsById((String) hs
						.getAttribute("email")));
				up.setUserPostData(buff);
				up.setUserPostDate(dt);
				up.setUserPostType(p.getContentType());
				posbean.setUserPost(up);
				msg = "Posted successfully...";
				is.close();
				break;
				
			case "deleteuserpost":
				page ="wall.jsp";
				posbean.deleteUserPostById(Integer.parseInt(req.getParameter("id")));
				msg="Deleted";
				break;

			case "postusercommentwall":
				page = "wall.jsp";
				upc = new UserPostComment();
				dt = new Date();
				upc.setCommentDate(dt);
				upc.setUser(ubean.getUserDetailsById((String) hs
						.getAttribute("email")));
				upc.setUserPost(posbean.getUserPostById(Integer.parseInt(req
						.getParameter("userpostid"))));
				upc.setUserPostComment(req.getParameter("comment"));
				posbean.setUserPostComment(upc);

				msg = "";
				break;

			case "postusercomment":
				page = "friendWall.jsp";
				upc = new UserPostComment();
				dt = new Date();
				upc.setCommentDate(dt);
				upc.setUser(ubean.getUserDetailsById((String) hs
						.getAttribute("email")));
				upc.setUserPost(posbean.getUserPostById(Integer.parseInt(req
						.getParameter("userpostid"))));
				upc.setUserPostComment(req.getParameter("comment"));
				posbean.setUserPostComment(upc);

				msg = "";
				break;

			/******************************** for page **********************************/

			case "pagetextpost":
				page = "viewPage.jsp";
				dt = new Date();
				pp = new PagePost();
				pp.setPage(ubean.getPageById(Integer.parseInt(req
						.getParameter("pageId"))));
				pp.setPagePostData(req.getParameter("pagetextpost").getBytes());
				pp.setUser(ubean.getUserDetailsById(hs.getAttribute("email")
						.toString()));
				pp.setPagePostDate(dt);
				pp.setPagePostType("text/plain");
				posbean.setPagePost(pp);
				msg = "Posted successfully...";
				break;
			case "pageimgpost":
				page = "viewPage.jsp";
				dt = new Date();
				pp = new PagePost();
				p = req.getPart("pageimgpost");
				is = p.getInputStream();
				buff = new byte[is.available()];
				is.read(buff);
				pp.setPage(ubean.getPageById(Integer.parseInt(req
						.getParameter("pageId"))));
				pp.setPagePostData(buff);
				pp.setUser(ubean.getUserDetailsById(hs.getAttribute("email")
						.toString()));
				pp.setPagePostDate(dt);
				pp.setPagePostType(p.getContentType());
				posbean.setPagePost(pp);
				msg = "Posted successfully...";
				is.close();
				break;
				
			case "deletePagePost":
				page = "viewPage.jsp";
				posbean.deletePagePostById(Integer.parseInt(req.getParameter("postId")));
				msg = "Deleted";
				break;

			case "pagepostcomment":
				page = "viewPage.jsp";
				dt = new Date();
				pgpostcmt = new PagePostComment();
				pgpostcmt.setCommentDate(dt);
				pgpostcmt.setUser(ubean.getUserDetailsById(hs.getAttribute(
						"email").toString()));
				pgpostcmt.setPagePostComment(req.getParameter("comment"));
				pgpostcmt.setPagePost(posbean.getPagePostById(Integer
						.parseInt(req.getParameter("pagepostid"))));

				posbean.setPagePostComment(pgpostcmt);
				msg = "Done...";
				break;
			case "fpagepostcomment":
				page = "viewfPage.jsp";
				dt = new Date();
				pgpostcmt = new PagePostComment();
				pgpostcmt.setCommentDate(dt);
				pgpostcmt.setUser(ubean.getUserDetailsById(hs.getAttribute(
						"email").toString()));
				pgpostcmt.setPagePostComment(req.getParameter("comment"));
				pgpostcmt.setPagePost(posbean.getPagePostById(Integer
						.parseInt(req.getParameter("pagepostid"))));

				posbean.setPagePostComment(pgpostcmt);
				msg = "Done...";
				break;
			/***************************** GROUP ************************************/
			case "groupPost":
				page = "groupPost.jsp";
				hs.setAttribute("groupId", req.getParameter("groupId"));
				msg = "";
				break;
			case "grouptextpost":
				page = "groupPost.jsp";
				grpost = new GroupPost();
				dt = new Date();
				grpost.setGroup(ubean.getGroupById(Integer.parseInt(req
						.getParameter("groupId"))));
				grpost.setGroupPostData(req.getParameter("grouptextpost")
						.getBytes());
				grpost.setGroupPostType("text/plain");
				grpost.setGroupPostDate(dt);
				grpost.setUser(ubean.getUserDetailsById(hs
						.getAttribute("email").toString()));
				posbean.postOnGroup(grpost);
				msg = "posted";
				break;
			case "groupimgpost":
				page = "groupPost.jsp";
				grpost = new GroupPost();
				dt = new Date();
				p = req.getPart("groupimagepost");
				is = p.getInputStream();
				buff = new byte[is.available()];
				is.read(buff);

				grpost.setGroup(ubean.getGroupById(Integer.parseInt(req
						.getParameter("groupId"))));
				grpost.setGroupPostData(buff);
				grpost.setGroupPostType(p.getContentType());
				grpost.setGroupPostDate(dt);
				grpost.setUser(ubean.getUserDetailsById(hs
						.getAttribute("email").toString()));
				posbean.postOnGroup(grpost);
				msg = "posted";
				break;
			case "grouppostcomment":
				page = "groupPost.jsp";
				GroupPostComment gpc=new GroupPostComment();
				dt=new Date();
				gpc.setCommentDate(dt);
				gpc.setGroupPost(posbean.getGroupPostById(Integer.parseInt(req.getParameter("grouppostid"))));
				gpc.setGroupPostComment(req.getParameter("comment"));
				gpc.setUser(ubean.getUserDetailsById(hs.getAttribute("email").toString()));
				
				posbean.setGroupPostComment(gpc);
				msg = "Done...";
				break;
			default:
				break;
			}
		} catch (Exception e) {
			msg = "Error: " + e.getMessage();
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
