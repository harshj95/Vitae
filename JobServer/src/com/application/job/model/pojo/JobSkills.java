package com.application.job.model.pojo;

public class JobSkills {
	
	private int categoryId;
	private int skillId;
	private String skill;
	
	public JobSkills(int categoryId, int skillId, String skill) {
		super();
		this.categoryId = categoryId;
		this.skillId = skillId;
		this.skill = skill;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
}
