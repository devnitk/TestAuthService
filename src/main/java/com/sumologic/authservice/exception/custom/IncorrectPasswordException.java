package com.sumologic.authservice.exception.custom;

import com.sumologic.authservice.enums.ErrorCode;
import com.sumologic.authservice.exception.CustomServiceException;

public class IncorrectPasswordException extends CustomServiceException {

	public IncorrectPasswordException() {
		super(ErrorCode.WRONG_CREDENTIALS);
	}
	
	public IncorrectPasswordException(String message) {
		super(ErrorCode.WRONG_CREDENTIALS, message);
	}
	
}
