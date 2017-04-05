package com.application.job.controller;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.application.job.model.User;
import com.application.job.util.DBUtil;
import com.application.job.util.exception.ZException;
import com.application.job.controller.UserDao;

public class UserDao extends BaseDao {

	public static final String LOGGER = "BaseDao.class";

	public UserDao() {
		super(UserDao.LOGGER);
	}

	/**
	 * Add user details to the DB for a newly signed up user. Updates User and
	 * Session tables.
	 */
	public User addUserDetails(User userParam) {
		User user;
		Session session = null;
		info("addUserDetails enter");
		try {
		
			session = DBUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			user = userParam;
			session.save(user);

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
			}
			error("Hibernate exception: " + e.getMessage());
			user = null;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		info("addUserDetails exit");
		return user;
	}
	
	public User userActive(String accessToken) {

		User user = null;
		Session session = null;
		info("userActive enter");
		try {
			
			session = DBUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();

			String sql = "SELECT * FROM T_Users Where T_Users.c_user_id = (Select T_Session.c_user_id From T_Session WHERE c_access_token = :access_token)";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(User.class);
			query.setParameter("access_token", accessToken);
			java.util.List results = (java.util.List) query.list();

			if (results != null && results.size() > 0) {
				user = (User) results.get(0);
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
		info("userActive exit");
		return user;
	}

	public User updateUserDetails(User user) {

		Session session = null;
		info("updateUserDetails enter");
		try {
			session = DBUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			user = null;
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
		info("updateUserDetails exit");
		return user;
	}

	/**
	 * Get user details based on the email\password combo. Used in case of
	 * login. TODO: Add check for access Token from session
	 * 
	 */
	
	public User getUserDetails(String email, String accessToken) {

		User user = null;
		Session session = null;
		info("getUserDetails enter");
		try {
			
			session = DBUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			
			String sql = "SELECT * FROM T_Users WHERE c_user_email = :email_id && T_Users.c_user_id = (Select T_Session.c_user_id From T_Session WHERE c_access_token = :access_token)";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(User.class);
			query.setParameter("email_id", email);
			query.setParameter("access_token", accessToken);
			java.util.List results = (java.util.List) query.list();

			for (Iterator iterator = ((java.util.List) results).iterator(); iterator.hasNext();) {
				user = (User) iterator.next();
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
		info("getUserDetails exit");
		return user;
	}
	
	public User getUserDetailsFromEmail(String email) {

		User user = null;
		Session session = null;
		info("getUserDetails enter");
		try {	

			session = DBUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			String sql = "SELECT * FROM T_Users WHERE c_user_email = :email";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(User.class);
			query.setParameter("email", email);
			java.util.List results = (java.util.List) query.list();

			for (Iterator iterator = ((java.util.List) results).iterator(); iterator.hasNext();) {
				user = (User) iterator.next();
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
		info("getUserDetails exit");
		return user;
	}	
}
