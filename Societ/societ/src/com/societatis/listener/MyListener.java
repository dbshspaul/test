package com.societatis.listener;

import javax.ejb.EJB;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.societatis.bean.FriendBean;
import com.societatis.bean.MessageBean;
import com.societatis.bean.NoteBean;
import com.societatis.bean.PicBean;
import com.societatis.bean.PostBean;
import com.societatis.bean.UserBean;

@WebListener
public class MyListener implements HttpSessionListener {

	@EJB
	private UserBean ub;
	@EJB
	private PicBean pb;
	@EJB
	private NoteBean nb;
	@EJB
	private FriendBean fb;
	@EJB
	private MessageBean mb;
	@EJB
	private PostBean pob;
	

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute("userBean", ub);
		se.getSession().setAttribute("picBean", pb);
		se.getSession().setAttribute("noteBean", nb);
		se.getSession().setAttribute("friendBean", fb);
		se.getSession().setAttribute("messageBean", mb);
		se.getSession().setAttribute("postBean", pob);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		se.getSession().removeAttribute("userBean");
		se.getSession().removeAttribute("picBean");
		se.getSession().removeAttribute("noteBean");
		se.getSession().removeAttribute("friendBean");
		se.getSession().removeAttribute("messageBean");
		se.getSession().removeAttribute("postBean");
	}

}
