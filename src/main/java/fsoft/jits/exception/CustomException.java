package fsoft.jits.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
	//private static final long serialVersionUID = 1L;
	
	private final String message;
	private final HttpStatus httpStatus;
	
	public CustomException(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
