package com.application.job.model.entity;

import org.mongodb.morphia.annotations.Entity;

import com.application.job.util.Constants;

@Entity(value = "T_Categories", noClassnameStored = true)
public class Category extends BaseEntity {
	
	private int categoryId;
	private String categoryName;
	
	public Category() {
		super();
		status = Constants.STATUS_ACTIVE;
	}

	public Category(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
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

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", id=" + id 
				+ ", creationDate=" + creationDate + ", lastChange=" + lastChange + ", status=" + status + "]";
	}
}
