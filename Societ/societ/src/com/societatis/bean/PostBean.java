package com.societatis.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import com.societatis.model.GroupPost;
import com.societatis.model.GroupPostComment;
import com.societatis.model.GroupPostLike;
import com.societatis.model.GroupPostLikePK;
import com.societatis.model.Page;
import com.societatis.model.PageLike;
import com.societatis.model.PageLikePK;
import com.societatis.model.PagePost;
import com.societatis.model.PagePostComment;
import com.societatis.model.PagePostLike;
import com.societatis.model.PagePostLikePK;
import com.societatis.model.UserLikePK;
import com.societatis.model.UserPost;
import com.societatis.model.UserPostComment;
import com.societatis.model.UserPostLike;

@Stateless
public class PostBean {

	@PersistenceContext
	private EntityManager em;
	@Inject
	private HttpSession hs;

	public void setUserPost(UserPost up) {
		em.merge(up);
	}

	public List<UserPost> getAllPosts(String userid) {
		TypedQuery<UserPost> q = em
				.createQuery(
						"select s from UserPost s where s.user.emailId=:uid order by s.userPostDate DESC",
						UserPost.class);
		q.setParameter("uid", userid);
		return q.getResultList();
	}

	public List<UserPost> getAllPosts() {
		TypedQuery<UserPost> q = em
				.createQuery(
						"select s from UserPost s where s.user.emailId=:uid order by s.userPostDate DESC",
						UserPost.class);
		q.setParameter("uid", (String) hs.getAttribute("email"));
		return q.getResultList();
	}

	public UserPost getUserPostById(int id) {
		return em.find(UserPost.class, id);
	}
	
	public void deleteUserPostById(int id){
		em.remove(getUserPostById(id));
	}
	
	public void setUserPostComment(UserPostComment upc) {
		em.persist(upc);
	}

	public List<UserPostComment> getAllUserPostComments(int upid) {
		TypedQuery<UserPostComment> q = em
				.createQuery(
						"select s from UserPostComment s where s.userPost.userPostId=?1 order by s.commentDate DESC",
						UserPostComment.class);
		q.setParameter(1, upid);
		return q.getResultList();
	}

	/********************************* for user like *************************************/
	public UserPostLike getUserPostLikeById(UserLikePK ulpk) {
		return em.find(UserPostLike.class, ulpk);
	}

	public void likeUserPost(UserPostLike upl) {
		em.persist(upl);
	}

	public void unlikeUserPost(UserLikePK ulpk) {
		em.remove(getUserPostLikeById(ulpk));
	}

	public boolean isLiked(int postid) {
		TypedQuery<UserPostLike> q = em
				.createQuery(
						"select l from UserPostLike l where l.userPost.userPostId=:upid and l.user.emailId=:uid",
						UserPostLike.class);
		q.setParameter("upid", postid);
		q.setParameter("uid", hs.getAttribute("email").toString());
		return q.getResultList().size() > 0;
	}

	public int getTotalNumberOfLike(int userPostId) {
		TypedQuery<UserPostLike> q = em
				.createQuery(
						"select l from UserPostLike l where l.userPost.userPostId=:upid",
						UserPostLike.class);
		q.setParameter("upid", userPostId);
		return q.getResultList().size();
	}
	
	public List<String> namesOfTheUser_UserPostLikeBy(int postid){
		TypedQuery<String> q=em.createQuery("select s.user.name from UserPostLike s where s.userPost.userPostId=:postid", String.class);
		q.setParameter("postid", postid);
		return q.getResultList();		
	}

	/****************************************** Page ************************************/
	public Page getPageById(int id) {
		return em.find(Page.class, id);
	}

	public void setPagePost(PagePost pp) {
		em.merge(pp);
	}

	public List<PagePost> getAllPagePosts(int pageid) {
		TypedQuery<PagePost> q = em
				.createQuery(
						"select s from PagePost s where s.page.pageId=:pid order by s.pagePostDate DESC",
						PagePost.class);
		q.setParameter("pid", pageid);
		return q.getResultList();
	}

	public PagePost getPagePostById(int id) {
		return em.find(PagePost.class, id);
	}

	public void deletePagePostById(int id){
		em.remove(getPagePostById(id));
	}
	
	public void setPagePostComment(PagePostComment upc) {
		em.persist(upc);
	}

	public List<PagePostComment> getAllPagePostComments(int ppid) {
		TypedQuery<PagePostComment> q = em
				.createQuery(
						"select s from PagePostComment s where s.pagePost.pagePostId=?1 order by s.commentDate DESC",
						PagePostComment.class);
		q.setParameter(1, ppid);
		return q.getResultList();
	}

	public boolean isPagePostImageContentType(String contentType) {
		return contentType.equals("image/jpeg");
	}

	/********************************* for page post and page post like *************************************/
	public PagePostLike getPagePostLikeById(PagePostLikePK pplpk) {
		return em.find(PagePostLike.class, pplpk);
	}

	public void likePagePost(PagePostLike ppl) {
		em.persist(ppl);
	}

	public void unlikePagePost(PagePostLikePK pplpk) {
		em.remove(getPagePostLikeById(pplpk));
	}

	public boolean isPagePostLiked(int postid) {
		TypedQuery<PagePostLike> q = em
				.createQuery(
						"select l from PagePostLike l where l.pagePost.pagePostId=:upid and l.user.emailId=:uid",
						PagePostLike.class);
		q.setParameter("upid", postid);
		q.setParameter("uid", hs.getAttribute("email").toString());
		return q.getResultList().size() > 0;
	}

	public int getTotalNumberOfPagePostLike(int pagePostId) {
		TypedQuery<PagePostLike> q = em
				.createQuery(
						"select l from PagePostLike l where l.pagePost.pagePostId=:upid",
						PagePostLike.class);
		q.setParameter("upid", pagePostId);
		return q.getResultList().size();
	}
	
	public List<String> nemeOfTheUser_PagePostLikeBy(int pagePostId) {
		TypedQuery<String> q = em.createQuery("select l.user.name from PagePostLike l where l.pagePost.pagePostId=:upid",	String.class);
		q.setParameter("upid", pagePostId);
		return q.getResultList();
	}

	public PageLike getPageLikeById(PageLikePK pagelikePK) {
		return em.find(PageLike.class, pagelikePK);
	}

	public void likePage(PageLike pagelike) {
		em.persist(pagelike);
	}

	public void unlikePage(PageLikePK pagelikePK) {
		em.remove(getPageLikeById(pagelikePK));
	}

	public boolean isPageLiked(int pageid) {
		TypedQuery<PagePostLike> q = em
				.createQuery(
						"select l from PageLike l where l.page.pageId=:pid and l.user.emailId=:uid",
						PagePostLike.class);
		q.setParameter("pid", pageid);
		q.setParameter("uid", hs.getAttribute("email").toString());
		return q.getResultList().size() > 0;
	}

	public int getTotalNumberOfPageLike(int pageId) {
		TypedQuery<PageLike> q = em.createQuery(
				"select l from PageLike l where l.page.pageId=:pageid",
				PageLike.class);
		q.setParameter("pageid", pageId);
		return q.getResultList().size();
	}
	
	public List<String> nameOfTheUser_PageLikeBy(int pageId) {
		TypedQuery<String> q = em.createQuery(
				"select l.user.name from PageLike l where l.page.pageId=:pageid",
				String.class);
		q.setParameter("pageid", pageId);
		return q.getResultList();
	}

	/********************************* for group *************************************/
	public void postOnGroup(GroupPost gp) {
		em.persist(gp);
	}

	public GroupPost getGroupPostById(int id) {
		return em.find(GroupPost.class, id);
	}

	public List<GroupPost> getAllGroupPosts(int groupid) {
		TypedQuery<GroupPost> q = em
				.createQuery(
						"select s from GroupPost s where s.group.groupId=:groupid order by s.groupPostDate DESC",
						GroupPost.class);
		q.setParameter("groupid", groupid);
		return q.getResultList();
	}

	public boolean isGroupPostImageContentType(String contentType) {
		return contentType.equals("image/jpeg");
	}

	public int getTotalNumberOfGroupPostLike(int groupPostId) {
		TypedQuery<GroupPostLike> q = em
				.createQuery(
						"select l from GroupPostLike l where l.groupPost.groupPostId=:gpid",
						GroupPostLike.class);
		q.setParameter("gpid", groupPostId);
		return q.getResultList().size();
	}
	
	public List<String> nameofTheUser_GroupPostLikeBy(int groupPostId) {
		TypedQuery<String> q = em
				.createQuery(
						"select l.user.name from GroupPostLike l where l.groupPost.groupPostId=:gpid",
						String.class);
		q.setParameter("gpid", groupPostId);
		return q.getResultList();
	}

	public boolean isGroupPostLiked(int postid) {
		TypedQuery<PagePostLike> q = em
				.createQuery(
						"select l from GroupPostLike l where l.groupPost.groupPostId=:gpid and l.user.emailId=:uid",
						PagePostLike.class);
		q.setParameter("gpid", postid);
		q.setParameter("uid", hs.getAttribute("email").toString());
		return q.getResultList().size() > 0;
	}

	public GroupPostLike getGroupPostLikeById(GroupPostLikePK gplpk) {
		return em.find(GroupPostLike.class, gplpk);
	}

	public void likeGroupPost(GroupPostLike gpl) {
		em.persist(gpl);
	}

	public void unlikeGroupPost(GroupPostLikePK gplpk) {
		em.remove(getGroupPostLikeById(gplpk));
	}

	public void setGroupPostComment(GroupPostComment gpc) {
		em.persist(gpc);
	}

	public List<GroupPostComment> getAllGroupPostComments(int gpid) {
		TypedQuery<GroupPostComment> q = em
				.createQuery(
						"select s from GroupPostComment s where s.groupPost.groupPostId=?1 order by s.commentDate DESC",
						GroupPostComment.class);
		q.setParameter(1, gpid);
		return q.getResultList();
	}
}
