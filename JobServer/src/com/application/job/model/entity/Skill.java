package com.application.job.model.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "T_Skills", noClassnameStored = true)
public class Skill extends BaseEntity {
	
	@Property("c_skill_name")
	private String skillName;

	public Skill() {
		super();
	}

	public Skill(String skillName) {
		super();
		this.skillName = skillName;
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
