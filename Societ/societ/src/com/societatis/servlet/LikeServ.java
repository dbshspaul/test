package com.societatis.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.societatis.bean.PostBean;
import com.societatis.bean.UserBean;
import com.societatis.model.GroupPostLike;
import com.societatis.model.GroupPostLikePK;
import com.societatis.model.PageLike;
import com.societatis.model.PageLikePK;
import com.societatis.model.PagePostLike;
import com.societatis.model.PagePostLikePK;
import com.societatis.model.UserLikePK;
import com.societatis.model.UserPostLike;

@WebServlet({ "/likeUserPost", "/unlikeUserPost", "/likeUserPostWall",
		"/unlikeUserPostWall", "/likePagePost", "/unlikePagePost", "/likePage",
		"/unlikePage", "/likefPagePost", "/unlikefPagePost", "/likefPage",
		"/unlikefPage","/likeGroupPost", "/unlikeGroupPost" })
public class LikeServ extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	private PostBean postbean;
	@EJB
	private UserBean ubean;

	HttpSession hs;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		url = url.substring(url.lastIndexOf("/") + 1);
		String msg = "";
		String page = "";
		UserPostLike usrpostlike;
		UserLikePK ulpk;
		PagePostLike pplk;
		PagePostLikePK pplpk;
		PageLike pagelike;
		PageLikePK pagelikepk;
		GroupPostLike grpolike;
		GroupPostLikePK grpolikePK;
		hs = req.getSession();

		switch (url) {
		case "likeUserPost":
			page = "friendWall.jsp";
			usrpostlike = new UserPostLike();
			ulpk = new UserLikePK();
			ulpk.setEmailId(hs.getAttribute("email").toString());
			ulpk.setUserPostId(Integer.parseInt(req.getParameter("postid")));
			usrpostlike.setId(ulpk);
			usrpostlike.setUser(ubean.getUserDetailsById(hs.getAttribute(
					"email").toString()));
			usrpostlike.setUserPost(postbean.getUserPostById(Integer
					.parseInt(req.getParameter("postid"))));
			postbean.likeUserPost(usrpostlike);
			msg = "Liked";
			break;
		case "unlikeUserPost":
			page = "friendWall.jsp";
			ulpk = new UserLikePK();
			ulpk.setEmailId(hs.getAttribute("email").toString());
			ulpk.setUserPostId(Integer.parseInt(req.getParameter("postid")));
			postbean.unlikeUserPost(ulpk);
			msg = "Unliked";
			break;
		case "likeUserPostWall":
			page = "wall.jsp";
			usrpostlike = new UserPostLike();
			ulpk = new UserLikePK();
			ulpk.setEmailId(hs.getAttribute("email").toString());
			ulpk.setUserPostId(Integer.parseInt(req.getParameter("postid")));
			usrpostlike.setId(ulpk);
			usrpostlike.setUser(ubean.getUserDetailsById(hs.getAttribute(
					"email").toString()));
			usrpostlike.setUserPost(postbean.getUserPostById(Integer
					.parseInt(req.getParameter("postid"))));
			postbean.likeUserPost(usrpostlike);
			msg = "Liked";
			break;
		case "unlikeUserPostWall":
			page = "wall.jsp";
			usrpostlike = new UserPostLike();
			ulpk = new UserLikePK();
			ulpk.setEmailId(hs.getAttribute("email").toString());
			ulpk.setUserPostId(Integer.parseInt(req.getParameter("postid")));
			postbean.unlikeUserPost(ulpk);
			msg = "Unliked";
			break;
		/**************************** page *******************************/
		case "likefPagePost":
			page = "viewfPage.jsp";
			pplk = new PagePostLike();
			pplpk = new PagePostLikePK();
			pplpk.setEmailId(hs.getAttribute("email").toString());
			pplpk.setPagePostId(Integer.parseInt(req.getParameter("postid")));
			pplk.setPagePost(postbean.getPagePostById(Integer.parseInt(req
					.getParameter("postid"))));
			pplk.setUser(ubean.getUserDetailsById(hs.getAttribute("email")
					.toString()));
			pplk.setId(pplpk);

			postbean.likePagePost(pplk);
			msg = "L"
					+ "Liked";
			break;
		case "unlikefPagePost":
			page = "viewfPage.jsp";
			pplpk = new PagePostLikePK();
			pplpk.setEmailId(hs.getAttribute("email").toString());
			pplpk.setPagePostId(Integer.parseInt(req.getParameter("postid")));
			postbean.unlikePagePost(pplpk);
			msg = "Unliked";
			break;
		case "likefPage":
			page = "viewfPage.jsp";
			pagelike = new PageLike();
			pagelikepk = new PageLikePK();
			pagelikepk.setEmailId(hs.getAttribute("email").toString());
			pagelikepk.setPageId(Integer.parseInt(req.getParameter("pageid")));

			pagelike.setId(pagelikepk);
			pagelike.setPage(postbean.getPageById(Integer.parseInt(req
					.getParameter("pageid"))));
			pagelike.setUser(ubean.getUserDetailsById(hs.getAttribute("email")
					.toString()));

			postbean.likePage(pagelike);
			msg = "Liked";
			break;
		case "unlikefPage":
			page = "viewfPage.jsp";
			pagelikepk = new PageLikePK();
			pagelikepk.setEmailId(hs.getAttribute("email").toString());
			pagelikepk.setPageId(Integer.parseInt(req.getParameter("pageid")));

			postbean.unlikePage(pagelikepk);
			msg = "Unliked";
			break;
		case "likePagePost":
			page = "viewPage.jsp";
			pplk = new PagePostLike();
			pplpk = new PagePostLikePK();
			pplpk.setEmailId(hs.getAttribute("email").toString());
			pplpk.setPagePostId(Integer.parseInt(req.getParameter("postid")));
			pplk.setPagePost(postbean.getPagePostById(Integer.parseInt(req
					.getParameter("postid"))));
			pplk.setUser(ubean.getUserDetailsById(hs.getAttribute("email")
					.toString()));
			pplk.setId(pplpk);

			postbean.likePagePost(pplk);
			msg = "Liked";
			break;
		case "unlikePagePost":
			page = "viewPage.jsp";
			pplpk = new PagePostLikePK();
			pplpk.setEmailId(hs.getAttribute("email").toString());
			pplpk.setPagePostId(Integer.parseInt(req.getParameter("postid")));
			postbean.unlikePagePost(pplpk);
			msg = "Unliked";
			break;
		case "likePage":
			page = "viewPage.jsp";
			pagelike = new PageLike();
			pagelikepk = new PageLikePK();
			pagelikepk.setEmailId(hs.getAttribute("email").toString());
			pagelikepk.setPageId(Integer.parseInt(req.getParameter("pageid")));

			pagelike.setId(pagelikepk);
			pagelike.setPage(postbean.getPageById(Integer.parseInt(req
					.getParameter("pageid"))));
			pagelike.setUser(ubean.getUserDetailsById(hs.getAttribute("email")
					.toString()));

			postbean.likePage(pagelike);
			msg = "Liked";
			break;
		case "unlikePage":
			page = "viewPage.jsp";
			pagelikepk = new PageLikePK();
			pagelikepk.setEmailId(hs.getAttribute("email").toString());
			pagelikepk.setPageId(Integer.parseInt(req.getParameter("pageid")));

			postbean.unlikePage(pagelikepk);
			msg = "Unliked";
			break;
			
			/******************group********************/
		case "likeGroupPost":
			page = "groupPost.jsp";
			grpolike=new GroupPostLike();
			grpolikePK=new GroupPostLikePK();
			
			grpolikePK.setEmailId(hs.getAttribute("email").toString());
			grpolikePK.setGroupPostId(Integer.parseInt(req.getParameter("postid")));
			grpolike.setGroupPost(postbean.getGroupPostById(Integer.parseInt(req.getParameter("postid"))));
			grpolike.setId(grpolikePK);
			grpolike.setUser(ubean.getUserDetailsById(hs.getAttribute("email").toString()));

			postbean.likeGroupPost(grpolike);
			msg = "Liked";
			break;
		case "unlikeGroupPost":
			page = "groupPost.jsp";
			grpolikePK=new GroupPostLikePK();
			grpolikePK.setEmailId(hs.getAttribute("email").toString());
			grpolikePK.setGroupPostId(Integer.parseInt(req.getParameter("postid")));
			postbean.unlikeGroupPost(grpolikePK);
			msg = "Unliked";
			break;
		default:
			break;
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
