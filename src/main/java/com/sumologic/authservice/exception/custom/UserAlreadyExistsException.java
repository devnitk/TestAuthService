package com.sumologic.authservice.exception.custom;

import com.sumologic.authservice.enums.ErrorCode;
import com.sumologic.authservice.exception.CustomServiceException;

public class UserAlreadyExistsException extends CustomServiceException {

	public UserAlreadyExistsException() {
		super(ErrorCode.USER_ALREADY_EXIST);
	}
	
	public UserAlreadyExistsException(String message) {
		super(ErrorCode.USER_ALREADY_EXIST, message);
	}
}
