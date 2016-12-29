package com.societatis.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import com.societatis.model.Friend;
import com.societatis.model.User;

@Stateless
public class FriendBean {

	@PersistenceContext
	EntityManager em;
	@Inject
	HttpSession hs;
	@EJB
	private UserBean ubean;

	/******************************************* functions ********************************************/
	public Friend getFriendById(String id) {
		return em.find(Friend.class, id);
	}

	public List<User> searchFriends(String name) {
		ubean.setEmail((String) hs.getAttribute("email"));
		TypedQuery<User> q = em
				.createQuery(
						"select u from User u where UPPER(u.name) like :name and u<>:user",
						User.class);
		q.setParameter("name", "%" + name.toUpperCase() + "%");
		q.setParameter("user", ubean.getUserDetails());
		List<User> searchRes = q.getResultList();

		List<User> frndList = getFriendslist();
		List<Friend> reqFrndList = getSendFriendsRequestlist();
		for (User u : searchRes) {
			if (frndList.contains(u) || getFriendsRequest().contains(u)) {
				searchRes.remove(u);
			}
		}
		for (Friend f : reqFrndList) {
			if (searchRes.contains(ubean.getUserDetailsById(f.getFriendId()))) {
				searchRes.remove(ubean.getUserDetailsById(f.getFriendId()));
			}
		}
		return searchRes;
	}

	public void sendFriendRequest(String frndid) {
		Friend fr = new Friend();
		fr.setUser(ubean.getUserDetailsById((String) hs.getAttribute("email")));
		fr.setFriendId(frndid);
		fr.setStatus(1);
		em.persist(fr);
	}

	public List<User> getFriendsRequest() {
		TypedQuery<User> q = em
				.createQuery(
						"select f.user from Friend f where f.friendId=:friendId and f.status=1",
						User.class);
		q.setParameter("friendId", (String) hs.getAttribute("email"));
		return q.getResultList();
	}

	public List<Friend> getSendFriendsRequestlist() {
		TypedQuery<Friend> q = em.createQuery(
				"select f from Friend f where f.user=:usr and f.status=1",
				Friend.class);
		q.setParameter("usr",
				ubean.getUserDetailsById((String) hs.getAttribute("email")));
		return q.getResultList();
	}

	public void acceptFriendRequest(String frndid) {
		TypedQuery<Friend> q1 = em
				.createQuery(
						"select fr from Friend fr where fr.user=?1 and fr.friendId=?2 and fr.status=1",
						Friend.class);
		q1.setParameter(1, ubean.getUserDetailsById(frndid));
		q1.setParameter(2, ((String) hs.getAttribute("email")));
		Friend fr = q1.getSingleResult();
		fr.setStatus(2);
		em.merge(fr);

		Friend rf = new Friend();
		rf.setUser(ubean.getUserDetailsById((String) hs.getAttribute("email")));
		rf.setFriendId(frndid);
		rf.setStatus(2);
		em.persist(rf);
	}

	public void rejectFriendRequest(String frndid) {
		Query q = em
				.createQuery("delete from Friend fr where fr.user=?1 and fr.friendId=?2 and fr.status=1");
		q.setParameter(1, ubean.getUserDetailsById(frndid));
		q.setParameter(2, ((String) hs.getAttribute("email")));
		q.executeUpdate();
	}

	public List<User> getFriendslist() {
		TypedQuery<User> q = em
				.createQuery(
						"select f.user from Friend f where f.friendId=?1 and f.status=2",
						User.class);
		q.setParameter(1, (String) hs.getAttribute("email"));
		return q.getResultList();
	}
	
	public void deleteFriendEntry(String id){
		Query q=em.createQuery("delete from Friend fr where fr.friendId=:id");
		q.setParameter("id", id);
		q.executeUpdate();
	}

	public void removeFriend(String frndid) {
		Query q1 = em
				.createQuery("delete from Friend fr where fr.user=?1 and fr.friendId=?2 and fr.status=2");
		q1.setParameter(1, ubean.getUserDetailsById(frndid));
		q1.setParameter(2, ((String) hs.getAttribute("email")));
		q1.executeUpdate();

		Query q2 = em
				.createQuery("delete from Friend fr where fr.user=?1 and fr.friendId=?2 and fr.status=2");
		q2.setParameter(1,
				ubean.getUserDetailsById((String) hs.getAttribute("email")));
		q2.setParameter(2, frndid);
		q2.executeUpdate();
	}
}
