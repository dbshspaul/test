package com.societatis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GroupPostLikePK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(insertable = false, updatable = false)
	private String emailId;
	@Column(insertable = false, updatable = false)
	private int groupPostId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getGroupPostId() {
		return groupPostId;
	}

	public void setGroupPostId(int groupPostId) {
		this.groupPostId = groupPostId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GroupPostLikePK)) {
			return false;
		}
		GroupPostLikePK castOther = (GroupPostLikePK) obj;
		return (this.groupPostId == castOther.groupPostId)
				&& this.emailId.equals(castOther.emailId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime
				+ ((int) (this.groupPostId ^ (this.groupPostId >>> 32)));
		hash = hash * prime + this.emailId.hashCode();

		return hash;
	}
}
