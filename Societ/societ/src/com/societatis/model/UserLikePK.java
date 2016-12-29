package com.societatis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserLikePK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(updatable = false, insertable = false)
	private String emailId;
	@Column(updatable = false, insertable = false)
	private int userPostId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getUserPostId() {
		return userPostId;
	}

	public void setUserPostId(int userPostId) {
		this.userPostId = userPostId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserLikePK)) {
			return false;
		}
		UserLikePK castOther = (UserLikePK) obj;
		return (this.userPostId == castOther.userPostId)
				&& this.emailId.equals(castOther.emailId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime
				+ ((int) (this.userPostId ^ (this.userPostId >>> 32)));
		hash = hash * prime + this.emailId.hashCode();

		return hash;
	}
}
