package com.example.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionhandler {

	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<Object> handleApiRequestException(ProductNotFoundException e){
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
	ApiException apiException =  	new ApiException(e.getMessage(),
				httpStatus,
				new Date());
		return new ResponseEntity<>(apiException, httpStatus);
	}
	
	@ExceptionHandler(value = ProductDetailFormatException.class)
	public ResponseEntity<Object> handleApiRequestException3(ProductDetailFormatException e){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	ApiException apiException =  	new ApiException(e.getMessage(),
				httpStatus,
				new Date());
		return new ResponseEntity<>(apiException, httpStatus);
	}
	
	@ExceptionHandler(value = JwtTokenException.class)
	public ResponseEntity<Object> jwtTokenException(JwtTokenException e){
		HttpStatus httpStatus = HttpStatus.FORBIDDEN;
	ApiException apiException =  	new ApiException(e.getMessage(),
				httpStatus,
				new Date());
		return new ResponseEntity<>(apiException, httpStatus);
	}
}