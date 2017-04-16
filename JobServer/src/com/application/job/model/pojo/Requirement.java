package com.application.job.model.pojo;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Requirement {
	
	private int degreeId;
	private String degreeName;
	
	public Requirement() {
		super();
	}

	public Requirement(int degreeId, String degreeName) {
		super();
		this.degreeId = degreeId;
		this.degreeName = degreeName;
	}

	public int getDegreeId() {
		return degreeId;
	}

	public void setDegreeId(int degreeId) {
		this.degreeId = degreeId;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
}
