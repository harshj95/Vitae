package com.application.job.model.entity;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Session implements Serializable {

	private String accessToken;
	private String pushId;
	private String imei;
	
	public Session() {
		super();
	}

	public Session(String accessToken, String pushId, String imei) {
		super();
		this.accessToken = accessToken;
		this.pushId = pushId;
		this.imei = imei;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
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
