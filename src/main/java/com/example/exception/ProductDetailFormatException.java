package com.example.exception;

public class ProductDetailFormatException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductDetailFormatException(String message) {
		super(message);
	}
}