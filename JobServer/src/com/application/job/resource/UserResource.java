package com.application.job.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.bson.types.ObjectId;

import com.application.job.controller.BaseDao;
import com.application.job.model.entity.Skill;
import com.application.job.model.entity.User;
import com.application.job.model.pojo.Education;
import com.application.job.util.JsonUtil;

@Path("/user")
public class UserResource extends BaseResource {
	
	public static final String LOGGER = "UserResource.class";

	public UserResource() {
		super(UserResource.LOGGER);
	}
	
	@Path("/addEducation")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String getUser(@FormParam("user_id") String userId)
	{
		BaseDao dao = new BaseDao();
		
		User user = dao.get(User.class, new ObjectId(userId));
		
		return JsonUtil.jsonObject(user).toString();
	}
	
	@Path("/addSkill")
    @POST
    @Produces("application/json")
	@Consumes("application/json")
	public String addSkills(List<Skill> skills, @QueryParam("user_id") String userId)
	{
		BaseDao dao = new BaseDao();
		User user = dao.addSet(User.class, Skill.class, new ObjectId(userId), skills, "skills");
		
		return JsonUtil.jsonObject(user).toString();
	}
	
	@Path("/addEducation")
    @POST
    @Produces("application/json")
	@Consumes("application/json")
	public String addEducation(List<Education> education, @QueryParam("user_id") String userId)
	{
		BaseDao dao = new BaseDao();
		
		User user = dao.addObjectSet(User.class, Education.class, new ObjectId(userId), education, "education");
		
		return JsonUtil.jsonObject(user).toString();
	}
}
