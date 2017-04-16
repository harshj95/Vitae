package com.application.job.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.tomcat.util.codec.binary.Base64;

import com.application.job.model.entity.User;
import com.application.job.controller.BaseDao;
import com.application.job.controller.SessionDao;
import com.application.job.resource.UserSession;
import com.application.job.util.CommonLib;
import com.application.job.util.Constants;
import com.application.job.util.CryptoHelper;
import com.application.job.util.exception.ZException;
import com.application.job.controller.UserDao;

@Path("/auth")
public class UserSession extends BaseResource {
	
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
    public String login (@FormParam("email") String email, @FormParam("user_name") String userName, 
    		@FormParam("phone") String phone, @FormParam("password") String password)
    {
    	boolean sessionAdded = false;
    	
    	if ((email == null || email.isEmpty()) || (password == null || password.isEmpty()))
    	{
    		return CommonLib.getResponseString("Parameter error", "", CommonLib.RESPONSE_INVALID_PARAMS).toString();
    	}
    	
    	UserDao userDao = new UserDao();
    	BaseDao dao = new BaseDao();
		User user = null;
		String accessToken;
		
		CryptoHelper cryptoHelper = new CryptoHelper();
		if (password != null) {
			try {
				password = cryptoHelper.encrypt(password, null, null);
			} catch (Exception e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			}
		}
    	
    	if (userName != null && !userName.isEmpty())
    	{
			user = userDao.getUserDetailsFromEmail(email);
			if (user != null) 
			{
				if(user.getStatus() == Constants.STATUS_ACTIVE)
				{
					return CommonLib.getResponseString("Account already exists.", "Invalid signup credentials",
							CommonLib.RESPONSE_INVALID_PARAMS).toString();
				}
				else
				{
					boolean session;
					SessionDao userSessionDao = new SessionDao();
					
					String keySource = email + userName + password + String.valueOf(System.currentTimeMillis() * 1000)
					+ String.valueOf((int) (Math.random() * 1000 * 1000));
					byte[] tokenByte = Base64.encodeBase64(keySource.getBytes());
					accessToken = new String(tokenByte);
					
					session = userSessionDao.addSession(user.getId(), accessToken, "","");
					dao.updateField(User.class, user.getId(), "status", Constants.STATUS_ACTIVE);
					
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
		
		String keySource = email + userName + password + String.valueOf(System.currentTimeMillis() * 1000)
		+ String.valueOf((int) (Math.random() * 1000 * 1000));
		byte[] tokenByte = Base64.encodeBase64(keySource.getBytes());
		accessToken = new String(tokenByte);
    	
    	user = userDao.getUserDetails(email, accessToken);
    	
    	if (user == null)
    	{
    		User userToAdd = new User();
    		userToAdd.setUserName(userName);
    		userToAdd.setEmail(email);
    		userToAdd.setPassword(password);
    		userToAdd.setPhone(phone);
    		userToAdd.setVerified(0);
    		userToAdd.setEmailVerified(0);
    		userToAdd.setInstalled(1);
            
            user = dao.add(userToAdd);
    	}
    	
    	if (user == null || user.getId() == null)
    	{
    		return CommonLib.getResponseString("Error.", "Some error occured.", CommonLib.RESPONSE_INVALID_PARAMS).toString();    		
    	}
    	SessionDao userSessionDao = new SessionDao();
    	
    	int status = CommonLib.RESPONSE_SUCCESS;
    	
    	if(accessToken!= null && !accessToken.isEmpty())
    	{
    		sessionAdded = userSessionDao.addSession(user.getId(), accessToken, "", "");
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
		
		if(user==null || user.getId() == null)
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

		if (user != null && user.getId()!= null) {
			SessionDao sessionDao = new SessionDao();
			returnValue = sessionDao.nullifyAccessToken(user.getId(), accessToken);
		}

		if (accessToken != null && !returnValue)
			return CommonLib.getResponseString("Already logged out", "user already logged out", CommonLib.RESPONSE_INVALID_PARAMS).toString();

		return CommonLib.getResponseString("Logged out successfully", "", CommonLib.RESPONSE_SUCCESS).toString();
	}
}
