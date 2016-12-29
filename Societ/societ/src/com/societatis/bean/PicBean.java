package com.societatis.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import com.societatis.model.Album;
import com.societatis.model.Media;
import com.societatis.model.MediaComment;
import com.societatis.model.MediaLike;
import com.societatis.model.MediaLikePK;

@Stateless
public class PicBean {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private UserBean ubean;
	@Inject
	HttpSession hs;

	public void createAlbum(Album albm) {
		em.persist(albm);
	}

	public void addPicture(Media pic) {
		em.persist(pic);
	}

	public Album getAlbumbyId(int id) {
		return em.find(Album.class, id);
	}

	public Media getMediaById(int picId) {
		return em.find(Media.class, picId);
	}

	public void deleteAlbumById(int id) {
		em.remove(getAlbumbyId(id));
	}

	public void deletePictureById(int id) {
		em.remove(getMediaById(id));
	}

	public List<Media> getAllPictures(int albumId) {
		TypedQuery<Media> q = em
				.createQuery(
						"select m from Media m where m.album.albumId=:id order by m.uploadDate DESC",
						Media.class);
		q.setParameter("id", albumId);
		return q.getResultList();
	}

	public List<Album> getAllAlbums() {
		TypedQuery<Album> q = em.createQuery(
				"select a from Album a where a.user.emailId=:email",
				Album.class);
		q.setParameter("email", hs.getAttribute("email"));
		return q.getResultList();
	}
	
	public List<Album> getAllAlbumsByUserId(String id) {
		TypedQuery<Album> q = em.createQuery(
				"select a from Album a where a.user.emailId=:email",
				Album.class);
		q.setParameter("email", id);
		return q.getResultList();
	}

	public int getTotalNumberOfLikes(int mediaId) {
		TypedQuery<MediaLike> q = em.createQuery(
				"select ml from MediaLike ml where ml.media.mediaId=:id",
				MediaLike.class);
		q.setParameter("id", mediaId);
		return q.getResultList().size();
	}
	
	public List<String> namesOfTheUser_MediaLikeBy(int mediaId) {
		TypedQuery<String> q = em.createQuery(
				"select ml.user.name from MediaLike ml where ml.media.mediaId=:id",
				String.class);
		q.setParameter("id", mediaId);
		return q.getResultList();
	}

	public boolean isLiked(int mediaid) {
		TypedQuery<MediaLike> q = em
				.createQuery(
						"select ml from MediaLike ml where ml.media.mediaId=:mid and ml.user.emailId=:uid",
						MediaLike.class);
		q.setParameter("mid", mediaid);
		q.setParameter("uid", hs.getAttribute("email").toString());
		return q.getResultList().size() > 0;
	}

	public MediaLike getMediaLikeById(MediaLikePK mlpk) {
		return em.find(MediaLike.class, mlpk);
	}

	public void like(MediaLike ml) {
		em.persist(ml);
	}

	public void unlike(MediaLikePK mlpk) {
		em.remove(getMediaLikeById(mlpk));
	}

	public void commentOnMedia(MediaComment mc) {
		em.persist(mc);
	}

	public List<MediaComment> getAllComments(int mediaid) {
		TypedQuery<MediaComment> q = em
				.createQuery(
						"select mc from MediaComment mc where mc.media.mediaId=:id order by mc.commentDate DESC",
						MediaComment.class);
		q.setParameter("id", mediaid);
		return q.getResultList();
	}
}
