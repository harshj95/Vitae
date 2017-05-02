package com.application.job.model.pojo;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Level {
	
	private int levelId;
	private String level;
	
	public Level() {
		super();
	}

	public Level(int levelId, String level) {
		super();
		this.levelId = levelId;
		this.level = level;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
