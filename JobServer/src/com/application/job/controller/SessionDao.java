package com.application.job.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.application.job.model.pojo.Session;
import com.application.job.model.entity.User;
import com.application.job.util.Constants;
import com.application.job.util.DBUtil;
import com.application.job.util.exception.ZException;

public class SessionDao {
	
	private final Datastore datastore;
	private final BaseDao dao;
	
	public SessionDao() {
		datastore = DBUtil.instance().getDatabase();
		dao = new BaseDao();
	}
	/**
	 * Delete an accessToken for a particular user
	 */
	public boolean nullifyAccessToken(ObjectId userId, String accessToken) {

		User user = null;
		boolean removed = false;
		try 
		{
			Query<User> query = datastore.createQuery(User.class).field("id").equal(userId);
			UpdateOperations<User> operations = datastore.createUpdateOperations(User.class);
			user = query.get();
			
			List<Session> sessions = user.getSessions();
			
			if(sessions.size()==1)
			{
				dao.updateField(User.class, userId, "status", Constants.STATUS_INACTIVE);
			}
			
			if(sessions!=null || !sessions.isEmpty())
			{
				for(Session sesh : sessions)
				{
					if(sesh.getAccessToken().equals(accessToken))
					{
						operations.disableValidation().removeAll("sessions", sesh);
						datastore.update(query, operations);
						UpdateResults result = datastore.update(query, operations);
						removed = result.getUpdatedExisting();
						break;
					}
				}
			}

		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		return removed;
	}
}
