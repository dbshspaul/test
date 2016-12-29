package com.societatis.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import com.societatis.model.Note;

@Stateless
public class NoteBean {

	@EJB
	UserBean ubean;
	
	@Inject
	HttpSession hs;
	
	@PersistenceContext
	EntityManager em;

	public void addNewNote(Note n) {
		em.persist(n);
	}
	
	public void updateNote(Note n){
		em.merge(n);
	}
	public void deleteNote(int id){
		em.remove(this.getNoteByNoteId(id));
	}
	
	public List<Note> getAllNotes(){
		ubean.setEmail((String)hs.getAttribute("email"));
		TypedQuery<Note> q=em.createQuery("select n from Note n where n.user=:usr order by n.creationDate DESC", Note.class);
		q.setParameter("usr", ubean.getUserDetails());
		return q.getResultList();
	}
	public Note getNoteByNoteId(int id){
		return em.find(Note.class, id);
	}

}
