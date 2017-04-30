package com.application.job.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.bson.types.ObjectId;
import org.codehaus.jettison.json.JSONException;

import com.application.job.controller.BaseDao;
import com.application.job.model.entity.Category;
import com.application.job.model.entity.Industry;
import com.application.job.util.CommonLib;
import com.application.job.util.JsonUtil;

@Path("/industry")
public class IndustryResource extends BaseResource {
	
	public static final String LOGGER = "IndustryResource.class";

	public IndustryResource() {
		super(IndustryResource.LOGGER);
	}
	
	@Path("/add")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String addIndustry(@FormParam("industry_id") int industryId, @FormParam("industry_code") String industryCode,
			@FormParam("industry_name") String industryName)
	{
		BaseDao dao = new BaseDao();
		Industry industry = null;
		
		industry = new Industry();
		industry.setIndustryId(industryId);
		industry.setIndustryCode(industryCode);
		industry.setIndustryName(industryName);
		
		dao.add(industry);
		
		return CommonLib.getResponseString(industry.getIndustryName()+" Added", "", CommonLib.RESPONSE_SUCCESS).toString();
	}
	
	@Path("/addCategory")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String addCategory(@FormParam("industry_id") String industry_id, @FormParam("category_id") String categoryId) throws JSONException
	{
		BaseDao dao = new BaseDao();
		Industry industry = null;
		
		industry = dao.addToSet(Industry.class, Category.class, new ObjectId(industry_id), new ObjectId(categoryId), "categories");
		
		return JsonUtil.jsonObject(industry).toString();
	}
}
