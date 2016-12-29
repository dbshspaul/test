package com.societatis.model;

import java.io.Serializable;

public class UserFriendPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String user;
	private String friendId;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	@Override
	public int hashCode() {
		return getASCIITotal(user) + getASCIITotal(friendId);
	}

	private static int getASCIITotal(String s) {
		int c = 0;
		for (char ch : s.toCharArray()) {
			c += (int) ch;
		}
		return c;
	}

	@Override
	public boolean equals(Object obj) {
		UserFriendPK ufp = (UserFriendPK) obj;
		return this.hashCode() - ufp.hashCode() == 0;
	}
}
