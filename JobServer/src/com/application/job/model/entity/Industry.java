package com.application.job.model.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

import com.application.job.util.Constants;

@Entity(value = "T_Industries", noClassnameStored = true)
public class Industry extends BaseEntity {
	
	@Indexed(options = @IndexOptions(unique = true))
	private int industryId;
	private String industryCode;
	private String industryName;
	@Embedded 
	private List<Category> categories;
	
	public Industry() {
		super();
		status = Constants.STATUS_ACTIVE;
	}

	public Industry(int industryId, String industryCode, String industryName, List<Category> categories) {
		super();
		this.industryId = industryId;
		this.industryCode = industryCode;
		this.industryName = industryName;
		this.categories = categories;
		status = Constants.STATUS_ACTIVE;
	}

	public int getIndustryId() {
		return industryId;
	}

	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Industry [industryId=" + industryId + ", industryCode=" + industryCode + ", industryName="
				+ industryName + ", categories=" + categories + ", id=" + id + ", creationDate=" + creationDate
				+ ", lastChange=" + lastChange + ", status=" + status + "]";
	}
}
