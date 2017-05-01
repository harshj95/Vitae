package com.application.job.model.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

import com.application.job.util.Constants;

@Entity(value = "T_Categories", noClassnameStored = true)
public class Category extends BaseEntity {
	
	private int categoryId;
	private String categoryName;
	@Embedded
	private List<Skill> skills;
	
	public Category() {
		super();
		status = Constants.STATUS_ACTIVE;
	}

	public Category(int categoryId, String categoryName, List<Skill> skills) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.skills = skills;
		status = Constants.STATUS_ACTIVE;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", skills=" + skills + ", id="
				+ id + ", creationDate=" + creationDate + ", lastChange=" + lastChange + ", status=" + status + "]";
	}
}
