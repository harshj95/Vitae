package com.application.job.controller;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.application.job.model.entity.Job;
import com.application.job.model.pojo.SkillModel;
import com.application.job.util.DBUtil;

public class JobDao extends BaseDao{
	
	private final Datastore datastore;
	
	public JobDao() {
		datastore = DBUtil.instance().getDatabase();
	}
	
	public List<Job> getJobsById(int industryId)
	{
		Query<Job> query = datastore.createQuery(Job.class).field("industry.industryId").equal(industryId);
		
		return query.asList();
	}
	
	
	
	
	public List<List<SkillModel>> jobParser(List<Job> jobs)
	{
		List<List<SkillModel>> jobSkills = new ArrayList<List<SkillModel>>();
		for(Job job : jobs)
		{
			jobSkills.add(job.getSkills());
		}
		return jobSkills;
	}
}
