package com.societatis.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.societatis.bean.PicBean;
import com.societatis.bean.UserBean;
import com.societatis.model.UserProfile;

@WebServlet("/img")
public class PicServ extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	UserBean usrbean;
	@EJB
	PicBean pb;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		byte[] content = null;
		
			String email = req.getParameter("email");
			usrbean.setEmail(email);
			UserProfile usr = usrbean.getUserProfileDetails();
			content = usr.getPicture();
		

		ServletOutputStream sos = resp.getOutputStream();
		sos.write(content);
		sos.flush();
	}
}
