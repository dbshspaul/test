package com.societatis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GroupMemberPK implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(insertable = false, updatable = false)
	private String emailId;
	@Column(insertable = false, updatable = false)
	private int groupId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GroupMemberPK)) {
			return false;
		}
		GroupMemberPK castOther = (GroupMemberPK) obj;
		return (this.groupId == castOther.groupId)
				&& this.emailId.equals(castOther.emailId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.groupId ^ (this.groupId >>> 32)));
		hash = hash * prime + this.emailId.hashCode();

		return hash;
	}
}
