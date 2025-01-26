package com.kh.secom.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidParameterException.class)
	protected ResponseEntity<?> handleInvalidParameter(InvalidParameterException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@ExceptionHandler(DuplicateUserException.class)
	protected ResponseEntity<String> handleDuplicateUser(DuplicateUserException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleArgumentNotValid(MethodArgumentNotValidException e) {
		// List<FieldError> list = e.getBindingResult().getFieldErrors();
		Map<String, String> errors = new HashMap<String, String>();
		// for(int i = 0; i < list.size(); i++) {
		// log.info("{}, {}", list.get(i).getField(), list.get(i).getDefaultMessage());
		// errors.put(list.get(i).getField(), list.get(i).getDefaultMessage());

		// }
		e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(AccessTokenExpiredException.class)
	protected ResponseEntity<?> handlerExpiredToken(AccessTokenExpiredException e){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}
	
	@ExceptionHandler(JwtTokenException.class)
	protected ResponseEntity<?> handlerJwtException(JwtTokenException e){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}
	
	@ExceptionHandler(MismatchPasswordException.class)
	protected ResponseEntity<?> handlerPasswordException(MismatchPasswordException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
