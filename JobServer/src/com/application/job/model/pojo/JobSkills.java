package com.application.job.model.pojo;

import java.io.Serializable;

import com.application.job.model.entity.Skill;

public class JobSkills implements Serializable{
	
	private Skill skill;
	private double tf;
	
	public JobSkills() {
		super();
	}

	public JobSkills(Skill skill, double tf) {
		super();
		this.skill = skill;
		this.tf = tf;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public double getTf() {
		return tf;
	}

	public void setTf(double tf) {
		this.tf = tf;
	}

	@Override
	public String toString() {
		return "JobSkills [skill=" + skill + ", tf=" + tf + "]";
	}
}
