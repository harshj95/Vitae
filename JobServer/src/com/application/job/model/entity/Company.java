package com.application.job.model.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import com.application.job.util.Constants;

@Entity(value = "T_Companies", noClassnameStored = true)
public class Company extends BaseEntity{
	
	private String companyName;
	private String description;
	private String url;
	@Embedded
	private List<Job> jobs;
	
	public Company() {
		super();
		status = Constants.STATUS_ACTIVE;
	}

	public Company(String companyName, String description, String url, List<Job> jobs) {
		super();
		this.companyName = companyName;
		this.description = description;
		this.url = url;
		this.jobs = jobs;
		status = Constants.STATUS_ACTIVE;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	@Override
	public String toString() {
		return "Company [companyName=" + companyName + ", description=" + description + ", url=" + url + ", jobs="
				+ jobs + ", id=" + id + ", creationDate=" + creationDate + ", lastChange=" + lastChange + ", status="
				+ status + "]";
	}
}
