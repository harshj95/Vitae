package com.application.job.model.pojo;

import org.mongodb.morphia.annotations.Embedded;

import com.application.job.model.entity.Institution;

@Embedded
public class Qualification {
	
	private int degreeId;
	private String degreeName;
	@Embedded
	private Institution institution;
	
	public Qualification() {
		super();
	}

	public Qualification(int degreeId, String degreeName, Institution institution) {
		super();
		this.degreeId = degreeId;
		this.degreeName = degreeName;
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

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
}
