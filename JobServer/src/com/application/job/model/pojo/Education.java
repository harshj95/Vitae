package com.application.job.model.pojo;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Education {
	
	private int degreeId;
	private String degreeName;
	private String course;
	@Embedded
	private InstitutionModel institution;
	
	public Education() {
		super();
	}

	public Education(int degreeId, String degreeName, String course, InstitutionModel institution) {
		super();
		this.degreeId = degreeId;
		this.degreeName = degreeName;
		this.course = course;
		this.institution = institution;
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

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public InstitutionModel getInstitution() {
		return institution;
	}

	public void setInstitution(InstitutionModel institution) {
		this.institution = institution;
	}
}
