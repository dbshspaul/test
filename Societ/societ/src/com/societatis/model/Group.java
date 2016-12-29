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

@Entity(name = "groups")
public class Group {

	@Id
	@GeneratedValue
	private int groupId;
	private String groupName;
	private String groupType;
	private String groupDesc;
	@Temporal(TemporalType.TIMESTAMP)
	private Date groupCreationDate;
	@Lob
	private byte[] groupPicture;

	@ManyToOne
	@JoinColumn(name = "emailId")
	private User admin;

	@OneToMany(mappedBy = "group",cascade=CascadeType.ALL)
	private List<GroupMember> groupMembers;

	@OneToMany(mappedBy = "group",cascade=CascadeType.ALL)
	private List<GroupPost> groupPosts;
	
	public String getImageAsString(){
		if(groupPicture==null)
		{
			return "";
		}
		return Base64.encodeBytes(groupPicture);
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public Date getGroupCreationDate() {
		return groupCreationDate;
	}

	public void setGroupCreationDate(Date groupCreationDate) {
		this.groupCreationDate = groupCreationDate;
	}

	public byte[] getGroupPicture() {
		return groupPicture;
	}

	public void setGroupPicture(byte[] groupPicture) {
		this.groupPicture = groupPicture;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public List<GroupMember> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(List<GroupMember> groupMembers) {
		this.groupMembers = groupMembers;
	}

	public List<GroupPost> getGroupPosts() {
		return groupPosts;
	}

	public void setGroupPosts(List<GroupPost> groupPosts) {
		this.groupPosts = groupPosts;
	}

}
