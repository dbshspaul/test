package com.societatis.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.societatis.bean.FriendBean;
import com.societatis.bean.UserBean;
import com.societatis.model.User;

@WebServlet({ "/acceptRequest", "/addFriend", "/search", "/rejectRequest",
		"/removeFriend", "/viewFriend", "/friendWall", "/chat"})
public class FriendServ extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	private FriendBean fbean;
	@EJB
	private UserBean ubean;
	private String page = "";
	private String msg = "";
	HttpSession hs;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String url = req.getRequestURL().toString();
		url = url.substring(url.lastIndexOf("/") + 1);
		hs=req.getSession();

		try {
			switch (url) {
			/*case "search":
				page = "searchFriend.jsp";
				List<User> usrlst = fbean.searchFriends(req
						.getParameter("name"));

				if (usrlst.size() > 0) {
					req.setAttribute("frndlst", usrlst);
					msg = "";
					page = "searchFriend.jsp";
				} else {
					page = "searchFriend.jsp";
					msg = "No user found.";
				}
				break;*/
			case "search":
				page = "friend.jsp";
				List<User> usrlst = fbean.searchFriends(req
						.getParameter("name"));

				if (usrlst.size() > 0) {
					req.setAttribute("frndlst", usrlst);
					msg = "";
					page = "friend.jsp";
				} else {
					page = "friend.jsp";
					msg = "No user found.";
				}
				break;

			case "addFriend":
				page = "friend.jsp";
				fbean.sendFriendRequest(req.getParameter("frndid"));
				msg = "Friend request sent";
				break;

			case "acceptRequest":
				page = "friend.jsp";
				fbean.acceptFriendRequest(req.getParameter("frndid"));
				msg = "Friend request accepted";
				break;

			case "rejectRequest":
				page = "friend.jsp";
				fbean.rejectFriendRequest(req.getParameter("frndid"));
				msg = "Friend request rejected";
				break;

			case "removeFriend":
				page = "friend.jsp";
				fbean.removeFriend(req.getParameter("frndid"));
				msg = ubean.getUserDetailsById(req.getParameter("frndid"))
						.getName() + " has been removed from your friend list.";
				break;
			case "viewFriend":
				page = "viewFriend.jsp";
				req.setAttribute("friendId", req.getParameter("friendid"));
				break;
			case "friendWall":
				page = "friendWall.jsp";
				hs.setAttribute("friendId", req.getParameter("friendid"));
				break;
			case "chat":
				page = "chat.jsp";
				hs.setAttribute("friendId", req.getParameter("frndId"));
				msg="";
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
		this.doPost(req, resp);
	}

}
