package com.societatis.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Users")
public class User {

	@Id
	private String emailId;
	private String password;
	private String name;
	private String gender;

	private String state;
	private String country;
	private String motherTongue;

	@Temporal(TemporalType.DATE)
	private Date dob;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<Album> albums;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<Note> notes;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<Friend> friends;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserProfile userProfile;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<UserPost> userPosts;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<UserPostLike> userPostsLikes;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<UserPostComment> userPostscomments;

	@OneToMany(mappedBy = "admin")
	private List<Page> pages;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<PageLike> pageLikes;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<PagePost> pagePosts;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<PagePostLike> pagePostLikes;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<PagePostComment> pagePostsComments;

	@OneToMany(mappedBy = "admin",cascade=CascadeType.ALL)
	private List<Group> groups;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<GroupPost> groupPosts;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<GroupPostComment> groupPostsComments;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<GroupMember> groupMembers;

	@OneToOne(mappedBy = "sender",cascade=CascadeType.ALL)
	private Message msg;
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<MediaLike> mediaLikes;
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<MediaComment> mediaComments;
	
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL)
	private Privacy privacy;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<MailOutbox> mails;

	@Transient
	private boolean isFriend;

	/******************** getters and Setters ****************************/

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMotherTongue() {
		return motherTongue;
	}

	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public boolean isFriend() {
		return isFriend;
	}

	public void setFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public List<PagePost> getPagePosts() {
		return pagePosts;
	}

	public void setPagePosts(List<PagePost> pagePosts) {
		this.pagePosts = pagePosts;
	}

	public List<UserPost> getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(List<UserPost> userPosts) {
		this.userPosts = userPosts;
	}

	public List<GroupPost> getGroupPosts() {
		return groupPosts;
	}

	public void setGroupPosts(List<GroupPost> groupPosts) {
		this.groupPosts = groupPosts;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<GroupMember> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(List<GroupMember> groupMembers) {
		this.groupMembers = groupMembers;
	}

	public List<PageLike> getPageLikes() {
		return pageLikes;
	}

	public void setPageLikes(List<PageLike> pageLikes) {
		this.pageLikes = pageLikes;
	}

	public List<PagePostLike> getPagePostLikes() {
		return pagePostLikes;
	}

	public void setPagePostLikes(List<PagePostLike> pagePostLikes) {
		this.pagePostLikes = pagePostLikes;
	}

	public List<UserPostLike> getUserPostsLikes() {
		return userPostsLikes;
	}

	public void setUserPostsLikes(List<UserPostLike> userPostsLikes) {
		this.userPostsLikes = userPostsLikes;
	}

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

	public List<UserPostComment> getUserPostscomments() {
		return userPostscomments;
	}

	public void setUserPostscomments(List<UserPostComment> userPostscomments) {
		this.userPostscomments = userPostscomments;
	}

	public List<PagePostComment> getPagePostsComments() {
		return pagePostsComments;
	}

	public void setPagePostsComments(List<PagePostComment> pagePostsComments) {
		this.pagePostsComments = pagePostsComments;
	}

	public List<GroupPostComment> getGroupPostsComments() {
		return groupPostsComments;
	}

	public void setGroupPostsComments(List<GroupPostComment> groupPostsComments) {
		this.groupPostsComments = groupPostsComments;
	}

	public List<MediaComment> getMediaComments() {
		return mediaComments;
	}

	public void setMediaComments(List<MediaComment> mediaComments) {
		this.mediaComments = mediaComments;
	}

	public List<MediaLike> getMediaLikes() {
		return mediaLikes;
	}

	public void setMediaLikes(List<MediaLike> mediaLikes) {
		this.mediaLikes = mediaLikes;
	}

	public Privacy getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Privacy privacy) {
		this.privacy = privacy;
	}

	public List<MailOutbox> getMails() {
		return mails;
	}

	public void setMails(List<MailOutbox> mails) {
		this.mails = mails;
	}
}
