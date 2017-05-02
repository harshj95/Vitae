package com.application.job.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.application.job.controller.BaseDao;
import com.application.job.model.entity.Institution;
import com.application.job.util.CommonLib;

@Path("/institution")
public class InstituionResource extends BaseResource {
	
	public static final String LOGGER = "InstituionResource.class";

	public InstituionResource() {
		super(InstituionResource.LOGGER);
	}
	
	@Path("/add")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String addInstitution(@FormParam("institution_name") String institutionName, @FormParam("level") int level)
	{
		BaseDao dao = new BaseDao();
		Institution institution = new Institution(institutionName, level, CommonLib.getInstitutionLevel(level));
		
		dao.add(institution);
		
		return CommonLib.getResponseString(institution.getInstitutionName()+" Added", "", CommonLib.RESPONSE_SUCCESS).toString();
	}
}
