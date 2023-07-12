package com.sumologic.authservice.exception.custom;

import com.sumologic.authservice.enums.ErrorCode;
import com.sumologic.authservice.exception.CustomServiceException;

public class UserNotFoundException extends CustomServiceException {

	public UserNotFoundException() {
		super(ErrorCode.USER_NOT_EXIST);
	}

	public UserNotFoundException(String message) {
		super(ErrorCode.USER_NOT_EXIST, message);
	}

}
