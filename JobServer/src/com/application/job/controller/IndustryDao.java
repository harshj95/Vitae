package com.application.job.controller;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.application.job.model.entity.Industry;
import com.application.job.util.Constants;
import com.application.job.util.DBUtil;
import com.application.job.util.exception.ZException;

public class IndustryDao {
	
	private final Datastore datastore;
	
	public IndustryDao() {
		datastore = DBUtil.instance().getDatabase();
	}
	
	public String industry(int industryId)
	{
		switch(industryId)
		{
			case Constants.ACCOMMODATION : 
				return "Accommodation and Food Services"; 
			
			case Constants.ADMINISTRATIVE :
				return "Administrative and Support Services";
				
			case Constants.AGRICULTURE :
				return "Agriculture, Forestry, Fishing";
				
			case Constants.ARTS : 
				return "Arts and Recreation Services";
			
			case Constants.CONSTRUCTION :
				return "Construction";
				
			case Constants.EDUCATION :
				return "Education and Training";
				
			case Constants.SERVICES :
				return "Electricity, Gas, Water, Waste Services";
				
			case Constants.FINANCIAL :
				return "Financial and Insurance Services";
			
			case Constants.HEALTHCARE :
				return "Health Care and Social Assistance";
			
			case Constants.MEDIA :
				return "Information Media and Telecommunications";
			
			case Constants.MANUFACTURING :
				return "Manufacturing";
			
			case Constants.MINING :
				return "Mining";
			
			case Constants.PROFESSIONAL :
				return "Professional, Scientific, Technical Services";
			
			case Constants.PUBLIC :
				return "Public Administration and Safety";
			
			case Constants.REAL_ESTATE :
				return "Rental, Hiring and Real Estate Services";
			
			case Constants.RETAIL :
				return "Retail Trade";
			
			case Constants.TRANSPORT : 
				return "Transport, Postal and Warehousing";
			
			case Constants.WHOLESALE :
				return "Wholesale Trade";
			
			case Constants.OTHERS :
				return "Others";
				
			default :
				return "Others";
		}
	}
	
	public Industry getById(int industryId)
	{
		Industry industry = null;
		
		try
		{
			Query<Industry> query = datastore.createQuery(Industry.class).field("industrydId").equal(industryId);
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
