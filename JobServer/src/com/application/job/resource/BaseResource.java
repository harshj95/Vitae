package com.application.job.resource;

import org.apache.log4j.BasicConfigurator;

import com.application.job.util.CommonLib;

/**
 * Base class for all resource classes. Includes Log4j logger.
 */
public class BaseResource {

	// Logger object

	@SuppressWarnings("unused")
	public BaseResource(String classObj) {
		if (CommonLib.ZLOG && classObj != null) {
			BasicConfigurator.configure();
		}
	}

	/*public String clientCheck(String clientId, String appType) {
		String retValue = "Invalid params";

		// null checks, invalid request
		if (clientId == null || appType == null)
			return retValue;

		// check for client_id
		if (!(clientId.equals(CommonLib.ANDROID_CLIENT_ID) || clientId.equals(CommonLib.WEB_CLIENT_ID)))
			retValue = "Invalid client id";

		// check for app type if
		if (!(appType.equals(CommonLib.ANDROID_APP_TYPE) || appType.equals(CommonLib.WEB_APP_TYPE)))
			retValue = "Invalid app type";

		if (retValue.equals("Invalid app type") || retValue.equals("Invalid client id")) {
			ConsumerDao consumerDao = new ConsumerDao();
			boolean clientCheck = consumerDao.checkClient(clientId, appType);
			if (clientCheck)
				return "success";
			else
				return retValue;
		} else
			return "success";
	}*/

	public void debug(Object message) {
	}

	public void info(Object message) {
	}

	public void error(Object message) {
	}

	public void fatal(Object message) {
	}
}
