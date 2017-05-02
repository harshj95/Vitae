package com.application.job.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.codehaus.jettison.json.JSONException;

import com.application.job.controller.BaseDao;
import com.application.job.model.entity.Skill;
import com.application.job.util.CommonLib;
import com.application.job.util.JsonUtil;

@Path("/skills")
public class SkillResource extends BaseResource {
	
	public static final String LOGGER = "SkillResource.class";

	public SkillResource() {
		super(SkillResource.LOGGER);
	}
	
	@Path("/add")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String addSkill(@FormParam("skill_name") String skillName)
	{
		BaseDao dao = new BaseDao();
		Skill skill = null;
		skill = new Skill();
		skill.setSkillName(skillName);
		
		dao.add(skill);
		
		return CommonLib.getResponseString(skill.getSkillName()+" Added", "", CommonLib.RESPONSE_SUCCESS).toString();
	}
	
	@Path("/addSkills")
    @POST
    @Produces("application/json")
	@Consumes("application/json")
	public String addSkills(List<Skill> skills) throws JSONException
	{
		BaseDao dao = new BaseDao();
		
		for(Skill skill : skills)
		{
			dao.add(skill);
		}
		
		return JsonUtil.objectArray(skills).toString();
	}
}
