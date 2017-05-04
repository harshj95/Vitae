package com.application.job.model.pojo;

import java.util.List;

import com.application.job.model.entity.Job;

public class JobPojo {
	
	private String companyName;
	private IndustryModel industry;
	private String designation;
	private Experience experience;
	private List<SkillModel> skills;
	private String description;
	private Level level;
	private Requirement requirement;
	private Location location;
	private float salary;
	
	public JobPojo(Job job) {
		super();
		this.companyName = job.getCompany();
		this.industry = job.getIndustry();
		this.designation = job.getDesignation();
		this.experience = job.getExperience();
		this.skills = job.getSkills();
		this.description = job.getDescription();
		this.level = job.getLevel();
		this.requirement = job.getRequirement();
		this.location = job.getLocation();
		this.salary = job.getSalary();
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public IndustryModel getIndustry() {
		return industry;
	}

	public void setIndustry(IndustryModel industry) {
		this.industry = industry;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public List<SkillModel> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillModel> skills) {
		this.skills = skills;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Requirement getRequirement() {
		return requirement;
	}

	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
}
