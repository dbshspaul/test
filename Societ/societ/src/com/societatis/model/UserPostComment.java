package com.societatis.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UserPostComment {
	@Id@GeneratedValue
	private int userPostCommentId;
	private String userPostComment;
	@Temporal(TemporalType.TIMESTAMP)
	private Date commentDate;

	@ManyToOne
	@JoinColumn(name = "emailId")
	private User user;
	@ManyToOne
	@JoinColumn(name = "userPostId")
	private UserPost userPost;

	public int getUserPostCommentId() {
		return userPostCommentId;
	}

	public void setUserPostCommentId(int userPostCommentId) {
		this.userPostCommentId = userPostCommentId;
	}

	public String getUserPostComment() {
		return userPostComment;
	}

	public void setUserPostComment(String userPostComment) {
		this.userPostComment = userPostComment;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
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

}
