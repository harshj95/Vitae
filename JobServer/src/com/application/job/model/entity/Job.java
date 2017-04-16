package com.application.job.model.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import com.application.job.model.pojo.Requirement;
import com.application.job.model.pojo.Experience;
import com.application.job.model.pojo.Location;
import com.application.job.util.Constants;

@Entity(value = "T_Jobs", noClassnameStored = true)
public class Job extends BaseEntity {
	
	@Embedded
	private Company company;
	private int industryId;
	private String industryName;
	private String desgination;
	@Embedded
	private Experience experience;
	@Embedded
	private List<Skill> skills;
	private String description;
	@Embedded 
	Requirement requirement;
	@Embedded
	private Location location;
	private float salary;
	
	public Job() {
		super();
		status = Constants.STATUS_ACTIVE;
	}

	public Job(Company company, int industryId, String industryName, String desgination, Experience experience,
			List<Skill> skills, String description, Requirement requirement, Location location, float salary) {
		super();
		this.company = company;
		this.industryId = industryId;
		this.industryName = industryName;
		this.desgination = desgination;
		this.experience = experience;
		this.skills = skills;
		this.description = description;
		this.requirement = requirement;
		this.location = location;
		this.salary = salary;
		status = Constants.STATUS_ACTIVE;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getIndustryId() {
		return industryId;
	}

	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getDesgination() {
		return desgination;
	}

	public void setDesgination(String desgination) {
		this.desgination = desgination;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "Job [company=" + company + ", industryId=" + industryId + ", industryName=" + industryName
				+ ", desgination=" + desgination + ", experience=" + experience + ", skills=" + skills
				+ ", description=" + description + ", requirement=" + requirement + ", location=" + location
				+ ", salary=" + salary + ", id=" + id + ", creationDate=" + creationDate + ", lastChange=" + lastChange
				+ ", status=" + status + "]";
	}
}