package com.application.job.model.entity;

import org.mongodb.morphia.annotations.Entity;

import com.application.job.util.Constants;

@Entity(value = "T_Skills", noClassnameStored = true)
public class Skill extends BaseEntity {
	
	private String skillName;

	public Skill() {
		super();
		status = Constants.STATUS_ACTIVE;
	}

	public Skill(String skillName) {
		super();
		this.skillName = skillName;
		status = Constants.STATUS_ACTIVE;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	@Override
	public String toString() {
		return "Skill [skillName=" + skillName + ", id=" + id + ", creationDate="
				+ creationDate + ", lastChange=" + lastChange + ", status=" + status + "]";
	}
}
