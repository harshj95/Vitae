package com.application.job.model.pojo;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Experience {
	
	private int minimum;
	private int maximum;
	
	public Experience() {
		super();
	}

	public Experience(int minimum, int maximum) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
}
