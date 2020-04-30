package com.example.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ApiException {
	
	private String message;
	private HttpStatus httpStatus;
	private Date timestamp;
	
	public ApiException(String message, HttpStatus httpStatus, Date timestamp) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public Date getTimestamp() {
		return timestamp;
	}
}