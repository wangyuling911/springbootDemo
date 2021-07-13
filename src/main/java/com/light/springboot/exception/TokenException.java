package com.light.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
public class TokenException extends RuntimeException {

	private static final long serialVersionUID = -2309905504660327423L;

	public TokenException(String msg) {
		super(msg);
	}
	
}
