package com.application.job.model;

import java.io.Serializable;

public class User {	
	
	private int userId;
	private String email;
	private String userName;
	private String phone;
	private int verified;
	private int emailVerified;
	private int active;
	private int installed;
	private long created;
	private long modified;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getVerified() {
		return verified;
	}
	public void setVerified(int isVerified) {
		this.verified = isVerified;
	}
	public int getEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(int emailVerified) {
		this.emailVerified = emailVerified;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int isActive) {
		this.active = isActive;
	}
	public int getInstalled() {
		return installed;
	}
	public void setInstalled(int installed) {
		this.installed = installed;
	}
	public long getCreated() {
		return created;
	}
	public void setCreated(long created) {
		this.created = created;
	}
	public long getModified() {
		return modified;
	}
	public void setModified(long modified) {
		this.modified = modified;
	}

}
