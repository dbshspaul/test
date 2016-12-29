package com.societatis.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PagePostLike {

	@EmbeddedId
	PagePostLikePK id;

	@ManyToOne
	@JoinColumn(name = "pagePostid")
	private PagePost pagePost;

	@ManyToOne
	@JoinColumn(name = "emailId")
	private User user;

	private int isLike;

	public int getIsLike() {
		return isLike;
	}

	public void setIsLike(int isLike) {
		this.isLike = isLike;
	}

	public PagePostLikePK getId() {
		return id;
	}

	public void setId(PagePostLikePK id) {
		this.id = id;
	}

	public PagePost getPagePost() {
		return pagePost;
	}

	public void setPagePost(PagePost pagePost) {
		this.pagePost = pagePost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
