package com.makershark.poc.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class MSExceptionHandler {
	
	private static final String MESSAGE = "message";

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<Map<String,String>> handleRuntimeException(RuntimeException re)	{
		log.error("runtime exception ",re);
		Map<String, String> respMap = new HashMap<>();
		respMap.put(MESSAGE, re.getMessage());
		return new ResponseEntity<>(respMap,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException (MethodArgumentNotValidException me)	{
		log.error("invalid argument", me);
		Map<String, String> respMap = new HashMap<>();
		me.getFieldErrors().forEach(err -> {
			respMap.put(err.getField(), err.getDefaultMessage());
		});
		return new ResponseEntity<>(respMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Map<String,List<String>>> handleConstraintViolationException(ConstraintViolationException ce)	{
		log.error("arhumnet voilation ", ce);
		Map<String, List<String>> respMap = new HashMap<>();
		List<String> failures = new ArrayList<>();
		ce.getConstraintViolations().forEach(voilation -> {
			failures.add(voilation.getMessage());
		});
		respMap.put("errors", failures);
		return new ResponseEntity<>(respMap,HttpStatus.BAD_REQUEST);
	}
}
