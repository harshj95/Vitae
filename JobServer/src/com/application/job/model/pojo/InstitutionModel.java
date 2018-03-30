package com.application.job.model.pojo;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class InstitutionModel {
	
	private String institutionName;
	private int from;
	private int to;
	
	public InstitutionModel() {
		super();
	}

	public InstitutionModel(String institutionName, int from, int to) {
		super();
		this.institutionName = institutionName;
		this.from = from;
		this.to = to;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}
}
