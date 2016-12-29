package com.societatis.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PageLike {

	@EmbeddedId
	private PageLikePK id;

	@ManyToOne
	@JoinColumn(name = "emailId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "pageId")
	private Page page;

	private int isLiked;

	public PageLikePK getId() {
		return id;
	}

	public void setId(PageLikePK id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public int getIsLiked() {
		return isLiked;
	}

	public void setIsLiked(int isLiked) {
		this.isLiked = isLiked;
	}
}
