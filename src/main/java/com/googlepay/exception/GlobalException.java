package com.googlepay.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

	}

//handle custom validation error
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getFieldErrors()
				.forEach((error) -> errors.put(error.getField(), error.getDefaultMessage()));
		 ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
		 exception.getBindingResult().getFieldError().getDefaultMessage(),"validationerror");

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception) {
		Map<String, String> errors = new HashMap<>();

		exception.getConstraintViolations().forEach(cv -> {
			errors.put("message", cv.getMessage());
			errors.put("path", (cv.getPropertyPath()).toString());
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

	
	@ExceptionHandler(ClientFeignException.class)  
    public ResponseEntity<?> handleFeignStatusException(ClientFeignException e, WebRequest request) {
      
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),e.getMessage(),request.getDescription(false));
               return new ResponseEntity<>(errorDetails,HttpStatus.EXPECTATION_FAILED);
    }
}
