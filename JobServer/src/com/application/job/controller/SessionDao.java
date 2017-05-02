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
	 * Utility method to generate an accessToken.
	 */
	/*public Object[] generateAccessToken(String userName, int userId, String deviceId, String regId) {
		Object[] tokens = new Object[2];
		String accessToken = "";
		Transaction transaction = null;
		Session session = null;
		boolean retType = false;

		try {

			session = DBUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String sql = "SELECT * FROM Session WHERE UserId = :user_id and DeviceId = :device_id";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(com.application.job.model.Session.class);
			query.setParameter("user_id", userId);
			query.setParameter("device_id", deviceId);
			java.util.List results = (java.util.List) query.list();

			if (results != null && results.size() > 0) {
				for (Iterator iterator = ((java.util.List) results).iterator(); iterator.hasNext();) {
					retType = true;
					com.application.job.model.Session sessionToken = (com.application.job.model.Session) iterator
							.next();
					accessToken = sessionToken.getAccessToken();
					boolean shouldUpdate = false;
					if (regId != null && !regId.isEmpty()) {
						sessionToken.setPushId(regId);
						shouldUpdate = true;
					}

					if (shouldUpdate)
						session.update(sessionToken);

					break;
				}
			} else {
				String keySource = userName + String.valueOf(System.currentTimeMillis() * 1000)
						+ String.valueOf((int) (Math.random() * 1000 * 1000));
				byte[] tokenByte = Base64.encodeBase64(keySource.getBytes());
				accessToken = new String(tokenByte);
			}

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			transaction.rollback();
			try{
				throw new ZException("Error",e);
			}
			catch(ZException e1){
				e1.printStackTrace();
			}
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		tokens[0] = accessToken;
		tokens[1] = retType;
		return tokens;
	}*/

	/*public boolean updateRegistratonId(String pushId, String accessToken) {

		Session session = null;

		try {

			session = DBUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();

			String sql = "SELECT * FROM T_Session WHERE c_access_token = :access_token";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(com.application.job.model.Session.class);
			query.setParameter("access_token", accessToken);
			java.util.List results = (java.util.List) query.list();
			com.application.job.model.Session currentSession = (com.application.job.model.Session) results
					.get(0);
			if (currentSession != null) {
				currentSession.setPushId(pushId);
				currentSession.setModified(System.currentTimeMillis());
				session.update(currentSession);
			}

			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			try{
				throw new ZException("Error",e);
			}
			catch(ZException e1){
				e1.printStackTrace();
			}
			return false;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}

		return true;
	}*/

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

	/*public ArrayList<String> getUserPushIds(int userId) {
		ArrayList<String> pushIds = new ArrayList<String>();
		Session session = null;
		info("getStore enter");
		try {
			session = DBUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();

			String sql = "SELECT PushId FROM T_Session WHERE c_user_id = :userId";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("userId", userId);
			java.util.List results = (java.util.List) query.list();

			for (Iterator iterator = ((java.util.List) results).iterator(); iterator.hasNext();) {
				String pushId = String.valueOf(iterator.next());
				pushIds.add(pushId);
			}

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			try{
				throw new ZException("Error",e);
			}
			catch(ZException e1){
				e1.printStackTrace();
			}
			error("Hibernate exception: " + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		info("getStore exit");
		return pushIds;
	}
	
	public com.application.job.model.Session getSessionById(int userId)
	{
		com.application.job.model.Session userSession = null;
		Session session = null;
		info("getSessionById enter");
		try {	

			session = DBUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			String sql = "SELECT * FROM T_Session WHERE c_user_id = :userId";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(com.application.job.model.Session.class);
			query.setParameter("userId", userId);
			java.util.List results = (java.util.List) query.list();

			for (Iterator iterator = ((java.util.List) results).iterator(); iterator.hasNext();) {
				userSession = (com.application.job.model.Session) iterator.next();
			}

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
			}
			error("Hibernate exception: " + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		info("getSessionById exit");
		return userSession;
	}

	public ArrayList<String> getAllPushIds(int start, int count) {
		ArrayList<String> pushIds = new ArrayList<String>();
		Session session = null;
		info("getStore enter");
		try {
			session = DBUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();

			String sql = "SELECT PushId FROM T_Session LIMIT :start, :count";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("start", start);
			query.setParameter("count", count);
			java.util.List results = (java.util.List) query.list();

			for (Iterator iterator = ((java.util.List) results).iterator(); iterator.hasNext();) {
				String pushId = String.valueOf(iterator.next());
				pushIds.add(pushId);
			}

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			try{
				throw new ZException("Error",e);
			}
			catch(ZException e1){
				e1.printStackTrace();
			}
			error("Hibernate exception: " + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		info("getStore exit");
		return pushIds;
	}
	
	public int getPushIdsCount() {
		int size = 0;
		Session session = null;
		info("getStore enter");
		try {
			session = DBUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();

			String sql = "SELECT count(*) FROM T_Session";
			SQLQuery query = session.createSQLQuery(sql);
			java.util.List results = (java.util.List) query.list();

			if (results != null && results.size() > 0) {
				Object resultValue = results.get(0);
				if (resultValue instanceof BigInteger)
					size = ((BigInteger) results.get(0)).intValue();
				else
					size = 0;
			}

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			try{
				throw new ZException("Error",e);
			}
			catch(ZException e1){
				e1.printStackTrace();
			}
			error("Hibernate exception: " + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		info("getStore exit");
		return size;
	}*/
}
