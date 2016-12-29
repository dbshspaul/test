package com.societatis.servlet;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.societatis.bean.UserBean;
import com.societatis.model.MailOutbox;
import com.societatis.model.User;
import com.societatis.util.EmailUtility;

@WebServlet("/SendEmail")
public class EmailSendingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	String page = "email.jsp";

	private String host;
	private String port;
	private String user;
	private String pass;
	private String msg="";
	@EJB
	private UserBean ub;
	private User usr;

	public void init() {
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession hs = request.getSession();
		MailOutbox mob=new MailOutbox();
		Date dt = new Date();


		String recipient = request.getParameter("recipient");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		usr=ub.getUserDetailsById(hs.getAttribute("email").toString());
		
		mob.setContent(content);
		mob.setRecipientAddress(recipient);
		mob.setSendDate(dt);
		mob.setSubject(subject);
		mob.setUser(usr);
		
		ub.addMailoutbox(mob);

		try {
			EmailUtility.sendEmail(host, port, user, pass, recipient, "Name: "+ usr.getName() + ", Email: "+ usr.getEmailId() + 
					", Subject: "+ subject, content);
			msg = "Mail has been sent successfully...";
		} catch (Exception ex) {
			ex.printStackTrace();
			msg = "There were an error: " + ex.getMessage();
		} finally {
			request.setAttribute("Message", msg);
			request.getRequestDispatcher(page).forward(request, response);
		}
	}
}