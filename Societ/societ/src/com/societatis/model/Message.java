package com.societatis.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Message {

	@Id@GeneratedValue
	private int messageId;
	@OneToOne@JoinColumn(name="emailId")
	private User sender;
	
	private String recipientId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date messageSendDate;
	
	private String message;
	
	private int readStatus;
	
	public String getMessageHead(){
		String[] str= null;
		str= message.split(" ");
		if(str.length>2){
			return str[0]+" "+str[1]+" "+str[2];
		}else if(str.length>1){
			return str[0]+" "+str[1];
		}else if(str.length>0){
			return str[0];
		}else return "";
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public Date getMessageSendDate() {
		return messageSendDate;
	}

	public void setMessageSendDate(Date messageSendDate) {
		this.messageSendDate = messageSendDate;
	}

	public int getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
