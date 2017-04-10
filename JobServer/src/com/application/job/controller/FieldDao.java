package com.application.job.controller;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

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

}
