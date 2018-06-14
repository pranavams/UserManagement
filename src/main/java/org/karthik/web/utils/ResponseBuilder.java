package org.karthik.web.utils;

import javax.ws.rs.core.Response;

import org.karthik.exception.ExceptionHandler;

public class ResponseBuilder {
	
	private static final String CLASS_NAME = ResponseBuilder.class.getSimpleName();
	
	private static final ExceptionHandler exceptionHandler = new ExceptionHandler();
	
	public enum Status {
		OK(200), USER_EXCEPTION_GENERAL(400), USER_EXCEPTION_NOT_AUTHORIZED(401), SERVER_EXCEPTION_GENERAL(500);

		int code;

		Status(final int code) {
			this.code = code;
		}

		public int code() {
			return code;
		}
	}
	
	public static Response ok(final Object entity) {
		return Response.status(Status.OK.code).entity(entity).build();
	}
	
	public static Response okOrUserError(final Object entity) {
		return Response.status(Status.USER_EXCEPTION_GENERAL.code).entity(entity).build();
	}
	
	public static Response serverError(final String message){
		return Response.status(Status.SERVER_EXCEPTION_GENERAL.code).entity(message).build();
	}
	
	public static Response okOrUserErrorJson(final Object entity) {
		try {
			return ok(entity);
		} catch (final Exception e) {
			exceptionHandler.throwServerException(CLASS_NAME, e);
			return userErrorJson(e.getMessage());
		}
	}
	
	public static Response userErrorJson(final String message) {
		return userError(message);
	}
	
	public static Response userError(final Object entity) {
		return Response.status(Status.USER_EXCEPTION_GENERAL.code).entity(entity).build();
	}
}
