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
public class GroupPostComment {
	@Id@GeneratedValue
	private int groupPostCommentId;
	private String groupPostComment;
	@Temporal(TemporalType.TIMESTAMP)
	private Date commentDate;

	@ManyToOne
	@JoinColumn(name = "emailId")
	private User user;
	@ManyToOne
	@JoinColumn(name = "groupPostId")
	private GroupPost groupPost;

	public int getGroupPostCommentId() {
		return groupPostCommentId;
	}

	public void setGroupPostCommentId(int groupPostCommentId) {
		this.groupPostCommentId = groupPostCommentId;
	}

	public String getGroupPostComment() {
		return groupPostComment;
	}

	public void setGroupPostComment(String groupPostComment) {
		this.groupPostComment = groupPostComment;
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

	public GroupPost getGroupPost() {
		return groupPost;
	}

	public void setGroupPost(GroupPost groupPost) {
		this.groupPost = groupPost;
	}
}
