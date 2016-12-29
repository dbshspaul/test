package com.societatis.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserPostLike {

	@EmbeddedId
	private UserLikePK id;

	@ManyToOne
	@JoinColumn(name = "emailId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "userPostId")
	private UserPost userPost;

	private int isLike;

	public UserLikePK getId() {
		return id;
	}

	public void setId(UserLikePK id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserPost getUserPost() {
		return userPost;
	}

	public void setUserPost(UserPost userPost) {
		this.userPost = userPost;
	}

	public int getIsLike() {
		return isLike;
	}

	public void setIsLike(int isLike) {
		this.isLike = isLike;
	}

}
