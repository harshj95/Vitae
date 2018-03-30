package com.application.job.model.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import com.application.job.model.pojo.Requirement;
import com.application.job.model.pojo.SkillModel;
import com.application.job.model.pojo.Experience;
import com.application.job.model.pojo.IndustryModel;
import com.application.job.model.pojo.Level;
import com.application.job.model.pojo.Location;
import com.application.job.util.Constants;

@Entity(value = "T_Jobs", noClassnameStored = true)
public class Job extends BaseEntity {
	
	private String companyName;
	@Embedded
	private IndustryModel industry;
	private String designation;
	@Embedded
	private Experience experience;
	@Embedded
	private List<SkillModel> skills;
	private String description;
	@Embedded
	private Level level;
	@Embedded 
	private Requirement requirement;
	@Embedded
	private Location location;
	private float salary;
	
	public Job() {
		super();
		status = Constants.STATUS_ACTIVE;
	}

	public Job(String companyName, IndustryModel industry, String designation, Experience experience, List<SkillModel> skills,
			String description, Level level, Requirement requirement, Location location, float salary) {
		super();
		this.companyName = companyName;
		this.industry = industry;
		this.designation = designation;
		this.experience = experience;
		this.skills = skills;
		this.description = description;
		this.level = level;
		this.requirement = requirement;
		this.location = location;
		this.salary = salary;
		status = Constants.STATUS_ACTIVE;
	}

	public String getCompany() {
		return companyName;
	}

	public void setCompany(String company) {
		this.companyName = company;
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

	@Override
	public String toString() {
		return "Job [companyName=" + companyName + ", industry=" + industry + ", designation=" + designation + ", experience="
				+ experience + ", skills=" + skills + ", description=" + description + ", level=" + level
				+ ", requirement=" + requirement + ", location=" + location + ", salary=" + salary + ", id=" + id
				+ ", creationDate=" + creationDate + ", lastChange=" + lastChange + ", status=" + status + "]";
	}
}