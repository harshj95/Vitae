package com.application.job.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;
import org.codehaus.jettison.json.JSONException;

import com.application.job.controller.BaseDao;
import com.application.job.controller.IndustryDao;
import com.application.job.controller.JobDao;
import com.application.job.model.entity.Company;
import com.application.job.model.entity.Job;
import com.application.job.model.entity.Skill;
import com.application.job.model.entity.User;
import com.application.job.model.pojo.IndustryModel;
import com.application.job.model.pojo.JobModel;
import com.application.job.model.pojo.SkillModel;
import com.application.job.model.pojo.UserSkill;
import com.application.job.util.CommonLib;
import com.application.job.util.JobCompare;
import com.application.job.util.JsonUtil;
import com.application.job.util.TfIdf;

@Path("/jobs")
public class JobResource extends BaseResource{
	
	public static final String LOGGER = "UserSession.class";

	public JobResource() {
		super(JobResource.LOGGER);
	}
	
	@Path("/ret")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes("application/x-www-form-urlencoded")
	public String check(@FormParam("user_id") String userId)
	{
		BaseDao dao = new BaseDao();
		return String.valueOf(dao.get(User.class, new ObjectId(userId)).getIndustry().getIndustryId());
	}
	
	@Path("/addJob")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
    public String addJob(@FormParam("company_id") String companyId,@FormParam("industry_id") int industryId,
    		@FormParam("designation") String designation, @FormParam("description") String description, 
    		@FormParam("salary") float salary)
    {
		BaseDao dao = new BaseDao();
		IndustryDao industryDao = new IndustryDao();
		Company company = dao.get(Company.class, new ObjectId(companyId));
		Job job = null;
		
		Job jobToAdd = new Job();
		jobToAdd.setCompany(company.getCompanyName());
		jobToAdd.setIndustry(new IndustryModel(industryId, industryDao.getById(industryId).getIndustryName()));
		jobToAdd.setDesignation(designation);
		jobToAdd.setDescription(description);
		jobToAdd.setSalary(salary);
		
		job = dao.add(jobToAdd);
		dao.addToSet(Company.class, Job.class, new ObjectId(companyId), job.getId(), "jobs");
		
		return CommonLib.getResponseString(jobToAdd.getId().toString() + "Added", "", CommonLib.RESPONSE_SUCCESS).toString();
    }
	
	@Path("/add")
    @POST
    @Produces("application/json")
	@Consumes("application/json")
    public String add(Job job)
    {
		BaseDao dao = new BaseDao();
		
		dao.add(job);
		
		return CommonLib.getResponseString(job.getId().toString() + "Added", "", CommonLib.RESPONSE_SUCCESS).toString();
    }
	
	@Path("/addSkill")
    @POST
    @Produces("application/json")
	@Consumes("application/json")
	public String addSkill(List<Skill> skills, @QueryParam("job_id") String jobId)
	{
		BaseDao dao = new BaseDao();
		Job job = null;
		
		List<SkillModel> Skills = new ArrayList<SkillModel>();
		
		for(Skill skill : skills)
		{
			Skills.add(new SkillModel(skill));
		}
		
		job = dao.addObjectSet(Job.class, SkillModel.class, new ObjectId(jobId), Skills, "skills");
		
		return JsonUtil.jsonObject(job).toString();
	}
	
	@Path("/all")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String all() throws JSONException
	{
		BaseDao dao = new BaseDao();
		
		return JsonUtil.objectArray(dao.getAll(Job.class)).toString();
	}
	
	@Path("tfIdf")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String tfIdf(@FormParam("user_id") String userId) throws JSONException
	{
		BaseDao dao  = new BaseDao();
		User user = dao.get(User.class, new ObjectId(userId));
		List<Job> jobs = new JobDao().getJobsById(user.getIndustry().getIndustryId());
		
		List<JobModel> JOBS = new ArrayList<JobModel>();
		
		double idf = 0;
		
		List<SkillModel> skills = user.getSkills();
		
		for(Job job : jobs)
		{
			List<UserSkill> jobSkills = new ArrayList<UserSkill>();
			JobModel JOB = new JobModel();
			List<SkillModel> SKILLS = job.getSkills();
			
			for(SkillModel skill : skills)
			{
				UserSkill jobSkill = new UserSkill();
				jobSkill.setSkill(skill);
				double tf = 0;
				
				for(SkillModel SKILL : SKILLS)
				{
					if(SKILL.getSkillName().equalsIgnoreCase(skill.getSkillName()))
					{
						tf++;
					}
				}
				
				jobSkill.setTf(tf);
				idf = TfIdf.idfCalculcator(jobs, skill.getSkillName());
				jobSkill.setIdf(idf);
				jobSkill.setTfIdf(tf*idf);
				jobSkills.add(jobSkill);
			}
			
			JOB.setJob(job);
			JOB.setSkills(jobSkills);
			
			JOBS.add(JOB);
			
			for(JobModel finalJob : JOBS)
			{
				double a = 0;
				for(UserSkill JOBSKILL : finalJob.getSkills())
				{
					a+=JOBSKILL.getTfIdf();
				}
				
				finalJob.setFactor(a/finalJob.getJob().getSkills().size());
			}
		}
		
		Collections.sort(JOBS, new JobCompare());
		
		return JsonUtil.objectArray(JOBS).toString();
	}
	
}
