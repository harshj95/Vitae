package com.application.job.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

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
	
	public <E extends BaseEntity> boolean update(Class<E> clazz, String search, Object searchValue, 
			String field, Object value)
	{
		boolean toReturn = false;
		try
		{
			Query<E> query = datastore.createQuery(clazz).field(search).equal(searchValue);
			UpdateOperations<E> operations = datastore.createUpdateOperations(clazz);
			operations.disableValidation().set(field, value);
			
			UpdateResults result = datastore.update(query, operations);
			toReturn = result.getUpdatedExisting();
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		return toReturn;
	}
	
	public <E extends BaseEntity> boolean updateField(Class<E> clazz, ObjectId id, String field, Object value)
	{
		boolean toReturn = false;
		try
		{
			Query<E> query = datastore.createQuery(clazz).field("id").equal(id);
			UpdateOperations<E> operations = datastore.createUpdateOperations(clazz);
			operations.disableValidation().set(field, value);
			
			UpdateResults result = datastore.update(query, operations);
			toReturn = result.getUpdatedExisting();
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		return toReturn;
	}
	
	public <E extends BaseEntity, T extends BaseEntity> E addToSet(Class<E> clazz, Class<T> clazzz, 
			ObjectId setId, ObjectId valueId, String set)
	{
		E obj = null;
		try
		{
			Query<E> query = datastore.createQuery(clazz).field("id").equal(setId);
			T object = get(clazzz, valueId);
			UpdateOperations<E> operations = datastore.createUpdateOperations(clazz).addToSet(set, object);
			
			datastore.update(query, operations);
			obj = get(clazz, setId); 
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		return obj;
	}
	
	public <E extends BaseEntity, T> boolean addObject(Class<E> clazz, Class<T> clazzz, 
			ObjectId setId, T object, String set)
	{
		boolean added;
		try
		{
			Query<E> query = datastore.createQuery(clazz).field("id").equal(setId);
			UpdateOperations<E> operations = datastore.createUpdateOperations(clazz).addToSet(set, object);
			
			datastore.update(query, operations);
			added = true;
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				added = false;
			}
		}
		return added;
	}
	
	public <E extends BaseEntity, T extends BaseEntity> E addEntityObject(Class<E> clazz, Class<T> clazzz, 
			ObjectId setId, T object, String set)
	{
		E obj = null;
		try
		{
			Query<E> query = datastore.createQuery(clazz).field("id").equal(setId);
			UpdateOperations<E> operations = datastore.createUpdateOperations(clazz).addToSet(set, object);
			
			datastore.update(query, operations);
			obj = get(clazz, setId); 
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		return obj;
	}
	
	public <E extends BaseEntity, T extends BaseEntity> E addSet(Class<E> clazz, Class<T> clazzz, 
			ObjectId setId, List<T> objects, String set)
	{
		E obj = null;
		try
		{
			Query<E> query = datastore.createQuery(clazz).field("id").equal(setId);
			UpdateOperations<E> operations = datastore.createUpdateOperations(clazz).addToSet(set, objects);
			
			datastore.update(query, operations);
			obj = get(clazz, setId); 
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		return obj;
	}
	
	public <E extends BaseEntity, T> E addObjectSet(Class<E> clazz, Class<T> clazzz, 
			ObjectId setId, List<T> objects, String set)
	{
		E obj = null;
		try
		{
			Query<E> query = datastore.createQuery(clazz).field("id").equal(setId);
			UpdateOperations<E> operations = datastore.createUpdateOperations(clazz).addToSet(set, objects);
			
			datastore.update(query, operations);
			obj = get(clazz, setId); 
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		return obj;
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
