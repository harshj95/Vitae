package com.application.job.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.application.job.model.User;
import com.application.job.controller.UserSessionDao;
import com.application.job.resource.UserSession;
import com.application.job.util.CommonLib;

import com.application.job.controller.UserDao;

@Path("/auth")
public class UserSession extends BaseResource{
	
	public static final String LOGGER = "UserSession.class";

	public UserSession() {
		super(UserSession.LOGGER);
	}
	
	@Path("/ret")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String gotIt()
	{
		return "success";
	}

	/**
	 * Operations - User login, Add a new user,
	 * Generates a session for the user
	 * 
	 * @author Harsh
	 */	
    @Path("/login")
    @POST
    @Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
    public String login(@FormParam("email") String email, @FormParam("user_name") String userName, 
    		@FormParam("access_token") String accessToken)
    {
    	boolean sessionAdded = false;
    	
    	if ((email == null || email.isEmpty()) || (accessToken == null || accessToken.isEmpty()))
    	{
    		return CommonLib.getResponseString("Parameter error", "", CommonLib.RESPONSE_INVALID_PARAMS).toString();
    	}
    	
    	UserDao userDao = new UserDao();
		User user = null;
		
    	
    	if (userName != null && !userName.isEmpty())
    	{
			user = userDao.getUserDetailsFromEmail(email);
			if (user != null) 
			{
				if(user.getActive() == 1)
				{
					return CommonLib.getResponseString("Account already exists.", "Invalid signup credentials",
							CommonLib.RESPONSE_INVALID_PARAMS).toString();
				}
				else
				{
					boolean session;
					UserSessionDao userSessionDao = new UserSessionDao();
					session = userSessionDao.addSession(user.getUserId(), accessToken, "");
					user.setActive(1);
					user.setModified(System.currentTimeMillis());
					userDao.updateUserDetails(user);
					
					if(session)
					{						
						return CommonLib.getResponseString("Successfully logged in.", "", CommonLib.RESPONSE_SUCCESS).toString();    		
			    	}
			    	else
			    	{
			    		return CommonLib.getResponseString("Error", "", CommonLib.RESPONSE_SUCCESS).toString();
			    	}
				}
			}
		}
    	
    	user = userDao.getUserDetails(email, accessToken);
    	
    	if (user == null || user.getUserId() <= 0)
    	{
    		User userToAdd = new User();
    		userToAdd.setEmail(email);		
            userToAdd.setUserName(userName);
            userToAdd.setActive(1);
            userToAdd.setInstalled(1);
            userToAdd.setCreated(System.currentTimeMillis());
            userToAdd.setModified(0);
            
            user = userDao.addUserDetails(userToAdd);
    	}
    	
    	if (user == null || user.getUserId() <= 0)
    	{
    		return CommonLib.getResponseString("Error.", "Some error occured.", CommonLib.RESPONSE_INVALID_PARAMS).toString();    		
    	}
    	UserSessionDao userSessionDao = new UserSessionDao();
    	
    	int status = CommonLib.RESPONSE_SUCCESS;
    	
    	if(accessToken!= null && !accessToken.isEmpty())
    	{
    		sessionAdded = userSessionDao.addSession(user.getUserId(), accessToken, "");
    	}
    	
    	if(sessionAdded==true)
    	{
    		return CommonLib.getResponseString("Successfully logged in.", "", status).toString();    		
    	}
    	else
    	{
    		return CommonLib.getResponseString("Error", "", status).toString();
    	}
    }
    
    
    /**
	 * Operations - User check
	 * 
	 * @author Harsh
	 */
    
    @Path("/check")
    @POST
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public String check(@FormParam("email") String email, 
    		@FormParam("access_token") String accessToken)
    {
    	if ((email == null || email.isEmpty()) || (accessToken==null || accessToken.isEmpty()))
    	{
    		return CommonLib.getResponseString("Invalid parameters", "", CommonLib.RESPONSE_INVALID_PARAMS).toString();
    	}
    	
    	UserDao userDao = new UserDao();
		User user = null;
		
		user = userDao.getUserDetails(email, accessToken);
		
		if(user==null || user.getUserId()<=0)
		{
			return CommonLib.getResponseString("LoggedOut", "", CommonLib.RESPONSE_SUCCESS).toString();
		}
		else
		{
			return CommonLib.getResponseString("LoggedIn", "", CommonLib.RESPONSE_SUCCESS).toString();
		}    	
    }
    
    /**
	 * Operations - User logout,
	 * Delete a session for the user
	 * 
	 * @author Harsh
	 */
    
    @Path("/logout")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String userLogout(@FormParam("access_token") String accessToken) {

		UserDao userDao = new UserDao();
		User user = userDao.userActive(accessToken);
		boolean returnValue = false;

		if (user != null && user.getUserId() > 0) {
			UserSessionDao sessionDao = new UserSessionDao();
			returnValue = sessionDao.nullifyAccessToken(user.getUserId(), accessToken);
			user.setActive(0);
			user.setModified(System.currentTimeMillis());
			user = userDao.updateUserDetails(user);
		}

		if (accessToken != null && !returnValue)
			return CommonLib.getResponseString("Already logged out", "user already logged out", CommonLib.RESPONSE_INVALID_PARAMS).toString();

		return CommonLib.getResponseString("Logged out successfully", "", CommonLib.RESPONSE_SUCCESS).toString();
	}
}
