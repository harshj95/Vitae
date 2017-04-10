package com.application.job.model.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import com.application.job.model.pojo.Location;
import com.application.job.util.Constants;

@Entity(value = "T_Jobs", noClassnameStored = true)
public class Job extends BaseEntity {
	
	@Embedded
	private Company company;
	private String companyName;
	@Embedded
	private List<Skill> skills;
	@Property("c_salary")
	private float salary;
	@Embedded
	private Location location;
	
	public Job() {
		super();
	}

	public Job(Company company, List<Skill> skill, float salary, Location location, String name) {
		super();
		this.company = company;
		this.skills = skill;
		this.salary = salary;
		this.location = location;
		status = Constants.STATUS_ACTIVE;
		this.companyName = name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Job [company=" + company + ", skills=" + skills + ", salary=" + salary + ", location=" + location
				+ ", id=" + id + ", creationDate=" + creationDate + ", lastChange=" + lastChange + ", status=" + status
				+ "]";
	}
}
