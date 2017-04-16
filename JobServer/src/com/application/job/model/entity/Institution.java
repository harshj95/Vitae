package com.application.job.model.entity;

import org.mongodb.morphia.annotations.Entity;

import com.application.job.util.Constants;

@Entity(value = "T_Institutions", noClassnameStored = true)
public class Institution extends BaseEntity {
	
	private String institutionName;
	private String level;
	
	public Institution() {
		super();
		status = Constants.STATUS_ACTIVE;
	}

	public Institution(String institutionName, String level) {
		super();
		this.institutionName = institutionName;
		this.level = level;
		status = Constants.STATUS_ACTIVE;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Institution [institutionName=" + institutionName + ", level="
				+ level + ", id=" + id + ", creationDate=" + creationDate + ", lastChange=" + lastChange + ", status="
				+ status + "]";
	}
}
