package com.application.job.model.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import com.application.job.model.pojo.IndustryModel;
import com.application.job.util.Constants;

@Entity(value = "T_Skills", noClassnameStored = true)
public class Skill extends BaseEntity {
	
	private int skillId;
	private String skillName;
	@Embedded
	private List<IndustryModel> industries;

	public Skill() {
		super();
		status = Constants.STATUS_ACTIVE;
	}

	public Skill(int skillId, String skillName, List<IndustryModel> industries) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
		this.industries = industries;
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

	public List<IndustryModel> getIndustries() {
		return industries;
	}

	public void setIndustries(List<IndustryModel> industries) {
		this.industries = industries;
	}

	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", skillName=" + skillName + ", industries=" + industries + ", id=" + id
				+ ", creationDate=" + creationDate + ", lastChange=" + lastChange + ", status=" + status + "]";
	}
}
