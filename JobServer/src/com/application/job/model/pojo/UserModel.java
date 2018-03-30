package com.application.job.model.pojo;

public class UserModel {
	
	private String userId;
	private String email;
	private String userName;
	private String phone;
	private String accessToken;
	
	public UserModel(String userId, String email, String userName, String phone, String accessToken) {
		super();
		this.userId = userId;
		this.email = email;
		this.userName = userName;
		this.phone = phone;
		this.accessToken = accessToken;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
