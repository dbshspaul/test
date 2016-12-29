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
public class Media {
	@Id  @GeneratedValue
	private int mediaId;
	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadDate;
	private String fileExt;
	private String contentType;
	
	@Lob
	private byte[] fileContent;

	@ManyToOne
	@JoinColumn(name="albumId")
	private Album album;
	
	@OneToMany(mappedBy="media",cascade=CascadeType.ALL)
	private List<MediaLike> mediaLikes; 
	
	@OneToMany(mappedBy="media",cascade=CascadeType.ALL)
	private List<MediaComment> mediaComments;
	
	public String getMediaAsString(){
		if(fileContent==null)
		{
			return "";
		}
		return Base64.encodeBytes(fileContent);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getMediaId() {
		return mediaId;
	}

	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}

	public List<MediaComment> getMediaComments() {
		return mediaComments;
	}

	public void setMediaComments(List<MediaComment> mediaComments) {
		this.mediaComments = mediaComments;
	}
	
	
	
}
