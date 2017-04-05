package com.application.job.util;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/*
import com.application.zapplon.model.Address;
import com.application.zapplon.model.AppConfig;
import com.application.zapplon.model.C_Employee;
import com.application.zapplon.model.C_Group;
import com.application.zapplon.model.C_Packages;
import com.application.zapplon.model.C_Product;
import com.application.zapplon.model.OAuthSession;
import com.application.zapplon.model.OfflineAddress;
*/
import com.application.job.model.User;
/*
import com.application.zapplon.model.Voucher;
import com.application.zapplon.model.intercity.TaxiBookings;
import com.application.zapplon.model.intracity.Bookings;
import com.application.zapplon.model.intracity.Tracking;
import com.application.zapplon.model.pojo.intercity.IntercityCab;
import com.application.zapplon.model.pojo.intracity.IntracityCab;
import com.application.zapplon.model.pojo.intracity.IntracityCabPriceEstimates;
import com.application.zapplon.model.pojo.intracity.IntracityCabTimeEstimates;
import com.application.zapplon.model.pojo.intracity.Surcharge;
import com.application.zapplon.model.pojo.selfDrive.selfDriveCabDetails;
*/

public class JsonUtil {
	
	/*

	public static JSONObject getAppConfigJson(AppConfig appConfig) throws JSONException {
		if (appConfig == null)
			return null;
		JSONObject appConfigJson = new JSONObject();
		appConfigJson.put("key", appConfig.getAppKey());
		appConfigJson.put("value", appConfig.getAppValue());
		return appConfigJson;
	}

	public static JSONObject getAddressJson(Address address) throws JSONException {
		if (address == null)
			return null;
		JSONObject appConfigJson = new JSONObject();
		appConfigJson.put("latitude", address.getAddressLatitude());
		appConfigJson.put("longitude", address.getAddressLongitude());
		appConfigJson.put("type", address.getAddressType());
		appConfigJson.put("address", address.getAddress());
		return appConfigJson;
	}

	public static JSONObject getCabBookingDetailsJson(Bookings cabBookingDetails) throws JSONException {
		if (cabBookingDetails == null)
			return null;
		JSONObject cabBookingDetailsJson = new JSONObject();
		cabBookingDetailsJson.put("bookingId", cabBookingDetails.getBookingId());
		cabBookingDetailsJson.put("crn", cabBookingDetails.getCrn());
		cabBookingDetailsJson.put("callId", cabBookingDetails.getCallId());
		cabBookingDetailsJson.put("driver_name", cabBookingDetails.getDriverName());
		cabBookingDetailsJson.put("driver_number", cabBookingDetails.getDriverNumber());
		cabBookingDetailsJson.put("type", cabBookingDetails.getType());
		cabBookingDetailsJson.put("cab_type", cabBookingDetails.getCabType());
		cabBookingDetailsJson.put("cab_number", cabBookingDetails.getCabNumber());
		cabBookingDetailsJson.put("cab_model", cabBookingDetails.getCabModel());
		cabBookingDetailsJson.put("eta", cabBookingDetails.getEta());
		cabBookingDetailsJson.put("driver_lat", cabBookingDetails.getDriverLatitude());
		cabBookingDetailsJson.put("driver_lng", cabBookingDetails.getDriverLongitude());
		cabBookingDetailsJson.put("created", cabBookingDetails.getCreated());
		cabBookingDetailsJson.put("status", cabBookingDetails.getStatus());
		cabBookingDetailsJson.put("pickupLatitude", cabBookingDetails.getPickupLatitude());
		cabBookingDetailsJson.put("pickupLongitude", cabBookingDetails.getPickupLongitude());
		cabBookingDetailsJson.put("dropLatitude", cabBookingDetails.getDropLatitude());
		cabBookingDetailsJson.put("dropLongitude", cabBookingDetails.getDropLongitude());
		cabBookingDetailsJson.put("amount", cabBookingDetails.getAmount());
		cabBookingDetailsJson.put("paymentMode", cabBookingDetails.getPaymentMode());
		cabBookingDetailsJson.put("accessToken", cabBookingDetails.getAccessToken());
		cabBookingDetailsJson.put("refreshToken", cabBookingDetails.getRefreshToken());
		cabBookingDetailsJson.put("deletionTime", cabBookingDetails.getDeletionTime());
		cabBookingDetailsJson.put("pickup_address", cabBookingDetails.getPickupAddress());
		cabBookingDetailsJson.put("drop_address", cabBookingDetails.getDropAddress());
		cabBookingDetailsJson.put("hash", cabBookingDetails.getHash());
		cabBookingDetailsJson.put("keyUrl", "http://zapplon.com/tracking?key=" + cabBookingDetails.getHash());
		cabBookingDetailsJson.put("cashback", cabBookingDetails.getCashback());
		return cabBookingDetailsJson;
	}

	public static JSONObject getCabBookingCompactJson(Bookings cabBookingDetails) throws JSONException {
		if (cabBookingDetails == null)
			return null;
		JSONObject cabBookingDetailsJson = new JSONObject();
		cabBookingDetailsJson.put("type", cabBookingDetails.getType());
		cabBookingDetailsJson.put("driver_lat", cabBookingDetails.getDriverLatitude());
		cabBookingDetailsJson.put("driver_lng", cabBookingDetails.getDriverLongitude());
		cabBookingDetailsJson.put("created", cabBookingDetails.getCreated());
		cabBookingDetailsJson.put("status", cabBookingDetails.getStatus());
		cabBookingDetailsJson.put("pickupLatitude", cabBookingDetails.getPickupLatitude());
		cabBookingDetailsJson.put("pickupLongitude", cabBookingDetails.getPickupLongitude());
		cabBookingDetailsJson.put("dropLatitude", cabBookingDetails.getDropLatitude());
		cabBookingDetailsJson.put("dropLongitude", cabBookingDetails.getDropLongitude());
		cabBookingDetailsJson.put("deletionTime", cabBookingDetails.getDeletionTime());
		cabBookingDetailsJson.put("pickup_address", cabBookingDetails.getPickupAddress());
		cabBookingDetailsJson.put("drop_address", cabBookingDetails.getDropAddress());
		cabBookingDetailsJson.put("cashback", cabBookingDetails.getCashback());
		return cabBookingDetailsJson;
	}

	public static JSONObject getCabSessionJson(OAuthSession cabSession) throws JSONException {
		if (cabSession == null)
			return null;
		JSONObject appConfigJson = new JSONObject();
		appConfigJson.put("type", cabSession.getType());
		appConfigJson.put("cabSessionId", cabSession.getCabSessionId());
		appConfigJson.put("deletionTime", cabSession.getDeletionTime());
		appConfigJson.put("accessToken", cabSession.getAccessToken());
		return appConfigJson;
	}

	public static JSONObject getVoucherJson(Voucher voucher) throws JSONException {
		if (voucher == null)
			return null;
		JSONObject voucherJson = new JSONObject();
		voucherJson.put("voucher_id", voucher.getVoucherId());
		voucherJson.put("image_url", voucher.getImage_url());
		voucherJson.put("company_name", voucher.getCompany_name());
		voucherJson.put("terms", voucher.getTerms());
		voucherJson.put("expiry_date", voucher.getExpiry_date());
		voucherJson.put("value", voucher.getValue());
		return voucherJson;
	}
	*/

	public static JSONObject getUserJson(User user) throws JSONException {
		if (user == null)
			return null;
		JSONObject userJsonObject = new JSONObject();

		JSONObject userJson = new JSONObject();
		//userJsonObject.put("email", user.getEmail());
		userJsonObject.put("user_id", user.getUserId() + "");
		//userJsonObject.put("is_verified", user.getIsVerified());
		//userJsonObject.put("phone", user.getPhone());
		//userJsonObject.put("contact", user.getPhone());
		userJson.put("user", userJsonObject);
		return userJson;
	}
	/*

	public static JSONObject getUberTimeEstimateJson(IntracityCabTimeEstimates uberTimeEstimates) throws JSONException {
		if (uberTimeEstimates == null)
			return null;
		JSONObject appConfigJson = new JSONObject();
		appConfigJson.put("estimate", uberTimeEstimates.getEstimate());
		appConfigJson.put("display_name", uberTimeEstimates.getDisplayName());
		appConfigJson.put("product_id", uberTimeEstimates.getProductId());
		return appConfigJson;
	}

	public static JSONObject getUberPriceEstimateJson(IntracityCabPriceEstimates uberTimeEstimates)
			throws JSONException {
		if (uberTimeEstimates == null)
			return null;
		JSONObject appConfigJson = new JSONObject();
		appConfigJson.put("product_id", uberTimeEstimates.getProductId());
		appConfigJson.put("currency_code", uberTimeEstimates.getCurrencyCode());
		appConfigJson.put("display_name", uberTimeEstimates.getDisplayName());
		appConfigJson.put("estimate", uberTimeEstimates.getEstimate());
		appConfigJson.put("low_estimate", uberTimeEstimates.getLowEstimate());
		appConfigJson.put("high_estimate", uberTimeEstimates.getHighEstimate());
		appConfigJson.put("surge_multiplier", uberTimeEstimates.getSurgeMultiplier());
		appConfigJson.put("duration", uberTimeEstimates.getDuration());
		appConfigJson.put("distance", uberTimeEstimates.getDistance());

		return appConfigJson;
	}

	public static JSONObject getCabDetailsJson(IntracityCab cabDetails) throws JSONException {
		if (cabDetails == null)
			return null;
		JSONObject appConfigJson = new JSONObject();

		appConfigJson.put("type", cabDetails.getType());
		appConfigJson.put("subType", cabDetails.getSubType());
		appConfigJson.put("waitingCostPerMinute", cabDetails.getWaitingCostPerMinute());
		appConfigJson.put("ridingCostPerMinute", cabDetails.getRidingCostPerMinute());
		appConfigJson.put("costPerDistance", cabDetails.getCostPerDistance());
		appConfigJson.put("base", cabDetails.getBase());
		appConfigJson.put("surcharge", getSurchargeObject(cabDetails.getSurcharge()));
		appConfigJson.put("cancellationFee", cabDetails.getCancellationFee());
		appConfigJson.put("estimatedTimeOfArrival", cabDetails.getEstimatedTimeOfArrival());
		appConfigJson.put("priceEstimate", cabDetails.getPriceEstimate());
		appConfigJson.put("timeEstimate", cabDetails.getTimeEstimate());
		appConfigJson.put("capacity", cabDetails.getCapacity());
		appConfigJson.put("distance", cabDetails.getDistance());
		appConfigJson.put("productId", cabDetails.getProductId());
		appConfigJson.put("logoUrl", cabDetails.getLogoUrl());
		appConfigJson.put("displayName", cabDetails.getDisplayName());
		appConfigJson.put("isRecommended", cabDetails.getIsRecommended());
		return appConfigJson;
	}

	public static JSONObject getTaxiDetailsJson(IntercityCab taxiDetails) throws JSONException {
		if (taxiDetails == null)
			return null;
		JSONObject appConfigJson = new JSONObject();
		appConfigJson.put("type", taxiDetails.getType());
		appConfigJson.put("subType", taxiDetails.getSubType());
		appConfigJson.put("logoUrl", taxiDetails.getLogoUrl());
		appConfigJson.put("displayName", taxiDetails.getDisplayName());
		appConfigJson.put("costPerDistance", taxiDetails.getCostPerDistance());
		appConfigJson.put("basePrice", taxiDetails.getBasePrice());
		appConfigJson.put("fare", taxiDetails.getFare());
		appConfigJson.put("advance", taxiDetails.getAdvance());
		appConfigJson.put("capacity", taxiDetails.getCapacity());
		appConfigJson.put("structure", taxiDetails.getStructure());
		appConfigJson.put("modes", taxiDetails.getModes());
		appConfigJson.put("availability", taxiDetails.getAvailability());
		appConfigJson.put("bookingId", taxiDetails.getBookingId());
		appConfigJson.put("pickupId", taxiDetails.getPickupId());
		appConfigJson.put("cabImageUrl", taxiDetails.getCabImageUrl());
		appConfigJson.put("cabType", taxiDetails.getCabType());
		appConfigJson.put("terms", taxiDetails.getTerms());
		return appConfigJson;
	}

	public static JSONObject getTaxiBookingDetailsJson(TaxiBookings taxiDetails) throws JSONException {
		if (taxiDetails == null)
			return null;
		JSONObject appConfigJson = new JSONObject();

		appConfigJson.put("bookingId", taxiDetails.getBookingId());
		appConfigJson.put("fromCity", taxiDetails.getFromCity());
		appConfigJson.put("toCity", taxiDetails.getToCity());
		appConfigJson.put("status", taxiDetails.getStatus());
		appConfigJson.put("startDate", taxiDetails.getStartDate());
		appConfigJson.put("returnDate", taxiDetails.getReturnDate());
		appConfigJson.put("bookingCode", taxiDetails.getBookingCode());
		appConfigJson.put("amount", taxiDetails.getAmount());
		appConfigJson.put("advance",(Double) taxiDetails.getAdvance());
		appConfigJson.put("created", taxiDetails.getCreated());
		appConfigJson.put("modified", taxiDetails.getModified());
		appConfigJson.put("type", taxiDetails.getType());
		appConfigJson.put("subType", taxiDetails.getSubType());
		appConfigJson.put("structure", taxiDetails.getStructure());
		appConfigJson.put("mode", taxiDetails.getMode());
		appConfigJson.put("userId", taxiDetails.getUserId());
		appConfigJson.put("pickupLatitude", taxiDetails.getPickupLatitude());
		appConfigJson.put("pickupLongitude", taxiDetails.getPickupLongitude());
		appConfigJson.put("pickupAddress", taxiDetails.getPickupAddress());
		appConfigJson.put("dropLatitude", taxiDetails.getDropLatitude());
		appConfigJson.put("dropLongitude", taxiDetails.getDropLongitude());
		appConfigJson.put("dropAddress", taxiDetails.getDropAddress());
		appConfigJson.put("referenceId", taxiDetails.getReferenceId());
		appConfigJson.put("hash", taxiDetails.getHash());

		return appConfigJson;
	}

	public static JSONObject getSurchargeObject(Surcharge surcharge) throws JSONException {
		if (surcharge == null)
			return null;
		JSONObject trackingJson = new JSONObject();
		trackingJson.put("name", surcharge.getName());
		trackingJson.put("type", surcharge.getType());
		trackingJson.put("description", surcharge.getDescription());
		trackingJson.put("value", surcharge.getValue());
		return trackingJson;
	}

	public static JSONObject getCabTrackingObject(Tracking tracking) throws JSONException {
		if (tracking == null)
			return null;
		JSONObject trackingJson = new JSONObject();
		trackingJson.put("advance", tracking.getAdvance());
		trackingJson.put("amount", tracking.getAmount());
		trackingJson.put("booking_status", tracking.getBookingStatus());
		trackingJson.put("discount", tracking.getDiscount());
		trackingJson.put("distance_value", tracking.getDistance_value());
		trackingJson.put("driver_latitude", tracking.getDriverLatitude());
		trackingJson.put("driver_longitude", tracking.getDriverLongitude());
		trackingJson.put("money_balance", tracking.getMoneyBalance());
		trackingJson.put("payable_amount", tracking.getPayableAmount());
		trackingJson.put("timestamp", tracking.getTimestamp());
		trackingJson.put("token_deletion_time", tracking.getTokenDeletionTime());
		trackingJson.put("tracking_id", tracking.getTrackingId());
		trackingJson.put("wait_time_value", tracking.getWait_time_value());
		trackingJson.put("crn", tracking.getCrn());
		trackingJson.put("distance_unit", tracking.getDistance_unit());
		trackingJson.put("status", tracking.getStatus());
		trackingJson.put("token", tracking.getToken());
		trackingJson.put("wait_time_unit", tracking.getWait_time_unit());
		return trackingJson;
	}

	public static JSONObject getSelfDriveAvailabilityJson(selfDriveCabDetails cabs) throws JSONException {
		JSONObject object = new JSONObject();
		object.put("carModel", cabs.getCarModel());
		object.put("pkgRate", cabs.getPkgRate());
		object.put("DepositAmt", cabs.getDepositAmt());
		object.put("subLocationName", cabs.getSubLocationName());
		object.put("carCatgory", cabs.getCarCatgory());
		return object;
	}
	
	public static JSONObject getOfflineAddressJson(OfflineAddress location)throws JSONException {
		JSONObject object = new JSONObject();
		object.put("cityId", location.getCityId());
		object.put("address", location.getAddress());
		object.put("latitude", location.getLatitude());
		object.put("id", location.getId());
		object.put("longitude", location.getLongitude());
		return object;
	}
	
	public static JSONObject getGroupsJson(C_Group group)throws JSONException{
		JSONObject object = new JSONObject();
		object.put("groupId", group.getGroupId());
		object.put("name", group.getName());
		return object;
	}
	
	public static JSONObject getEmployeesJson(C_Employee employee)throws JSONException{
		JSONObject object = new JSONObject();
		object.put("name", employee.getE_name());
		object.put("email", employee.getE_email());
		return object;
	}
	
	public static JSONObject getPackagesJson(C_Packages packages)throws JSONException{
		JSONObject object = new JSONObject();
		object.put("packageId", packages.getPackageId());
		object.put("packageName", packages.getPackageName());
		object.put("packageDescription", packages.getPackageDescription());

		return object;
	}
	*/

}