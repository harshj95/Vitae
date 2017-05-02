package com.application.job.model.pojo;

import org.mongodb.morphia.annotations.Embedded;

import com.application.job.model.entity.Skill;

@Embedded
public class SkillModel {
	
	private int skillId;
	private String skillName;
	private String industry;
	
	public SkillModel() {
		super();
	}

	public SkillModel(Skill skill) {
		super();
		this.skillId = skill.getSkillId();
		this.skillName = skill.getSkillName();
		this.industry = skill.getIndustry();
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

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
}
