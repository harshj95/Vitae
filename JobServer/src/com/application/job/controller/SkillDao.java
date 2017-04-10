package com.application.job.controller;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.application.job.model.entity.Category;
import com.application.job.model.entity.Skill;
import com.application.job.util.DBUtil;
import com.application.job.util.exception.ZException;

public class SkillDao {
	
	private final Datastore datastore;
	private final BaseDao dao;
	
	public SkillDao() {
		datastore = DBUtil.instance().getDatabase();
		dao = new BaseDao();
	}
	
	public Category addToCategory(ObjectId categoryId, ObjectId skillId)
	{
		Category category = null;
		try
		{
			Query<Category> query = datastore.createQuery(Category.class).field("id").equal(categoryId);
			Skill skill = dao.get(Skill.class, skillId);
			UpdateOperations<Category> operations = datastore.createUpdateOperations(Category.class).addToSet("skills", skill);
			datastore.update(query, operations);
			
			category = dao.get(Category.class, categoryId);
			
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		return category;
	}
}
