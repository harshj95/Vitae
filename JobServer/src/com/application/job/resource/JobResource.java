package com.application.job.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.application.job.controller.JobDao;
import com.application.job.model.Job;
import com.application.job.util.CommonLib;
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
    public String addJob(@FormParam("skills_array") String skillsArray, @FormParam("salary") float salary) throws JSONException
    {
		JobDao jobDao = new JobDao();
		Job job = null;
		
		Job jobToAdd = new Job();
		jobToAdd.setCompanyId(1);
		jobToAdd.setCompanyName("abc");
		jobToAdd.setSkillsArray(skillsArray);
		jobToAdd.setSalary(salary);
		
		job = jobDao.addJob(jobToAdd);
		
		return CommonLib.getResponseString("Successfully logged in.", "", CommonLib.RESPONSE_SUCCESS).toString();
    }
	
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
		
		return array.toString();
	}
	
	@Path("tfIdf")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String tfIdf(@FormParam("skill") String skill, @FormParam("company") int company) throws JSONException
	{
		JobDao jobDao = new JobDao();
		List<Job> jobs = jobDao.getProductById(company);
		
		double[] tfs = new double[jobs.size()+1];
		int i=0;
		
		for(Job job : jobs)
		{
			tfs[i] = TfIdf.tfCalculator(jobDao.skillsParser(job), skill);
			i++;
		}
		
		double idf = TfIdf.idfCalculcator(jobDao.jobParser(jobs), skill);
		String ret="";
		for(int j=0;j<i;j++)
		{
			double tfIdf = tfs[j]*idf;
			ret = ret.concat(" + ").concat(String.valueOf(tfIdf));
		}
			
		return CommonLib.getResponseString(String.valueOf(ret), "", CommonLib.RESPONSE_SUCCESS).toString();
	}
}
