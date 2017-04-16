package com.application.job.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.bson.types.ObjectId;

import com.application.job.controller.BaseDao;
import com.application.job.model.entity.Skill;
import com.application.job.model.entity.User;
import com.application.job.util.JsonUtil;

@Path("/user")
public class UserResource extends BaseResource {
	
	public static final String LOGGER = "UserResource.class";

	public UserResource() {
		super(UserResource.LOGGER);
	}
	
	@Path("/addSkill")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String addCategory(@FormParam("user_id") String jobId, @FormParam("skills_id") String skillId)
	{
		BaseDao dao = new BaseDao();
		User user = dao.addToSet(User.class, Skill.class, new ObjectId(jobId), new ObjectId(skillId), "skills");
		
		return JsonUtil.jsonObject(user).toString();
	}
}
