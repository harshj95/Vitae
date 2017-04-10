package com.application.job.model.pojo;

import java.io.Serializable;
import java.util.List;

import com.application.job.model.entity.Job;

public class JobModel implements Serializable{
	
	private Job job;
	private List<JobSkills> skills;
	private double idf;
	private double factor;
	
	public JobModel() {
		super();
	}

	public JobModel(Job job, List<JobSkills> skills, double idf, double factor) {
		super();
		this.job = job;
		this.skills = skills;
		this.idf = idf;
		this.factor = factor;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public List<JobSkills> getSkills() {
		return skills;
	}

	public void setSkills(List<JobSkills> skills) {
		this.skills = skills;
	}

	public double getIdf() {
		return idf;
	}

	public void setIdf(double idf) {
		this.idf = idf;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

	@Override
	public String toString() {
		return "JobModel [job=" + job + ", skills=" + skills + ", idf=" + idf + ", factor=" + factor + "]";
	}
}