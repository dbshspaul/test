package com.societatis.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GroupPostLike {

	@EmbeddedId
	private GroupPostLikePK id;

	@ManyToOne
	@JoinColumn(name = "emailId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "groupPostId")
	private GroupPost groupPost;

	private int isLike;

	public GroupPostLikePK getId() {
		return id;
	}

	public void setId(GroupPostLikePK id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GroupPost getGroupPost() {
		return groupPost;
	}

	public void setGroupPost(GroupPost groupPost) {
		this.groupPost = groupPost;
	}

	public int getIsLike() {
		return isLike;
	}

	public void setIsLike(int isLike) {
		this.isLike = isLike;
	}


}
