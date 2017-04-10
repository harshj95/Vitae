package com.application.job.model.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import com.application.job.util.Constants;

@Entity(value = "T_Users", noClassnameStored = true)
public class User extends BaseEntity{	
	
	@Property("c_user_name")
	private String userName;
	@Property("c_email")
	private String email;
	@Property("c_password")
	private String password;
	@Property("c_phone")
	private String phone;
	private int verified;
	private int emailVerified;
	private int installed;
	@Embedded
	private List<Session> sessions;
	@Embedded 
	private List<Skill> skills;
	
	public User() {
		super();
	}

	public User(String userName, String email, String password, String phone, int verified, int emailVerified,
			int installed, List<Session> sessions, List<Skill> skills) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.verified = verified;
		this.emailVerified = emailVerified;
		this.installed = installed;
		this.sessions = sessions;
		this.skills = skills;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public void setVerified(int verified) {
		this.verified = verified;
	}

	public int getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(int emailVerified) {
		this.emailVerified = emailVerified;
	}

	public int getInstalled() {
		return installed;
	}

	public void setInstalled(int installed) {
		this.installed = installed;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", email=" + email + ", password=" + password + ", phone=" + phone
				+ ", verified=" + verified + ", emailVerified=" + emailVerified + ", installed=" + installed
				+ ", sessions=" + sessions + ", skills=" + skills + ", id=" + id + ", creationDate=" + creationDate
				+ ", lastChange=" + lastChange + ", status=" + status + "]";
	}
}