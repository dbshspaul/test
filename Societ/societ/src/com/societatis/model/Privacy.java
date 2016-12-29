package com.societatis.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Privacy {
	@Id
	@OneToOne@JoinColumn(name="emailId")
	private User user;

	private int contactNo;
	private int address;
	private int languagesKnown;
	private int education;
	private int hobbies;
	private int aboutUser;
	private int state;
	private int country;
	private int motherTongue;
	private int dob;

	public int getContactNo() {
		return contactNo;
	}

	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public int getLanguagesKnown() {
		return languagesKnown;
	}

	public void setLanguagesKnown(int languagesKnown) {
		this.languagesKnown = languagesKnown;
	}

	public int getEducation() {
		return education;
	}

	public void setEducation(int education) {
		this.education = education;
	}

	public int getHobbies() {
		return hobbies;
	}

	public void setHobbies(int hobbies) {
		this.hobbies = hobbies;
	}

	public int getAboutUser() {
		return aboutUser;
	}

	public void setAboutUser(int aboutUser) {
		this.aboutUser = aboutUser;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public int getMotherTongue() {
		return motherTongue;
	}

	public void setMotherTongue(int motherTongue) {
		this.motherTongue = motherTongue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getDob() {
		return dob;
	}

	public void setDob(int dob) {
		this.dob = dob;
	}

}
