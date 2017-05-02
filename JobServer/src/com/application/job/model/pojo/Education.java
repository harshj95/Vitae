package com.application.job.model.pojo;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;

import com.application.job.model.entity.Institution;

@Embedded
public class Education {
	
	private int degreeId;
	private String degreeName;
	@Embedded
	private List<InstitutionModel> institutions;
	
	public Education() {
		super();
	}

	public Education(int degreeId, String degreeName, List<InstitutionModel> institutions) {
		super();
		this.degreeId = degreeId;
		this.degreeName = degreeName;
		this.institutions = institutions;
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

	public List<InstitutionModel> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(List<InstitutionModel> institutions) {
		this.institutions = institutions;
	}
}
