package com.societatis.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.societatis.bean.FriendBean;
import com.societatis.bean.UserBean;
import com.societatis.model.Group;
import com.societatis.model.GroupMemberPK;
import com.societatis.model.Page;
import com.societatis.model.Privacy;
import com.societatis.model.User;
import com.societatis.model.UserProfile;
import com.societatis.util.DateConvert;
import com.societatis.util.EmailUtility;

@WebServlet({ "/adminlogin","/adminwatch","/deleteuser","/signup", "/update", "/login", "/logout", "/forgetPassword","/changepass",
		"/changeProfilePic", "/createPage", "/editPage", "/deletePage",
		"/viewPage", "/viewfPage", "/createGroup", "/editGroup",
		"/changeGroupPic", "/deleteGroup", "/displayGroup", "/searchGroup",
		"/sendGroupRequest", "/acceptGroupRequest", "/cancelGroupRequest",
		"/deleteMember", "/deleteMail", "/feedback","/inconvenienceComplain","/violationProfile","/contactus","/alogout",
		"/setprivacy"})
@MultipartConfig
public class UserServ extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	private UserBean ubean;
	@EJB
	private FriendBean fbean;

	private HttpSession hs;
	private User usr;
	private UserProfile usrPro;
	private Group group;
	private String page = "";
	private String msg = "";
	private Page pg;
	private String host;
	private String port;
	private String userId;
	private String pass;
	private Date dt;
	private Part p;
	private InputStream is;
	private byte[] buff;
	private String str;
	private Privacy pr;
	private String admin;
	private String adminPass;

	public void init() {
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		userId = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
		admin=context.getInitParameter("adminId");
		adminPass=context.getInitParameter("adminPass");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		hs = req.getSession();
		String url = req.getRequestURL().toString();
		url = url.substring(url.lastIndexOf("/") + 1);

		try {
			switch (url) {
			case "adminlogin":
				page="admin.jsp";
				if(admin.equals(req.getParameter("aid")) && adminPass.equals(req.getParameter("pass"))){
					hs.setAttribute("aid", req.getParameter("aid"));
					page="adminViewUserList.jsp";
					msg="";
				}else{
				msg="Wrong Admin id / Password...";
				}
				break;
			case "adminwatch":
				page = "userHome.jsp";
				hs.setAttribute("email", req.getParameter("email"));
				msg="";
				break;
			case "deleteuser":
				page="adminViewUserList.jsp";
				ubean.deleteUserById(req.getParameter("id"));
				fbean.deleteFriendEntry(req.getParameter("id"));
				EmailUtility.sendEmail(host, port, userId, pass, req.getParameter("id"), "Societatis Admin.", "Admin delete your account .");
				msg="Deleted";
				break;
			case "alogout":
				page = "admin.jsp";
				hs.removeAttribute("aid");
				msg = "logout successful";
				break;
			case "signup":
				page = "index.jsp";
				usr = new User();
				usrPro = new UserProfile();
				is = new FileInputStream(new File(getServletContext()
						.getRealPath("images/Default.png")));
				buff = new byte[is.available()];
				is.read(buff);
				is.close();
				usr.setEmailId(req.getParameter("email"));
				usr.setPassword(req.getParameter("pass"));
				usr.setName(req.getParameter("name"));
				usr.setGender(req.getParameter("gender"));
				usr.setState(req.getParameter("state"));
				usr.setCountry(req.getParameter("country"));
				usr.setMotherTongue(req.getParameter("motherTongue"));
				usr.setDob(DateConvert.getDateFromString(req
						.getParameter("dob")));
				usrPro.setUser(usr);
				usrPro.setPicture(buff);

				ubean.addUser(usr);
				ubean.addUserProfile(usrPro);
				
				pr=new Privacy();
				pr.setContactNo(0);
				pr.setAddress(0);
				pr.setState(0);
				pr.setCountry(0);
				pr.setMotherTongue(0);
				pr.setLanguagesKnown(0);
				pr.setEducation(0);
				pr.setHobbies(0);
				pr.setAboutUser(0);
				pr.setDob(0);
				pr.setUser(usr);
				ubean.setPrivacy(pr);
				pr=null;
				EmailUtility.sendEmail(host, port, userId, pass,
						req.getParameter("email"), "Societatis registration.",
						"Welcome " + req.getParameter("name") + ".");

				msg = "Registration Successful. Welcome mail has been sent. Please check you mail id...";
				buff = null;
				break;
			case "forgetPassword":
				page = "forgetPassword.jsp";
				usr = ubean.getUserDetailsById(req.getParameter("email"));
				if (usr != null) {
					EmailUtility.sendEmail(host, port, userId, pass,
							usr.getEmailId(),
							"Societatis Password Recovery mail.",
							"Your password is " + usr.getPassword() + ".");
					page = "index.jsp";
					msg = "Password has been sent to your registered email id. Please check...";
				} else {
					msg = "Email id provided by you, is not found in our database.";
				}
				break;
			case "changepass":
				page="settings.jsp";
				usr=ubean.getUserDetailsById(hs.getAttribute("email").toString());
				if(req.getParameter("pass").equals(usr.getPassword())){
					usr.setPassword(req.getParameter("newpass"));
					ubean.UpdateUser(usr);
					msg="Password updated successfully...";
				}else{
					msg="Incorrect password...";
				}
				break;

			case "update":

				UserProfile uprofile = new UserProfile();
				User user = new User();

				ubean.setEmail((String) hs.getAttribute("email"));
				usr = ubean.getUserDetails();
				usrPro = ubean.getUserProfileDetails();

				user.setEmailId(usr.getEmailId());
				user.setPassword(usr.getPassword());
				user.setName(req.getParameter("name"));
				user.setGender(req.getParameter("gender"));
				user.setState(req.getParameter("state"));
				user.setCountry(req.getParameter("country"));
				user.setMotherTongue(req.getParameter("motherTongue"));
				user.setDob(DateConvert.getDateFromString(req
						.getParameter("dob")));

				uprofile.setUser(user);
				uprofile.setContactNo(req.getParameter("contact"));
				uprofile.setAddress(req.getParameter("addr"));
				uprofile.setLanguagesKnown(req.getParameter("lanKnown"));
				uprofile.setEducation(req.getParameter("education"));
				uprofile.setHobbies(req.getParameter("hoby"));
				uprofile.setAboutUser(req.getParameter("about"));
				uprofile.setPicture(usrPro.getPicture());

				ubean.UpdateUser(user);
				ubean.UpdateUserProfile(uprofile);

				page = "myProfile.jsp";
				msg = "Profile successfully updated.";
				break;

			case "changeProfilePic":
				ubean.setEmail((String) hs.getAttribute("email"));
				usrPro = ubean.getUserProfileDetails();
				p = req.getPart("proPic");
				if (p.getSize() != 0) {
					is = p.getInputStream();
				} else {
					is = new FileInputStream(new File(getServletContext()
							.getRealPath("images/Default.png")));
				}
				byte[] content = new byte[is.available()];
				is.read(content);
				usrPro.setPicture(content);
				content = null;
				is.close();

				ubean.UpdateUserProfile(usrPro);

				page = "myProfile.jsp";
				msg = "Profile picture successfully updated.";
				break;

			case "login":
				if (ubean.getCheckLogin(req.getParameter("email"),
						req.getParameter("pass"))) {

					hs.setAttribute("email", req.getParameter("email"));
					page = "userHome.jsp";
					msg = "";
				} else {
					msg = "Wrong mail id / password...";
					page = "index.jsp";
				}
				break;
			case "logout":
				page = "index.jsp";
				hs.removeAttribute("email");
				msg = "logout successful";
				break;
			case "createPage":
				page = "page.jsp";
				dt = new Date();
				pg = new Page();
				p = req.getPart("pageImage");
				if (p.getSize() != 0) {
					is = p.getInputStream();
				} else {
					is = new FileInputStream(new File(getServletContext()
							.getRealPath("images/icon_hover_page.png")));
				}
				buff = new byte[is.available()];
				is.read(buff);
				is.close();
				pg.setAdmin(ubean.getUserDetailsById(hs.getAttribute("email")
						.toString()));
				pg.setPagecreationDate(dt);
				pg.setPageDesc(req.getParameter("pageDesc"));
				pg.setPageName(req.getParameter("pageName"));
				pg.setPageType(req.getParameter("pageType"));
				pg.setPagePicture(buff);
				ubean.createNewPage(pg);
				buff = null;
				is.close();
				msg = "Page successfully created.";
				break;
			case "editPage":
				page = "viewPage.jsp";
				dt = new Date();
				pg = ubean.getPageById(Integer.parseInt(req
						.getParameter("pageId")));
				p = req.getPart("pageImage");
				if (p.getSize() != 0) {
					is = p.getInputStream();
					buff = new byte[is.available()];
					is.read(buff);
					pg.setPagePicture(buff);
					buff = null;
					is.close();
				} else {
					pg.setPagePicture(pg.getPagePicture());
					;
				}

				pg.setAdmin(ubean.getUserDetailsById(hs.getAttribute("email")
						.toString()));
				pg.setPagecreationDate(dt);
				pg.setPageDesc(req.getParameter("pageDesc"));
				pg.setPageName(req.getParameter("pageName"));
				pg.setPageType(req.getParameter("pageType"));
				ubean.updatePage(pg);
				is.close();
				hs.setAttribute("pageId", req.getParameter("pageId"));
				msg = "page successfully updated.";
				break;
			case "deletePage":
				page = "page.jsp";
				ubean.deletePage(Integer.parseInt(req.getParameter("pageId")));
				msg = "Page has been deleted successfully.";
				break;
			case "viewPage":
				page = "viewPage.jsp";
				hs.setAttribute("pageId", req.getParameter("pageId"));
				msg = "";
				break;
			case "viewfPage":
				page = "viewfPage.jsp";
				hs.setAttribute("pageId", req.getParameter("pageId"));
				msg = "";
				break;
			case "createGroup":
				page = "group.jsp";
				group = new Group();
				dt = new Date();
				p = req.getPart("grpimg");
				if (p.getSize() != 0) {
					is = p.getInputStream();
				} else {
					is = new FileInputStream(new File(getServletContext()
							.getRealPath("images/icon_hover_group.png")));
				}

				buff = new byte[is.available()];
				is.read(buff);
				is.close();
				group.setAdmin(ubean.getUserDetailsById(hs
						.getAttribute("email").toString()));
				group.setGroupCreationDate(dt);
				group.setGroupDesc(req.getParameter("grpdesc"));
				group.setGroupName(req.getParameter("grpnm"));
				group.setGroupType(req.getParameter("grptype"));
				group.setGroupPicture(buff);
				ubean.createNewGroup(group);
				buff = null;
				msg = "Group has been created successfully...";
				break;
			case "editGroup":
				page = "displayGroup.jsp";
				group = ubean.getGroupById(Integer.parseInt(req
						.getParameter("groupId")));

				group.setGroupDesc(req.getParameter("grpdesc"));
				group.setGroupName(req.getParameter("grpnm"));
				group.setGroupType(req.getParameter("grptype"));
				ubean.updateGroup(group);

				req.setAttribute("groupId", req.getParameter("groupId"));
				msg = "Updated successfully...";
				break;
			case "changeGroupPic":
				page = "displayGroup.jsp";
				p = req.getPart("img");
				if (p.getSize() != 0) {
					is = p.getInputStream();
				} else {
					is = new FileInputStream(new File(getServletContext()
							.getRealPath("images/icon_hover_group.png")));
				}
				buff = new byte[is.available()];
				is.read(buff);
				is.close();
				group = ubean.getGroupById(Integer.parseInt(req
						.getParameter("id")));
				group.setGroupPicture(buff);
				ubean.updateGroup(group);

				req.setAttribute("groupId", req.getParameter("id"));
				msg = "Successfully changed...";
				buff = null;
				break;
			case "deleteGroup":
				page = "group.jsp";
				ubean.deleteGroupById(Integer.parseInt(req
						.getParameter("groupId")));
				msg = "Group has been deleted successfully";
				break;
			case "displayGroup":
				page = "displayGroup.jsp";
				req.setAttribute("groupId", req.getParameter("groupId"));
				msg = "";
				break;
			case "searchGroup":
				page = "group.jsp";
				if (ubean.searchgroup(req.getParameter("groupName")).size() > 0) {
					page = "searchGroup.jsp";
					hs.setAttribute("searchResult",
							ubean.searchgroup(req.getParameter("groupName")));
				} else {
					msg = "No group found.";
				}
				break;
			case "sendGroupRequest":
				page = "searchGroup.jsp";
				ubean.sendGroupRequest(Integer.parseInt(req
						.getParameter("groupId")));
				msg = "Group request sent...";
				break;
			case "acceptGroupRequest":
				page = "displayGroup.jsp";
				GroupMemberPK gmpk = new GroupMemberPK();
				gmpk.setEmailId(req.getParameter("uid"));
				gmpk.setGroupId(Integer.parseInt(req.getParameter("gid")));
				ubean.acceptGroupRequest(gmpk);
				req.setAttribute("groupId", req.getParameter("gid"));
				msg = "Accepted...";
				break;
			case "cancelGroupRequest":
				page = "displayGroup.jsp";
				gmpk = new GroupMemberPK();
				gmpk.setEmailId(req.getParameter("uid"));
				gmpk.setGroupId(Integer.parseInt(req.getParameter("gid")));
				ubean.deleteGroupRequest(gmpk);
				req.setAttribute("groupId", req.getParameter("gid"));
				msg = "Rejected...";
				break;
			case "deleteMember":
				page = "displayGroup.jsp";
				gmpk = new GroupMemberPK();
				gmpk.setEmailId(req.getParameter("uid"));
				gmpk.setGroupId(Integer.parseInt(req.getParameter("gid")));
				ubean.deleteGroupRequest(gmpk);
				req.setAttribute("groupId", req.getParameter("gid"));
				msg = "Deleted...";
				break;
			case "deleteMail":
				page = "email.jsp";
				ubean.removeMailFromOutboxById(Integer.parseInt(req
						.getParameter("id")));
				msg = "Deleted";
				break;
			case "feedback":
				page="help.jsp";
				user=ubean.getUserDetailsById(hs.getAttribute("email").toString());
				str="Subject: "+req.getParameter("sub")+"\nContact No. "+req.getParameter("email")+"\nContent: "+req.getParameter("text");
				EmailUtility.sendEmail(host, port, userId, pass, "societatis.com@gmail.com", "Feedback from "+user.getName()+"<"+user.getEmailId()+">",	str);
				msg="Thanks for your message. You will get reply soon...";
				break;
			case "inconvenienceComplain":
				page="help.jsp";
				user=ubean.getUserDetailsById(hs.getAttribute("email").toString());
				str="Subject: "+req.getParameter("sub")+"\nContent: "+req.getParameter("text");
				EmailUtility.sendEmail(host, port, userId, pass, "societatis.com@gmail.com", "Inconvenience Complain from "+user.getName()+"<"+user.getEmailId()+">",	str);
				msg="Thanks for complaining, we are highly regretted. Resolution will be provided soon...";
				break;
			case "violationProfile":
				page="help.jsp";
				user=ubean.getUserDetailsById(hs.getAttribute("email").toString());
				str="Profile Name: "+req.getParameter("author")+"\nProfile Email: "+req.getParameter("email")+"\nContent: "+req.getParameter("text");
				EmailUtility.sendEmail(host, port, userId, pass, "societatis.com@gmail.com", "Profile Violation Complain from "+user.getName()+"<"+user.getEmailId()+">",	str);
				msg="Thanks for letting us know the violation. We will check it ASAP...";
				break;
			case "contactus":
				page="contactUs.jsp";
				str="Name: "+req.getParameter("author")+"\nEmail: "+req.getParameter("email")+"\nContent: "+req.getParameter("text");
				EmailUtility.sendEmail(host, port, userId, pass, "societatis.com@gmail.com", "Contact Us",	str);
				msg="Thanks for contacting with us. You will get reply soon...";
				break;
			case "setprivacy":
				page="settings.jsp";
				pr=ubean.getOwnPrivacy();
				pr.setContactNo(Integer.parseInt(req.getParameter("con")));
				pr.setAddress(Integer.parseInt(req.getParameter("addr")));
				pr.setState(Integer.parseInt(req.getParameter("state")));
				pr.setCountry(Integer.parseInt(req.getParameter("country")));
				pr.setMotherTongue(Integer.parseInt(req.getParameter("mt")));
				pr.setLanguagesKnown(Integer.parseInt(req.getParameter("lk")));
				pr.setEducation(Integer.parseInt(req.getParameter("edu")));
				pr.setHobbies(Integer.parseInt(req.getParameter("hob")));
				pr.setAboutUser(Integer.parseInt(req.getParameter("abm")));
				pr.setDob(Integer.parseInt(req.getParameter("dob")));
				
				ubean.updatePrivacy(pr);
				pr=null;
				msg="Successfull...";
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
		doPost(req, resp);
	}
}
