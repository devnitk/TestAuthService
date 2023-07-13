package com.sumologic.authservice.exception;

import com.sumologic.authservice.enums.ErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	final ErrorCode errorCode;

	public CustomServiceException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public CustomServiceException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
}