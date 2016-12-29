package com.societatis.servlet;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.societatis.bean.MessageBean;
import com.societatis.bean.UserBean;
import com.societatis.model.Message;

@WebServlet({"/sendMessage","/newMessageDisplay","/messageDisplay","/deleteInboxMessage","/deleteOutboxMessage"})
public class MessageServ extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	@EJB
	private MessageBean mbean;
	@EJB
	private UserBean ubean;
	private String msg="";
	private String page="";
	private Message message;
	private Date dt;
	private HttpSession hs;
	
	
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	hs=req.getSession();
	String url = req.getRequestURL().toString();
	url = url.substring(url.lastIndexOf("/") + 1);
	
	switch (url) {
	case "sendMessage":
		page="messageWriteNow.jsp";
		message=new Message();
		dt=new Date();
		String recipient = req.getParameter("id");
		recipient=recipient.substring(recipient.lastIndexOf("<")+1, recipient.lastIndexOf(">"));
		
		message.setMessageSendDate(dt);
		message.setReadStatus(0);
		message.setRecipientId(recipient);
		message.setMessage(req.getParameter("content"));
		message.setSender(ubean.getUserDetailsById(hs.getAttribute("email").toString()));
		mbean.sendMessage(message);
		
		message=new Message();
		message.setMessageSendDate(dt);
		message.setReadStatus(3);
		message.setRecipientId(hs.getAttribute("email").toString());
		message.setMessage(req.getParameter("content"));
		message.setSender(ubean.getUserDetailsById(recipient));
		mbean.sendMessage(message);
		msg="message sent successfully.";
		break;
	case "newMessageDisplay":
		page="messageDisplay.jsp";
		req.setAttribute("msgid", req.getParameter("msgid"));
		mbean.setMessageAsRead(Integer.parseInt(req.getParameter("msgid")));
		msg="";
		break;
	case "messageDisplay":
		page="messageDisplay.jsp";
		req.setAttribute("msgid", req.getParameter("msgid"));
		msg="";
		break;		
	case "deleteInboxMessage":
		page="message.jsp";
		mbean.deleteMessageById(Integer.parseInt(req.getParameter("msgid")));
		msg="Message has been deleted...";
		break;
	case "deleteOutboxMessage":
		page="messageOutbox.jsp";
		mbean.deleteMessageById(Integer.parseInt(req.getParameter("msgid")));
		msg="Message has been deleted...";
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
