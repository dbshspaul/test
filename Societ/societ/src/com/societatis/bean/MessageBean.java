package com.societatis.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import com.societatis.model.Message;

@Stateless
public class MessageBean {

	@PersistenceContext
	EntityManager em;
	@Inject
	HttpSession hs;

	public void sendMessage(Message msg) {
		em.persist(msg);
	}

	public Message getMessageById(int id) {
		return em.find(Message.class, id);
	}
	
	public void deleteMessageById(int id) {
		em.remove(getMessageById(id));
	}

	public List<Message> getInboxMessages() {
		TypedQuery<Message> q = em
				.createQuery(
						"select m from Message m where m.recipientId=:email and (m.readStatus=0 or m.readStatus=1) order by m.messageSendDate DESC",
						Message.class);
		q.setParameter("email", hs.getAttribute("email"));
		return q.getResultList();
	}

	public List<Message> getOutboxMessages() {
		TypedQuery<Message> q = em
				.createQuery(
						"select m from Message m where m.recipientId=:email and m.readStatus=3 order by m.messageSendDate DESC",
						Message.class);
		q.setParameter("email", hs.getAttribute("email"));
		return q.getResultList();
	}

	public List<Message> getUnreadMessages() {
		TypedQuery<Message> q = em
				.createQuery(
						"select m from Message m where m.recipientId=:email and m.readStatus=0 order by m.messageSendDate DESC",
						Message.class);
		q.setParameter("email", hs.getAttribute("email"));
		return q.getResultList();
	}
	
	public void setMessageAsRead(int id){
		Message m=getMessageById(id);
		m.setReadStatus(1);
		em.merge(m);
	}
}
