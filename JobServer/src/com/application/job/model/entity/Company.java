package com.application.job.model.entity;

import org.mongodb.morphia.annotations.Entity;

@Entity(value = "T_Companies", noClassnameStored = true)
public class Company extends BaseEntity{

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
