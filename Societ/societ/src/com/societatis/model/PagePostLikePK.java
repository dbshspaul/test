package com.societatis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PagePostLikePK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(insertable = false, updatable = false)
	private String emailId;
	@Column(insertable = false, updatable = false)
	private int pagePostId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getPagePostId() {
		return pagePostId;
	}

	public void setPagePostId(int pageId) {
		this.pagePostId = pageId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PagePostLikePK)) {
			return false;
		}
		PagePostLikePK castOther = (PagePostLikePK) obj;
		return (this.pagePostId == castOther.pagePostId)
				&& this.emailId.equals(castOther.emailId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.pagePostId ^ (this.pagePostId >>> 32)));
		hash = hash * prime + this.emailId.hashCode();

		return hash;
	}
}
