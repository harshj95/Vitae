package com.application.job.model.pojo;

import java.io.Serializable;
import java.util.List;

import com.application.job.model.entity.Job;

public class JobModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3594738763386930520L;
	
	private Job job;
	private List<UserSkill> userSkills;
	private double factor;
	
	public JobModel() {
		super();
	}

	public JobModel(Job job, List<UserSkill> userSkills, double factor) {
		super();
		this.job = job;
		this.userSkills = userSkills;
		this.factor = factor;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public List<UserSkill> getSkills() {
		return userSkills;
	}

	public void setSkills(List<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

	@Override
	public String toString() {
		return "JobModel [job=" + job + ", userSkills=" + userSkills + ", factor=" + factor + "]";
	}
}