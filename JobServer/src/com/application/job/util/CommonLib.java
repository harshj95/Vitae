package com.application.job.util;

import java.io.InputStream;
import java.util.UUID;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.application.job.model.pojo.Level;
import com.application.job.util.exception.ZException;

public class CommonLib {

	public static final int RESPONSE_FAILURE = 200;
	public static final int RESPONSE_SUCCESS = 201;
	public static final int RESPONSE_INVALID_USER = 202;
	public static final int RESPONSE_INVALID_PARAMS = 203;
	public static final int RESPONSE_INVALID_APP_TYPE = 204;
	public static final int RESPONSE_INVALID_CLIENT_ID = 205;
	public static final int RESPONSE_INVALID_MERCHANT = 206;
	public static final int RESPONSE_NO_ERROR_CODE = 0;

	public static JSONObject getResponseString(String responseJson, String errorMessage, int status) {
		JSONObject responseObject = new JSONObject();
		switch (status) {
		case RESPONSE_SUCCESS:
			try {
				responseObject.put("response", responseJson);
				responseObject.put("status", "success");
				responseObject.put("errorCode", RESPONSE_NO_ERROR_CODE);
				responseObject.put("errorMessage", "");
			} catch (JSONException e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			}
			break;

		case RESPONSE_INVALID_USER:
			try {
				responseObject.put("response", responseJson);
				responseObject.put("status", "failure");
				responseObject.put("errorCode", RESPONSE_INVALID_USER);
				responseObject.put("errorMessage", errorMessage);
			} catch (JSONException e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			}
			break;
		case RESPONSE_INVALID_PARAMS:
			try {
				responseObject.put("response", responseJson);
				responseObject.put("status", "failure");
				responseObject.put("errorCode", RESPONSE_INVALID_PARAMS);
				responseObject.put("errorMessage", errorMessage);
			} catch (JSONException e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			}
			break;
		case RESPONSE_INVALID_APP_TYPE:
			try {
				responseObject.put("response", responseJson);
				responseObject.put("status", "failure");
				responseObject.put("errorCode", RESPONSE_INVALID_APP_TYPE);
				responseObject.put("errorMessage", errorMessage);
			} catch (JSONException e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			}
			break;
		case RESPONSE_INVALID_CLIENT_ID:
			try {
				responseObject.put("response", responseJson);
				responseObject.put("status", "failure");
				responseObject.put("errorCode", RESPONSE_INVALID_CLIENT_ID);
				responseObject.put("errorMessage", errorMessage);
			} catch (JSONException e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			}
			break;
		case RESPONSE_FAILURE:
			try {
				responseObject.put("response", responseJson);
				responseObject.put("status", "failure");
				responseObject.put("errorCode", RESPONSE_FAILURE);
				responseObject.put("errorMessage", errorMessage);
			} catch (JSONException e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			}
			break;
		default:
			break;
		}

		return responseObject;
	}
	
	public Level getLevel(int levelCode)
	{
		Level level = new Level();
		
		switch(levelCode)
		{
		case 1: 
			level.setLevelId(levelCode);
			level.setLevel("Unpaid");
			break;
			
		case 2:
			level.setLevelId(levelCode);
			level.setLevel("Training");
			break;
			
		case 3:
			level.setLevelId(levelCode);
			level.setLevel("Entry-level");
			break;
			
		case 4:
			level.setLevelId(levelCode);
			level.setLevel("Senior");
			break;
			
		case 5:
			level.setLevelId(levelCode);
			level.setLevel("Manager");
			break;
			
		case 6:
			level.setLevelId(levelCode);
			level.setLevel("Director");
			break;
			
		case 7:
			level.setLevelId(levelCode);
			level.setLevel("Vice President (VP)");
			break;
			
		case 8:
			level.setLevelId(levelCode);
			level.setLevel("Chief Officer (CO)");
			break;
			
		case 9:
			level.setLevelId(levelCode);
			level.setLevel("Partner");
			break;
			
		case 10:
			level.setLevelId(levelCode);
			level.setLevel("Owner");
			break;
		}
		
		return level;
	}

	public static String getUserKey() {
		String key = "null";
		try {
			key = UUID.randomUUID().toString().substring(0, 5);
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
			}
		}
		return key;
	}

	public static String getMerchantPin() {
		String pin = "null";
		try {
			pin = UUID.randomUUID().toString().substring(0, 5);
		} catch (Exception e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
			}
		}
		return pin;
	}

	public static final String otpText = " is your One Time Password";

	public static String getOTP() {
		return (int) (Math.random() * 5000) + 1000 + "";
	}
	
	/*
	public static String getUserName(User user) {
		if (user == null)
			return "";
		String userName = "";
		if (user.getUserName() == null || user.getUserName().equals("") || user.getUserName().equals("null")) {
			try {
				JSONObject data = new JSONObject(user.getFacebookData());
				if (data.has("name")) {
					String name = String.valueOf(data.get("name"));
					name = name.split(" ")[0];
					userName = name;
				}
			} catch (JSONException e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			String name = user.getUserName();
			name = name.split(" ")[0];
			userName = name;
		}
		return userName;
	}
	*/

	
	/** Method used to read contents of html/text/XML file */
	public static String readFile(InputStream stream) {
		// NB: does not close inputStream, you can use IOUtils.closeQuietly for
		// that
		java.util.Scanner s = new java.util.Scanner(stream).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";

	}

	public static Double deg2rad(Double deg) {
		return deg * (Math.PI / 180);
	}

	public static double getDistanceFromLatLong(double startLatitude, double startLongitude, double endLatitude,
			double endLongitude) {

		double latitudeDiff = deg2rad(Math.abs(endLatitude - startLatitude));
		double longitudeDiff = deg2rad(Math.abs(endLongitude - startLongitude));

		double A = (Math.pow(Math.sin(latitudeDiff / 2), 2) + Math.cos(deg2rad(startLatitude))
				* Math.cos(deg2rad(endLatitude)) * Math.pow(Math.sin(longitudeDiff / 2), 2));

		double C = 2 * Math.atan2(Math.sqrt(A), Math.sqrt(1 - A));

		double distance = 6371 * C;

		return distance;
	}
}
