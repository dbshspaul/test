package com.societatis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PageLikePK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(insertable = false, updatable = false)
	private String emailId;
	@Column(insertable = false, updatable = false)
	private int pageId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PageLikePK)) {
			return false;
		}
		PageLikePK castOther = (PageLikePK) obj;
		return (this.getPageId() == castOther.getPageId())
				&& this.emailId.equals(castOther.emailId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.getPageId() ^ (this.getPageId() >>> 32)));
		hash = hash * prime + this.emailId.hashCode();

		return hash;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
}
