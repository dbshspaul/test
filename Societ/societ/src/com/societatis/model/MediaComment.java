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
public class MediaComment {
	@Id
	@GeneratedValue
	private int mediaCommentId;
	private String comments;
	@Temporal(TemporalType.TIMESTAMP)
	private Date commentDate;

	@ManyToOne
	@JoinColumn(name = "emailId")
	private User user;
	@ManyToOne
	@JoinColumn(name = "mediaId")
	private Media media;

	public int getMediaCommentId() {
		return mediaCommentId;
	}

	public void setMediaCommentId(int mediaCommentId) {
		this.mediaCommentId = mediaCommentId;
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

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
