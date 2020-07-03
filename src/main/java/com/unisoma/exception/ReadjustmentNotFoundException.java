package com.unisoma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReadjustmentNotFoundException extends RuntimeException {

	public ReadjustmentNotFoundException(String exception) {
		super(exception);
	}

}