package com.application.job.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.bson.types.ObjectId;

import com.application.job.controller.BaseDao;
import com.application.job.model.entity.Category;
import com.application.job.model.entity.Skill;
import com.application.job.util.CommonLib;

@Path("/categories")
public class CategoryResource extends BaseResource {
	
	public static final String LOGGER = "CategoryResource.class";

	public CategoryResource() {
		super(CategoryResource.LOGGER);
	}
	
	@Path("/add")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String addCategory(@FormParam("category_name") String categoryName)
	{
		BaseDao dao = new BaseDao();
		
		Category category = new Category(categoryName, null);
		
		dao.add(category);
		
		return CommonLib.getResponseString(category.getId()+" Added", "", CommonLib.RESPONSE_SUCCESS).toString();
	}
	
	@Path("/addSkill")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String addToCategory(@FormParam("category_id") String categoryId, @FormParam("skill_id") String skillId)
	{
		BaseDao dao = new BaseDao();
		Category category = null;
		
		category = dao.addToSet(Category.class, Skill.class, new ObjectId(categoryId), new ObjectId(skillId), "skills");
		
		return category.toString();
	}
}
