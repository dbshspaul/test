package com.societatis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MediaLikePK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(insertable = false, updatable = false)
	private String emailId;
	@Column(insertable = false, updatable = false)
	private int mediaId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getMediaId() {
		return mediaId;
	}

	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MediaLikePK)) {
			return false;
		}
		MediaLikePK castOther = (MediaLikePK) obj;
		return (this.getMediaId() == castOther.getMediaId())
				&& this.emailId.equals(castOther.emailId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.getMediaId() ^ (this.getMediaId() >>> 32)));
		hash = hash * prime + this.emailId.hashCode();

		return hash;
	}
}
