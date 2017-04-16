package com.application.job.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.application.job.controller.BaseDao;
import com.application.job.model.entity.Company;
import com.application.job.util.CommonLib;

@Path("/company")
public class CompanyResource extends BaseResource {

	public static final String LOGGER = "CompanyResource.class";

	public CompanyResource() {
		super(CompanyResource.LOGGER);
	}
	
	@Path("/add")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String addCompany(@FormParam("company_name") String companyName, 
			@FormParam("description") String description, @FormParam("url") String url)
	{
		BaseDao dao = new BaseDao();
		Company company = new Company(companyName, description, url, null);
		
		dao.add(company);
		
		return CommonLib.getResponseString(company.getCompanyName()+" Added", "", CommonLib.RESPONSE_SUCCESS).toString();
	}
}
