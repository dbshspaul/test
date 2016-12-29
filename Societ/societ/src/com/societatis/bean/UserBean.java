package com.societatis.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import com.societatis.model.Group;
import com.societatis.model.GroupMember;
import com.societatis.model.GroupMemberPK;
import com.societatis.model.MailOutbox;
import com.societatis.model.Page;
import com.societatis.model.PageLike;
import com.societatis.model.Privacy;
import com.societatis.model.User;
import com.societatis.model.UserProfile;

@Stateless
public class UserBean {
	@PersistenceContext
	private EntityManager em;
	private String email;
	@Inject
	private HttpSession hs;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addUser(User user) {
		em.persist(user);
	}

	public void addUserProfile(UserProfile up) {
		em.persist(up);
	}

	public User getUserDetails() {
		User user = em.find(User.class, email);
		return user;
	}

	public UserProfile getUserProfileDetails() {
		return em.find(UserProfile.class, email);
	}

	public User getUserDetailsById(String id) {
		return em.find(User.class, id);
	}
	
	public UserProfile getUserProfileDetailsById(String id) {
		 return em.find(UserProfile.class, id);
	}

	public boolean getCheckLogin(String email, String pass) {

		Query q = em.createQuery("select s from User s where s.emailId='"
				+ email + "' and s.password= '" + pass + "'");
		return q.getResultList().size() > 0;

	}

	public void UpdateUser(User usr) {
		em.merge(usr);
	}

	public void UpdateUserProfile(UserProfile usrpro) {
		em.merge(usrpro);
	}

	public List<User> getUserByName(String name) {
		TypedQuery<User> q = em.createQuery(
				"select u from User u where UPPER(u.name) like :name",
				User.class);
		q.setParameter("name", "%" + name.toUpperCase() + "%");
		return q.getResultList();
	}

	public List<User> getAllUser() {
		TypedQuery<User> q = em.createQuery(
				"select u from User u",	User.class);
		return q.getResultList();
	}
	
	public void deleteUserById(String id){
		em.remove(getUserDetailsById(id));
	}
	
	/**************** page *******************/

	public Page getPageById(int id) {
		return em.find(Page.class, id);
	}

	public void createNewPage(Page pg) {
		em.persist(pg);
	}

	public void deletePage(int pageId) {
		em.remove(getPageById(pageId));
	}

	public void updatePage(Page pg) {
		em.merge(pg);
	}

	public List<Page> getAllPages(String userId) {
		TypedQuery<Page> q = em.createQuery(
				"select p from Page p where p.admin.emailId=:usr", Page.class);
		q.setParameter("usr", userId);
		return q.getResultList();
	}

	public List<Page> getAllPagesByName(String name) {
		TypedQuery<Page> q = em
				.createQuery(
						"select p from Page p where UPPER(p.pageName) like :name and p.admin.emailId<>:userid",
						Page.class);
		q.setParameter("name", "%" + name.toUpperCase() + "%");
		q.setParameter("userid", hs.getAttribute("email"));
		return q.getResultList();
	}

	public List<PageLike> getAllLikePages() {
		TypedQuery<PageLike> q = em
				.createQuery(
						"select p from PageLike p where p.user.emailId=:userid and p.page.admin.emailId<>:adminid",
						PageLike.class);
		q.setParameter("userid", hs.getAttribute("email"));
		q.setParameter("adminid", hs.getAttribute("email"));
		return q.getResultList();
	}

	/************************ Group *************************************/
	public void createNewGroup(Group gr) {
		em.persist(gr);
	}

	public void updateGroup(Group gr) {
		em.merge(gr);
	}

	public Group getGroupById(int groupId) {
		return em.find(Group.class, groupId);
	}

	public void deleteGroupById(int groupId) {
		em.remove(getGroupById(groupId));
	}

	public List<Group> getAllGroup(String email) {
		TypedQuery<Group> q = em
				.createQuery(
						"select g from groups g where g.admin.emailId=:email order by g.groupCreationDate DESC",
						Group.class);
		q.setParameter("email", email);
		return q.getResultList();
	}

	public List<Group> searchgroup(String groupName) {
		TypedQuery<Group> q = em
				.createQuery(
						"select g from groups g where g.admin.emailId<>:email and g.groupName like :name",
						Group.class);
		q.setParameter("email", hs.getAttribute("email"));
		q.setParameter("name", "%" + groupName + "%");
		return q.getResultList();
	}

	public void sendGroupRequest(int groupid) {
		GroupMemberPK gmpk = new GroupMemberPK();
		GroupMember gm = new GroupMember();
		gmpk.setEmailId(hs.getAttribute("email").toString());
		gmpk.setGroupId(groupid);
		gm.setId(gmpk);
		gm.setGroup(getGroupById(groupid));
		gm.setUser(getUserDetailsById(hs.getAttribute("email").toString()));
		gm.setIsMember(1);
		em.persist(gm);
	}

	public void acceptGroupRequest(GroupMemberPK gmpk) {
		GroupMember gm = getGroupMemberById(gmpk);
		gm.setIsMember(2);
		em.merge(gm);
	}

	public void deleteGroupRequest(GroupMemberPK gmpk) {
		GroupMember gm = getGroupMemberById(gmpk);
		em.remove(gm);
	}

	public GroupMember getGroupMemberById(GroupMemberPK gmpk) {
		return em.find(GroupMember.class, gmpk);
	}

	public List<User> getRequestGroup(int gr) {
		TypedQuery<User> q = em
				.createQuery(
						"select s.user from GroupMember s where s.group.groupId=:grp and s.isMember=1",
						User.class);
		q.setParameter("grp", gr);
		return q.getResultList();
	}

	public List<User> getMemberOfTheGroup(int gr) {
		TypedQuery<User> q = em
				.createQuery(
						"select s.user from GroupMember s where s.group.groupId=:grp and s.isMember=2",
						User.class);
		q.setParameter("grp", gr);
		return q.getResultList();
	}

	public int getGroupMmmberStatus(int groupId) {
		TypedQuery<Integer> q = em
				.createQuery(
						"select s.isMember from GroupMember s where s.group.groupId=:grp and s.user.emailId=:email",
						Integer.class);
		q.setParameter("grp", groupId);
		q.setParameter("email", hs.getAttribute("email"));
		if (q.getResultList().size() > 0) {
			return q.getSingleResult();
		} else {
			return 0;
		}
	}

	public List<Group> getGroupsOfTheMember() {
		TypedQuery<Group> q = em
				.createQuery(
						"select s.group from GroupMember s where s.user.emailId=:email and s.isMember=2",
						Group.class);
		q.setParameter("email", hs.getAttribute("email"));
		return q.getResultList();
	}

	public List<Group> showGrouprequest() {
		TypedQuery<Group> q = em
				.createQuery(
						"select s.group from GroupMember s where s.group.admin.emailId=:emailid and s.isMember=1",
						Group.class);
		q.setParameter("emailid", hs.getAttribute("email"));
		return q.getResultList();
	}
	/***********************for email outbox***********************/
	public void addMailoutbox(MailOutbox mo){
		em.persist(mo);
	}
	
	public MailOutbox getMailOutBoxById(int id){
		return em.find(MailOutbox.class, id);
	}
	public void removeMailFromOutboxById(int id){
		em.remove(getMailOutBoxById(id));
	}
	public List<MailOutbox> getAllMailOutBox(){
		TypedQuery<MailOutbox> q=em.createQuery("select s from MailOutbox s where s.user.emailId=:emailid order by s.sendDate DESC", MailOutbox.class);
		q.setParameter("emailid", hs.getAttribute("email"));
		return q.getResultList();
	}
	/******************for privacy***********************/
	public void setPrivacy(Privacy p){
		em.persist(p);
	}
	public void updatePrivacy(Privacy p){
		em.merge(p);
	}
	public Privacy getOwnPrivacy(){
		return em.find(Privacy.class, hs.getAttribute("email"));
	}
	public Privacy getPrivacyById(String id){
		return em.find(Privacy.class, id);
	}
}