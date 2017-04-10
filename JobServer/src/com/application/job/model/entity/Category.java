package com.application.job.model.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "T_Categories", noClassnameStored = true)
public class Category extends BaseEntity {
	
	@Property("c_category_name")
	private String categoryName;
	@Embedded
	private List<Skill> skills;
	
	public Category() {
		super();
	}

	public Category(String categoryName, List<Skill> skills) {
		super();
		this.categoryName = categoryName;
		this.skills = skills;
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
		return "Category [categoryName=" + categoryName + ", skills=" + skills + ", id=" + id
				+ ", creationDate=" + creationDate + ", lastChange=" + lastChange + ", status=" + status + "]";
	}
}
