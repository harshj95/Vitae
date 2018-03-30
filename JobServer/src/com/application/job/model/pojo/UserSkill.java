package com.application.job.model.pojo;

import java.io.Serializable;

public class UserSkill implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5925243555716615582L;
	
	private SkillModel skill;
	private double tf;
	private double idf;
	private double tfIdf;
	
	public UserSkill() {
		super();
	}

	public UserSkill(SkillModel skill, double tf, double idf, double tfIdf) {
		super();
		this.skill = skill;
		this.tf = tf;
		this.idf = idf;
		this.tfIdf = tfIdf;
	}

	public SkillModel getSkill() {
		return skill;
	}

	public void setSkill(SkillModel skill) {
		this.skill = skill;
	}

	public double getTf() {
		return tf;
	}

	public void setTf(double tf) {
		this.tf = tf;
	}

	public double getIdf() {
		return idf;
	}

	public void setIdf(double idf) {
		this.idf = idf;
	}

	public double getTfIdf() {
		return tfIdf;
	}

	public void setTfIdf(double tfIdf) {
		this.tfIdf = tfIdf;
	}

	@Override
	public String toString() {
		return "UserSkill [skill=" + skill + ", tf=" + tf + ", idf=" + idf + ", tfIdf=" + tfIdf + "]";
	}
}
