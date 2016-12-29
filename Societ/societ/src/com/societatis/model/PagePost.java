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
public class PagePost {

	@Id
	@GeneratedValue
	private int pagePostId;

	private String pagePostType;
	@Lob
	private byte[] pagePostData;
	@Temporal(TemporalType.TIMESTAMP)
	private Date pagePostDate;

	@ManyToOne
	@JoinColumn(name = "pageId")
	private Page page;

	@ManyToOne
	@JoinColumn(name = "emailId")
	private User user;

	@OneToMany(mappedBy = "pagePost", cascade=CascadeType.ALL)
	private List<PagePostLike> pagePostLikes;

	@OneToMany(mappedBy = "pagePost", cascade=CascadeType.ALL)
	private List<PagePostComment> pagePostcomments;
	
	public String getPagePostText(){
		return new String(pagePostData);
	}
	
	public String getImageAsString(){
		if(pagePostData==null)
		{
			return "";
		}
		return Base64.encodeBytes(pagePostData);
	}

	public List<PagePostLike> getPagePostLikes() {
		return pagePostLikes;
	}

	public void setPagePostLikes(List<PagePostLike> pagePostLikes) {
		this.pagePostLikes = pagePostLikes;
	}

	public int getPagePostId() {
		return pagePostId;
	}

	public void setPagePostId(int pagePostId) {
		this.pagePostId = pagePostId;
	}

	public String getPagePostType() {
		return pagePostType;
	}

	public void setPagePostType(String pagePostType) {
		this.pagePostType = pagePostType;
	}

	public Date getPagePostDate() {
		return pagePostDate;
	}

	public void setPagePostDate(Date pagePostDate) {
		this.pagePostDate = pagePostDate;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<PagePostComment> getPagePostcomments() {
		return pagePostcomments;
	}

	public void setPagePostcomments(List<PagePostComment> pagePostcomments) {
		this.pagePostcomments = pagePostcomments;
	}

	public byte[] getPagePostData() {
		return pagePostData;
	}

	public void setPagePostData(byte[] pagePostData) {
		this.pagePostData = pagePostData;
	}

}
