package com.application.job.model.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "T_Fields", noClassnameStored = true)
public class Field extends BaseEntity {
	
	@Property("c_field_id")
	private int fieldId;
	@Property("c_field_name")
	private String fieldName;
	@Embedded 
	private List<Category> categories;
	
	public Field() {
		super();
	}

	public Field(int fieldId, String fieldName, List<Category> categories) {
		super();
		this.fieldId = fieldId;
		this.fieldName = fieldName;
		this.categories = categories;
	}

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Field [fieldId=" + fieldId + ", fieldName=" + fieldName + ", categories=" + categories + ", id=" + id
				+ ", creationDate=" + creationDate + ", lastChange=" + lastChange + ", status=" + status + "]";
	}
}
