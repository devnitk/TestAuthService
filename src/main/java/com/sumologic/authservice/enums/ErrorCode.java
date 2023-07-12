package com.sumologic.authservice.enums;

import org.springframework.http.HttpStatus;

import com.sumologic.authservice.constant.MessageConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/***
 * Error Codes Enum
 * 
 * This enum class defines the error codes, corresponding error messages, and
 * HTTP statuses for the application.
 * 
 * @author devender.kumar
 *
 */

@Getter
@AllArgsConstructor
@ToString
public enum ErrorCode {

	USER_NOT_EXIST("SAUTH100", MessageConstant.LOGIN_USER_NOT_EXIST, HttpStatus.NOT_FOUND),
	WRONG_CREDENTIALS("SAUTH101", MessageConstant.LOGIN_WRONG_CREDENTIALS, HttpStatus.UNAUTHORIZED),
	USER_ALREADY_EXIST("SAUTH102", MessageConstant.EMAIL_USER_ALREADY_EXISTS, HttpStatus.CONFLICT),
	METHOD_ARGUMENT_NOT_VALID("SAUTH103", "METHOD ARGUMENT NOT VALID", HttpStatus.BAD_REQUEST),
	HTTP_MEDIA_TYPE_NOT_SUPPORTED("SAUTH104", "MEDIA TYPE NOT SUPPORTED", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
	HTTP_MESSAGE_NOT_READABLE("SAUTH105", "HTTP MESSAGE NOT READABLE", HttpStatus.BAD_REQUEST),
	MISSING_PARAMETER("SAUTH106", "MISSING PARAMETER", HttpStatus.BAD_REQUEST),

	DATA_INTEGRITY_VIOLATION("SAUTH107", "DATA INTEGRITY VIOLATION", HttpStatus.INTERNAL_SERVER_ERROR),
	UNKNOWN("SAUTH108", "UNKNOWN ", HttpStatus.INTERNAL_SERVER_ERROR);

	private String code;

	private String message;

	private HttpStatus httpStatus;

}
