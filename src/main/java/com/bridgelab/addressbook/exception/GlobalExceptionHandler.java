package com.bridgelab.addressbook.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		List<String> errorList = allErrors.stream().map(error -> error.getDefaultMessage())
				.collect(Collectors.toList());
		return new ResponseEntity<List<String>>(errorList, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(AddressBookException.class)
	public ResponseEntity<String> handleAddressBookException(AddressBookException e) {
		String errorMessage = e.getMessage();
		return new ResponseEntity<String>(errorMessage, HttpStatus.BAD_REQUEST);
	}
}
