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
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.application.job.controller.BaseDao;
import com.application.job.controller.FieldDao;
import com.application.job.controller.JobDao;
import com.application.job.model.entity.Job;
import com.application.job.model.entity.Skill;
import com.application.job.model.entity.User;
import com.application.job.model.pojo.JobModel;
import com.application.job.model.pojo.JobSkills;
import com.application.job.util.CommonLib;
import com.application.job.util.JobCompare;
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
    public String addJob(@FormParam("companyName") String companyname, @FormParam("salary") float salary) throws JSONException
    {
		BaseDao dao = new BaseDao();
		Job job = null;
		
		Job jobToAdd = new Job(null, null, salary, null, companyname);
		dao.add(jobToAdd);
		
		return CommonLib.getResponseString(jobToAdd.getId().toString() + "Added", "", CommonLib.RESPONSE_SUCCESS).toString();
    }
	
	@Path("/skills")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String addCategory(@FormParam("job_id") String jobId, @FormParam("skills_id") String skillId) throws JSONException
	{
		JobDao fieldDao = new JobDao();
		JSONObject object = new JSONObject();
		object.put("job", fieldDao.addToField(new ObjectId(jobId), new ObjectId(skillId)));
		
		return object.toString();
	}
	
	@Path("/all")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String all() throws JSONException
	{
//		JobDao fieldDao = new JobDao();
		BaseDao dao = new BaseDao();
		JSONObject object = new JSONObject();
		object.put("job", dao.getAll(Job.class));
		
		
		return object.toString();
	}
	/*
	@Path("/test")
	@POST
	@Produces("application/json")
	public String ret() throws JSONException
	{
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		JSONObject object1 = new JSONObject();
		
		object.put("category_id", 101);
		object.put("skill_id", 10101);
		object.put("skill", "JSON");
		
		object1.put("category_id", 101);
		object1.put("skill_id", 10102);
		object1.put("skill", "TEST");
		
		array.put(object);
		array.put(object1);
		
		return array.toString();f
	}
	*/
	
	@Path("tfIdf")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String tfIdf(@FormParam("user_id") String userId, @FormParam("company") int company) throws JSONException
	{
		JobDao jobDao = new JobDao();
		BaseDao dao  = new BaseDao();
		List<Job> jobs = dao.getAll(Job.class);
		User user = dao.get(User.class, new ObjectId(userId));
		
		List<JobModel> JOBS = new ArrayList<JobModel>();
		
		
		double[] tfs = new double[jobs.size()+1];
		double idf;
		String ret="";
		
		
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
		
		JSONObject object = new JSONObject();
		object.put("jobs", JOBS);
			
		return object.toString();
		//return CommonLib.getResponseString(object.toString(), "", CommonLib.RESPONSE_SUCCESS).toString();
	}
	
}
