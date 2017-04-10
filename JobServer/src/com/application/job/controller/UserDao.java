package com.application.job.controller;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.application.job.model.entity.User;
import com.application.job.util.DBUtil;
import com.application.job.util.exception.ZException;
import com.application.job.util.Constants;
import com.application.job.controller.BaseDao;
import com.application.job.controller.UserDao;

public class UserDao {

	private final Datastore datastore;
	private final BaseDao dao;
	
	public UserDao() {
		datastore = DBUtil.instance().getDatabase();
		dao = new BaseDao();
	}
	
	public boolean updateField(ObjectId userId, String field, Object value)
	{
		boolean toReturn = false;
		try
		{
			Query<User> query = datastore.createQuery(User.class).field("id").equal(userId);
			UpdateOperations<User> operations = datastore.createUpdateOperations(User.class);
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
	
	public User userActive(String accessToken) 
	{
		User user = null;
		try 
		{
			Query<User> query = datastore.createQuery(User.class);
					
			Criteria access = query.criteria("sessions.accessToken").equal(accessToken);
			Criteria active = query.criteria("status").equal(Constants.STATUS_ACTIVE);
			
			query.and(access, active);
			
			user = query.get();
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		return user;
	}

	/**
	 * Get user details based on the email\password combo. Used in case of
	 * login. TODO: Add check for access Token from session
	 * 
	 */
	
	public User getUserDetails(String email, String accessToken) {

		User user = null;
		try 
		{
			Query<User> query = datastore.createQuery(User.class);
			
			Criteria access = query.criteria("sessions.accessToken").equal(accessToken);
			Criteria Email = query.criteria("c_email").equal(email);
			
			query.and(access, Email);
			
			user = query.get();
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		return user;
	}
	
	public User getUserDetailsFromEmail(String email) {

		User user = null;
		try 
		{	
			Query<User> query = datastore.createQuery(User.class).field("c_email").equal(email);
			
			user = query.get();
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		return user;
	}	
}
