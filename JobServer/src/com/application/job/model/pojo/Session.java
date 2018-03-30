package com.application.job.model.pojo;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Session implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1877935487600815919L;
	
	private String accessToken;
	private String loginType;
	private String pushId;
	private String imei;
	
	public Session() {
		super();
	}

	public Session(String accessToken, String loginType, String pushId, String imei) {
		super();
		this.accessToken = accessToken;
		this.loginType = loginType;
		this.pushId = pushId;
		this.imei = imei;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
}
