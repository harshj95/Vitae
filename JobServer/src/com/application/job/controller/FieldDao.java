package com.application.job.controller;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.application.job.model.entity.Category;
import com.application.job.model.entity.Field;
import com.application.job.util.DBUtil;
import com.application.job.util.exception.ZException;

public class FieldDao {
	
	private final Datastore datastore;
	private final BaseDao dao;
	
	public FieldDao() {
		datastore = DBUtil.instance().getDatabase();
		dao = new BaseDao();
	}
	
	public Field getById(int fieldId)
	{
		Field field = null;
		
		try
		{
			Query<Field> query = datastore.createQuery(Field.class).field("c_field_id").equal(fieldId);
			field = query.get();
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		
		return field;
	}
	
	public Field addToField(ObjectId fieldId, ObjectId categoryId)
	{
		Query<Field> query = datastore.createQuery(Field.class).field("id").equal(fieldId);
		Category category = dao.get(Category.class, categoryId);
		UpdateOperations<Field> operations = datastore.createUpdateOperations(Field.class).addToSet("categories", category);
		datastore.update(query, operations);
		
		return dao.get(Field.class, fieldId);
	}

}
