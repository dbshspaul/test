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

import com.societatis.bean.NoteBean;
import com.societatis.bean.UserBean;
import com.societatis.model.Note;

@WebServlet({ "/createNote", "/displayNote", "/deleteNote", "/editNote",
		"/updateNote" })
public class NoteServ extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	NoteBean nbean;
	@EJB
	UserBean ubean;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession hs = req.getSession();
		String url = req.getRequestURL().toString();
		url = url.substring(url.lastIndexOf("/") + 1);
		String page = "";
		String msg = "";
		Note nt;
		Date dt;

		try {
			switch (url) {
			case "createNote":
				page = "notes.jsp";
				ubean.setEmail((String) hs.getAttribute("email"));
				nt = new Note();
				dt = new Date();
				nt.setNoteTitle(req.getParameter("noteTitle"));
				nt.setNote(req.getParameter("note"));
				nt.setCreationDate(dt);
				nt.setUser(ubean.getUserDetails());
				nbean.addNewNote(nt);
				msg = "Note has been created successfully.";
				break;
			case "displayNote":
				page = "displayNote.jsp";
				req.setAttribute("dspnt", nbean.getNoteByNoteId(Integer
						.parseInt(req.getParameter("noteId"))));

				break;
			case "deleteNote":
				page = "notes.jsp";
				nbean.deleteNote(Integer.parseInt(req.getParameter("noteId")));
				msg = "Note has been deleted successfully";
				break;

			case "editNote":
				page = "editNote.jsp";
				req.setAttribute("edtnt", nbean.getNoteByNoteId(Integer
						.parseInt(req.getParameter("noteId"))));
				break;

			case "updateNote":
				page = "notes.jsp";
				ubean.setEmail((String) hs.getAttribute("email"));
				nt = new Note();
				dt = new Date();

				nt.setNoteId(Integer.parseInt(req.getParameter("noteId")));
				nt.setNoteTitle(req.getParameter("noteTitle"));
				nt.setNote(req.getParameter("note"));
				nt.setCreationDate(dt);
				nt.setUser(ubean.getUserDetails());
				nbean.updateNote(nt);

				req.setAttribute("dspnt", nbean.getNoteByNoteId(Integer
						.parseInt(req.getParameter("noteId"))));
				msg = "Note has been Updated Successfully.";
				break;

			default:
				break;
			}
		} catch (NumberFormatException e) {
			msg = "Error: " + e.getMessage();
			e.printStackTrace();
		}
		req.setAttribute("msg", msg);
		req.getRequestDispatcher(page).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			doGet(req, resp);
	}
}