package com.application.job.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.codehaus.jettison.json.JSONException;

import com.application.job.controller.BaseDao;
import com.application.job.model.entity.Category;
import com.application.job.util.CommonLib;
import com.application.job.util.JsonUtil;

@Path("/category")
public class CategoryResource extends BaseResource {
	
	public static final String LOGGER = "CategoryResource.class";

	public CategoryResource() {
		super(CategoryResource.LOGGER);
	}
	
	@Path("/change")
    @POST
    @Produces("application/json")
	@Consumes("application/json")
	public String sort(List<Category> categories) throws JSONException
	{
		for(Category category : categories)
		{
			new BaseDao().add(category);
		}
		
		return JsonUtil.objectArray(categories).toString();
	}
	
	@Path("/add")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String addCategory(@FormParam("category_id") int categoryId, @FormParam("category_name") String categoryName)
	{
		BaseDao dao = new BaseDao();
		
		Category category = new Category();
		category.setCategoryId(categoryId);
		category.setCategoryName(categoryName);
		
		dao.add(category);
		
		return CommonLib.getResponseString(category.getId()+" Added", "", CommonLib.RESPONSE_SUCCESS).toString();
	}
}
