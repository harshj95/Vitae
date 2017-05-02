package com.application.job.model.entity;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import com.application.job.model.pojo.IndustryModel;
import com.application.job.util.Constants;

@Entity(value = "T_Skills", noClassnameStored = true)
public class Skill extends BaseEntity {
	
	private int skillId;
	private String skillName;
	@Embedded
	private IndustryModel industry;

	public Skill() {
		super();
		status = Constants.STATUS_ACTIVE;
	}

	public Skill(int skillId, String skillName, IndustryModel industry) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
		this.industry = industry;
		status = Constants.STATUS_ACTIVE;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public IndustryModel getIndustry() {
		return industry;
	}

	public void setIndustry(IndustryModel industry) {
		this.industry = industry;
	}

	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", skillName=" + skillName + ", industry=" + industry + ", id=" + id
				+ ", creationDate=" + creationDate + ", lastChange=" + lastChange + ", status=" + status + "]";
	}
}
