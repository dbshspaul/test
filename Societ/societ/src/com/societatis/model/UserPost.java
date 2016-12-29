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
public class UserPost {

	@Id
	@GeneratedValue
	private int userPostId;

	private String userPostType;
	@Lob
	private byte[] userPostData;
	@Temporal(TemporalType.TIMESTAMP)
	private Date userPostDate;

	@ManyToOne
	@JoinColumn(name = "emailId")
	private User user;

	@OneToMany(mappedBy = "userPost",cascade=CascadeType.ALL)
	private List<UserPostLike> userPosts;
	
	@OneToMany(mappedBy = "userPost",cascade=CascadeType.ALL)
	private List<UserPostComment> userPostComments;
	
	public String getTextPost(){
		return new String(userPostData);
	}
	
	public String getImageAsString(){
		if(userPostData==null)
		{
			return "";
		}
		return Base64.encodeBytes(userPostData);
	}

	public int getUserPostId() {
		return userPostId;
	}

	public void setUserPostId(int userPostId) {
		this.userPostId = userPostId;
	}

	public String getUserPostType() {
		return userPostType;
	}

	public void setUserPostType(String userPostType) {
		this.userPostType = userPostType;
	}

	public Date getUserPostDate() {
		return userPostDate;
	}

	public void setUserPostDate(Date userPostDate) {
		this.userPostDate = userPostDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<UserPostLike> getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(List<UserPostLike> userPosts) {
		this.userPosts = userPosts;
	}

	public byte[] getUserPostData() {
		return userPostData;
	}

	public void setUserPostData(byte[] userPostData) {
		this.userPostData = userPostData;
	}

	public List<UserPostComment> getUserPostComments() {
		return userPostComments;
	}

	public void setUserPostComments(List<UserPostComment> userPostComments) {
		this.userPostComments = userPostComments;
	}
}
