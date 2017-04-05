package com.application.job.util.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.application.job.model.pojo.ErrorMessage;
import com.application.job.util.CommonLib;

@Provider
public class ZApplicationExceptionHandler implements ExceptionMapper<ZException> {

	@Override
	public Response toResponse(ZException exception) {

//		System.out.println("hey came here");
		// Email for exception

		return Response.status(Status.BAD_REQUEST).entity(
				new ErrorMessage(exception.getMessage(), "failure", CommonLib.RESPONSE_FAILURE, "exception caught"))
				.build();
	}

}
