package com.societatis.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.societatis.bean.UserBean;
import com.societatis.model.User;

@WebServlet({ "/autocomplite" })
public class AutocompliteServ extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	UserBean ubean;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = "";
		PrintWriter pw = resp.getWriter();

		url = req.getRequestURL().toString();
		url = url.substring(url.lastIndexOf("/") + 1);
		resp.setContentType("application/json");

		try {
			switch (url) {
			case "autocomplite":
				List<User> lst = ubean.getUserByName(req.getParameter("term"));
				List<String> name = new ArrayList<String>();
				for (User u : lst) {
					name.add(u.getName()+"<"+u.getEmailId()+">");					
				}
				
				String srchlst = new Gson().toJson(name);
				pw.println(srchlst);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error: "+e.getMessage());
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
