package com.application.job.model;

import java.io.Serializable;

public class Job implements Serializable{
	
	private int jobId;
	private int companyId;
	private String companyName;
	private String skillsArray;
	private float salary;
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSkillsArray() {
		return skillsArray;
	}
	public void setSkillsArray(String skillsArray) {
		this.skillsArray = skillsArray;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
}
