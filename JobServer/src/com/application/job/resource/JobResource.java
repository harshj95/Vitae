package com.application.job.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.bson.types.ObjectId;
import org.codehaus.jettison.json.JSONException;

import com.application.job.controller.BaseDao;
import com.application.job.controller.JobDao;
import com.application.job.model.entity.Job;
import com.application.job.model.entity.Skill;
import com.application.job.model.entity.User;
import com.application.job.model.pojo.JobModel;
import com.application.job.model.pojo.JobSkills;
import com.application.job.util.CommonLib;
import com.application.job.util.JobCompare;
import com.application.job.util.JsonUtil;
import com.application.job.util.TfIdf;

@Path("/job")
public class JobResource extends BaseResource{
	
	public static final String LOGGER = "UserSession.class";

	public JobResource() {
		super(JobResource.LOGGER);
	}
	
	@Path("/add")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
    public String addJob(@FormParam("companyName") String companyname, @FormParam("salary") float salary)
    {
		BaseDao dao = new BaseDao();
		
		Job jobToAdd = new Job(null, null, salary, null, companyname);
		dao.add(jobToAdd);
		
		return CommonLib.getResponseString(jobToAdd.getId().toString() + "Added", "", CommonLib.RESPONSE_SUCCESS).toString();
    }
	
	@Path("/skills")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String addSkill(@FormParam("job_id") String jobId, @FormParam("skill_id") String skillId)
	{
		BaseDao dao = new BaseDao();
		Job job = null;
		
		job = dao.addToSet(Job.class, Skill.class, new ObjectId(jobId), new ObjectId(skillId), "skills");
		
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
		JobDao jobDao = new JobDao();
		BaseDao dao  = new BaseDao();
		List<Job> jobs = dao.getAll(Job.class);
		User user = dao.get(User.class, new ObjectId(userId));
		
		List<JobModel> JOBS = new ArrayList<JobModel>();
		
		double idf;
		
		List<Skill> skills = user.getSkills();
		
		for(Skill skill : skills)
		{
			int i=0;
			
			for(Job job : jobs)
			{
				List<JobSkills> jobSkills = new ArrayList<JobSkills>();
				List<Skill> SKILLS = job.getSkills();
				JobModel JOB = new JobModel();
				
				
				for(Skill SKILL : SKILLS)
				{
					JobSkills jobSkill = new JobSkills();
					jobSkill.setSkill(SKILL);
					if(SKILL.getSkillName().equalsIgnoreCase(skill.getSkillName()))
					{
						jobSkill.setTf(1);
					}
					else
					{
						jobSkill.setTf(0);
					}
					jobSkills.add(jobSkill);
				}
				JOB.setJob(job);
				JOB.setSkills(jobSkills);
				idf = TfIdf.idfCalculcator(jobDao.jobParser(jobs), skill.getSkillName());
				JOB.setIdf(idf);
				
				JOBS.add(JOB);
			}
			
			for(JobModel finalJob : JOBS)
			{
				double a = 0;
				for(JobSkills JOBSKILL : finalJob.getSkills())
				{
					a+=JOBSKILL.getTf()*finalJob.getIdf();
				}
				finalJob.setFactor(a);
			}
			
		}
		
		Collections.sort(JOBS, new JobCompare());
		
		return JsonUtil.objectArray(JOBS).toString();
	}
	
}
