package com.application.job.model.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import com.application.job.model.pojo.Qualification;
import com.application.job.model.pojo.Session;
import com.application.job.util.Constants;

@Entity(value = "T_Users", noClassnameStored = true)
public class User extends BaseEntity{	
	
	private String userName;
	private String email;
	private String password;
	private String phone;
	@Embedded
	private List<Qualification> qualifications;
	@Embedded 
	private List<Skill> skills;
	private int userType;
	@Embedded
	private List<Session> sessions;
	@Embedded
	private List<Job> recommended;
	private int verified;
	private int emailVerified;
	private int installed;
	
	public User() {
		super();
		status = Constants.STATUS_ACTIVE;
	}

	public User(String userName, String email, String password, String phone, List<Qualification> qualifications,
			List<Skill> skills, int userType, List<Session> sessions, List<Job> recommended, int verified,
			int emailVerified, int installed) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.qualifications = qualifications;
		this.skills = skills;
		this.userType = userType;
		this.sessions = sessions;
		this.recommended = recommended;
		this.verified = verified;
		this.emailVerified = emailVerified;
		this.installed = installed;
		status = Constants.STATUS_ACTIVE;
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

	public List<Qualification> getQualifications() {
		return qualifications;
	}

	public void setQualifications(List<Qualification> qualifications) {
		this.qualifications = qualifications;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public List<Job> getRecommended() {
		return recommended;
	}

	public void setRecommended(List<Job> recommended) {
		this.recommended = recommended;
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

	@Override
	public String toString() {
		return "User [userName=" + userName + ", email=" + email + ", password=" + password + ", phone=" + phone
				+ ", qualifications=" + qualifications + ", skills=" + skills + ", userType=" + userType + ", sessions="
				+ sessions + ", recommended=" + recommended + ", verified=" + verified + ", emailVerified="
				+ emailVerified + ", installed=" + installed + ", id=" + id + ", creationDate=" + creationDate
				+ ", lastChange=" + lastChange + ", status=" + status + "]";
	}
}