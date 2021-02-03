package com.abc.exception;

public class ProductsNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductsNotFoundException(String message) {

		super(message);
	}
}
