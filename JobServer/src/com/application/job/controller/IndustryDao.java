package com.application.job.controller;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.application.job.model.entity.Industry;
import com.application.job.util.DBUtil;
import com.application.job.util.exception.ZException;

public class IndustryDao {
	
	private final Datastore datastore;
	
	public IndustryDao() {
		datastore = DBUtil.instance().getDatabase();
	}
	
	public Industry getById(int industryId)
	{
		Industry industry = null;
		
		try
		{
			Query<Industry> query = datastore.createQuery(Industry.class).field("industryId").equal(industryId);
			industry = query.get();
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		
		return industry;
	}
}
