package com.application.job.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.bson.types.ObjectId;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.application.job.controller.BaseDao;
import com.application.job.controller.FieldDao;
import com.application.job.model.entity.Field;
import com.application.job.util.CommonLib;

@Path("/fields")
public class FieldResource extends BaseResource {
	
	public static final String LOGGER = "FieldResource.class";

	public FieldResource() {
		super(FieldResource.LOGGER);
	}
	
	@Path("/add")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String addField(@FormParam("field_name") String fieldName)
	{
		BaseDao dao = new BaseDao();
		Field field = null;
		
		field = new Field(101, fieldName, null);
		
		dao.add(field);
		
		return CommonLib.getResponseString(field.getFieldName()+" Added", "", CommonLib.RESPONSE_SUCCESS).toString();
	}
	
	@Path("/category")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String addCategory(@FormParam("field_id") String fieldId, @FormParam("category_id") String categoryId) throws JSONException
	{
		FieldDao fieldDao = new FieldDao();
		JSONObject object = new JSONObject();
		object.put("field", fieldDao.addToField(new ObjectId(fieldId), new ObjectId(categoryId)));
		
		return object.toString();
	}
}
