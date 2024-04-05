package com.app.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	//Custom Exception : ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFound(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	
	// Validation Exception : MethodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> validationException(MethodArgumentNotValidException ex){
		Map<String, String> map = new HashMap<>();
			ex.getBindingResult().getAllErrors().forEach((error) -> {
				String field = ((FieldError)error).getField();
				String message = error.getDefaultMessage();
				map.put(field, message);
			});
		return new ResponseEntity<Map<String, String>>( map, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<ApiResponse> resourceNotFound(IOException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(MessagingException.class)
	public ResponseEntity<ApiResponse> messagingException(MessagingException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
