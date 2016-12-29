package com.societatis.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.societatis.util.Base64;

@Entity
public class GroupPost {
	@Id
	@GeneratedValue
	private int groupPostId;

	private String groupPostType;
	@Lob
	private byte[] groupPostData;
	@Temporal(TemporalType.TIMESTAMP)
	private Date groupPostDate;

	@ManyToOne
	@JoinColumn(name = "emailId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "groupId")
	private Group group;

	@OneToMany(mappedBy = "groupPost", cascade = CascadeType.ALL)
	private List<GroupPostLike> groupPostLikes;

	@OneToMany(mappedBy = "groupPost", cascade = CascadeType.ALL)
	private List<GroupPostComment> groupPostComments;
	
	public String getGroupPostText(){
		return new String(groupPostData);
	}
	
	public String getGroupPostImage(){
		if(groupPostData==null)
		{
			return "";
		}
		return Base64.encodeBytes(groupPostData);
	}

	public int getGroupPostId() {
		return groupPostId;
	}

	public void setGroupPostId(int groupPostId) {
		this.groupPostId = groupPostId;
	}

	public String getGroupPostType() {
		return groupPostType;
	}

	public void setGroupPostType(String groupPostType) {
		this.groupPostType = groupPostType;
	}

	public Date getGroupPostDate() {
		return groupPostDate;
	}

	public void setGroupPostDate(Date groupPostDate) {
		this.groupPostDate = groupPostDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<GroupPostLike> getGroupPostLikes() {
		return groupPostLikes;
	}

	public void setGroupPostLikes(List<GroupPostLike> groupPostLikes) {
		this.groupPostLikes = groupPostLikes;
	}

	public List<GroupPostComment> getGroupPostComments() {
		return groupPostComments;
	}

	public void setGroupPostComments(List<GroupPostComment> groupPostComments) {
		this.groupPostComments = groupPostComments;
	}

	public byte[] getGroupPostData() {
		return groupPostData;
	}

	public void setGroupPostData(byte[] groupPostData) {
		this.groupPostData = groupPostData;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
