package com.application.job.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.application.job.model.entity.BaseEntity;
import com.application.job.util.DBUtil;
import com.application.job.util.exception.ZException;

public class BaseDao {

	private final Datastore datastore;

	public BaseDao() {
		datastore = DBUtil.instance().getDatabase();
	}
	
	public <E extends BaseEntity> E add(E entity) 
	{
	    try
	    {
	    	datastore.save(entity);
	    	return entity;
	    } catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return null;
			}
	    }
	}
	
	public <E extends BaseEntity> E update (Class<E> clazz, ObjectId id, E entity)
	{
		try
		{
			Query<E> query = datastore.createQuery(clazz).field("id").equal(id);
			datastore.updateFirst(query, entity, false);
			return entity;
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return null;
			}
	    }
	}

	public <E extends BaseEntity> E get(Class<E> clazz, final ObjectId id) 
	{
		if ((clazz==null) || (id==null)) {
			return null;
	    }

	    try
	    {
	    	return datastore.find(clazz).field("id").equal(id).get();
	    } catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return null;
			}
	    }
	}
	
	public <E extends BaseEntity> List<E> getAll(Class<E> clazz)
	{
		if((clazz==null))
			return null;
		
		try
		{
			return datastore.createQuery(clazz).asList();
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return new ArrayList<E>();
			}
	    }
	}
	
	public <E extends BaseEntity> long count(Class<E> clazz) 
	{
		if (clazz==null) {
			return 0;
	    }

	    try
	    {
	    	return datastore.find(clazz).count();
	    } catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return 0;
			}
	    }
	}
}
