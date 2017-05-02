package com.application.job.model.pojo;

import com.application.job.model.entity.Institution;

public class InstitutionModel {
	
	private String institutionName;
	private int levelCode;
	private String level;
	
	public InstitutionModel() {
		super();
	}

	public InstitutionModel(Institution institution) {
		super();
		this.institutionName = institution.getInstitutionName();
		this.levelCode = institution.getLevelCode();
		this.level = institution.getLevel();
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public int getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(int levelCode) {
		this.levelCode = levelCode;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
