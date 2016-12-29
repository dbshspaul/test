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
public class Page {

	@Id
	@GeneratedValue
	private int pageId;
	private String pageName;
	private String pageType;
	private String pageDesc;
	@Temporal(TemporalType.DATE)
	private Date pagecreationDate;
	@Lob
	private byte[] pagePicture;

	@ManyToOne
	@JoinColumn(name = "emailId")
	private User admin;

	@OneToMany(mappedBy = "page",cascade=CascadeType.ALL)
	List<PagePost> pageposts;

	@OneToMany(mappedBy = "page",cascade=CascadeType.ALL)
	List<PageLike> pageLikes;
	
	public String getImageAsString(){
		if(pagePicture==null)
		{
			return "";
		}
		return Base64.encodeBytes(pagePicture);
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getPageDesc() {
		return pageDesc;
	}

	public void setPageDesc(String pageDesc) {
		this.pageDesc = pageDesc;
	}

	public Date getPagecreationDate() {
		return pagecreationDate;
	}

	public void setPagecreationDate(Date pagecreationDate) {
		this.pagecreationDate = pagecreationDate;
	}

	public byte[] getPagePicture() {
		return pagePicture;
	}

	public void setPagePicture(byte[] pagePicture) {
		this.pagePicture = pagePicture;
	}

	public List<PagePost> getPageposts() {
		return pageposts;
	}

	public void setPageposts(List<PagePost> pageposts) {
		this.pageposts = pageposts;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public List<PageLike> getPageLikes() {
		return pageLikes;
	}

	public void setPageLikes(List<PageLike> pageLikes) {
		this.pageLikes = pageLikes;
	}

}
