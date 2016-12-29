package com.societatis.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MediaLike {

	@EmbeddedId
	private MediaLikePK id;

	@ManyToOne
	@JoinColumn(name = "emailId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "mediaId")
	private Media media;

	public MediaLikePK getId() {
		return id;
	}

	public void setId(MediaLikePK id) {
		this.id = id;
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

}
