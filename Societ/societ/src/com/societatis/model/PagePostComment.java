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
public class PagePostComment {
	@Id@GeneratedValue
	private int pagePostCommentId;
	private String pagePostComment;
	@Temporal(TemporalType.TIMESTAMP)
	private Date commentDate;

	@ManyToOne
	@JoinColumn(name = "emailId")
	private User user;
	@ManyToOne
	@JoinColumn(name = "pagePostId")
	private PagePost pagePost;

	public int getPagePostCommentId() {
		return pagePostCommentId;
	}

	public void setPagePostCommentId(int pagePostCommentId) {
		this.pagePostCommentId = pagePostCommentId;
	}

	public String getPagePostComment() {
		return pagePostComment;
	}

	public void setPagePostComment(String pagePostComment) {
		this.pagePostComment = pagePostComment;
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

	public PagePost getPagePost() {
		return pagePost;
	}

	public void setPagePost(PagePost pagePost) {
		this.pagePost = pagePost;
	}

}
