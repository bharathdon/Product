package com.abc.exception;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ProductsNotFoundException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ResponseEntity<CustomErrorResponse> productsNotFoundException(
			ProductsNotFoundException productsNotFoundException, WebRequest request) {
		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setLocalDateTime(LocalDateTime.now(Clock.systemUTC()));
		customErrorResponse.setError(productsNotFoundException.getMessage());
		customErrorResponse.setStatus(HttpStatus.CONFLICT.value());
		return new ResponseEntity<CustomErrorResponse>(customErrorResponse, HttpStatus.CONFLICT);

	}

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ResponseEntity<CustomErrorResponse> exception(Exception exception, WebRequest request) {
		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setLocalDateTime(LocalDateTime.now(Clock.systemUTC()));
		customErrorResponse.setError(exception.getLocalizedMessage());
		customErrorResponse.setStatus(HttpStatus.CONFLICT.value());
		return new ResponseEntity<CustomErrorResponse>(customErrorResponse, HttpStatus.CONFLICT);

	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ResponseEntity<CustomErrorResponse> methodArgumentNotValidException(
			MethodArgumentNotValidException exception, WebRequest webRequest) {
		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setLocalDateTime(LocalDateTime.now(Clock.systemUTC()));
		customErrorResponse.setError(exception.getBindingResult().getFieldError().getDefaultMessage());
		customErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<CustomErrorResponse>(customErrorResponse, HttpStatus.BAD_GATEWAY);

	}
}
