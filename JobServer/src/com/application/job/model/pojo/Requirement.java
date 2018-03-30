package com.application.job.model.pojo;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Requirement {
	
	private int degreeId;
	private String degree;
	private String course;
	
	public Requirement() {
		super();
	}

	public Requirement(int degreeId, String degree, String course) {
		super();
		this.degreeId = degreeId;
		this.degree = degree;
		this.course = course;
	}

	public int getDegreeId() {
		return degreeId;
	}

	public void setDegreeId(int degreeId) {
		this.degreeId = degreeId;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
}
