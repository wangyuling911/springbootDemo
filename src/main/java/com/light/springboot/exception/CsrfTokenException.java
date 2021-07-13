package com.light.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class CsrfTokenException extends RuntimeException {

	private static final long serialVersionUID = -6996602479341506621L;

	public CsrfTokenException(String msg) {
		super(msg);
	}
	
}
