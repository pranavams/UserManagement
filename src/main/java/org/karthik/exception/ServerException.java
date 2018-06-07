package org.karthik.exception;

public class ServerException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ServerException(final String message) {
		super(message);
	}

	public ServerException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
