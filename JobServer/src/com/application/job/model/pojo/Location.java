package com.application.job.model.pojo;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Location {

	private String city;
	private double latitude;
	private double longitude;
	
	public Location() {
		super();
	}

	public Location(String city, double latitude, double longitude) {
		super();
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
