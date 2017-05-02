package com.application.job.controller;

import java.util.ArrayList;
import java.util.List;

import com.application.job.model.entity.Job;
import com.application.job.model.entity.Skill;
import com.application.job.model.pojo.SkillModel;

public class JobDao extends BaseDao{
	
/*	private final Datastore datastore;
	private final BaseDao dao;
*/	
	public JobDao() {
		/*datastore = DBUtil.instance().getDatabase();
		dao = new BaseDao()*/;
	}
	
	/*public Job addJob(Job jobParam)
	{
		Job job;
		Session session = null;
		info("addUserDetails enter");
		try {
		
			session = DBUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			job = jobParam;
			session.save(job);

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
			}
			error("Hibernate exception: " + e.getMessage());
			job = null;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		info("addUserDetails exit");
		return job;
	}
	
	public List<Job> getProductById(int companyId)
	{
		Job product = null;
		List<Job> jobs = new ArrayList();
		Session session = null;
		info("getProductById enter");
		try
		{
			session = DBUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			
			String sql = "SELECT * FROM T_Jobs Where T_Jobs.c_company_id= :company_id";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Job.class);
			query.setParameter("company_id", companyId);
			java.util.List results = (java.util.List) query.list();
			
			for (Iterator iterator = ((java.util.List) results).iterator(); iterator.hasNext();) {
				product = ((Job) iterator.next());
				jobs.add(product);
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
		return jobs;
	}
	
	public List<JobSkills> skillsParser(Job job)
	{
		List<JobSkills> skills = new ArrayList<JobSkills>();
		try {
			JSONArray skillsArray = new JSONArray(job.getSkillsArray());
			
			for(int i=0; i<skillsArray.length();i++)
			{
				JSONObject skillsObject = skillsArray.getJSONObject(i);
				JobSkills skill = new JobSkills(skillsObject.getInt("category_id"), skillsObject.getInt("skill_id"), 
						skillsObject.getString("skill"));
				skills.add(skill);
			}
		} catch (JSONException e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
			}
			error("JSON exception: " + e.getMessage());
		}
		return skills;
	*/
	
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
