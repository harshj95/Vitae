package com.application.job.model.pojo;
import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class IndustryModel {
	
	private int industryId;
	private String industryName;
	
	public IndustryModel() {
		super();
	}
	
	public IndustryModel(int industryId, String industryName) {
		super();
		this.industryId = industryId;
		this.industryName = industryName;
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
}
