package com.application.job.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.bson.types.ObjectId;

import com.application.job.controller.BaseDao;
import com.application.job.controller.SkillDao;
import com.application.job.model.entity.Category;
import com.application.job.model.entity.Skill;
import com.application.job.util.CommonLib;

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
		skill = new Skill(skillName);
		
		dao.add(skill);
		
		return CommonLib.getResponseString(skill.getSkillName()+" Added", "", CommonLib.RESPONSE_SUCCESS).toString();
	}
	
	@Path("/category")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String addToCategory(@FormParam("category_id") String categoryId, @FormParam("skills_id") String skillId)
	{
		SkillDao skillDao = new SkillDao();
		Category category = null;
		
		category = skillDao.addToCategory(new ObjectId(categoryId), new ObjectId(skillId));
		
		return category.toString();
	}
}
