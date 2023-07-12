package com.sumologic.authservice.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sumologic.authservice.enums.ErrorCode;
import com.sumologic.authservice.response.ResponseBody;
import com.sumologic.authservice.response.WrapperResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Global exception handler.
 *
 * @author devender.kumar
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle HttpMessageNotReadableException. Happens when request JSON is
	 * malformed.
	 *
	 * @param ex      HttpMessageNotReadableException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ResponseEntity object
	 */
	@Override
	@NonNull
	protected ResponseEntity<Object> handleHttpMessageNotReadable(@NonNull HttpMessageNotReadableException ex,
			@NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
		ErrorCode errorCode = ErrorCode.HTTP_MESSAGE_NOT_READABLE;
		log.error("Error parsing request: {} : {}: {}", headers, request, ex);
		return new ResponseEntity<>(
				ResponseBody.builder().code(errorCode.getCode()).message("Malformed JSON request").build(),
				errorCode.getHttpStatus());
	}

	/**
	 * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is
	 * invalid as well.
	 *
	 * @param ex      HttpMediaTypeNotSupportedException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ResponseEntity object
	 */
	@Override
	@NonNull
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(@NonNull HttpMediaTypeNotSupportedException ex,
			@NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
		ErrorCode errorCode = ErrorCode.HTTP_MEDIA_TYPE_NOT_SUPPORTED;
		return new ResponseEntity<>(ResponseBody
				.builder().code(errorCode.getCode()).message(ex.getContentType()
						+ " media type is not supported. Supported media types are " + ex.getSupportedMediaTypes())
				.build(), errorCode.getHttpStatus());
	}

	/**
	 * Handle MissingServletRequestParameterException. Triggered when a 'required'
	 * request parameter is missing.
	 *
	 * @param ex      MissingServletRequestParameterException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the response object
	 */
	@Override
	@NonNull
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			@NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
		ErrorCode errorCode = ErrorCode.MISSING_PARAMETER;
		return new ResponseEntity<>(ResponseBody.builder().code(errorCode.getCode())
				.message(ex.getParameterName() + " parameter is missing").build(), errorCode.getHttpStatus());
	}

	/**
	 * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid
	 * validation.
	 *
	 * @param ex      the MethodArgumentNotValidException that is thrown when @Valid
	 *                validation fails
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ResponseEntity object
	 */
	@Override
	@NonNull
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			@NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
		ErrorCode errorCode = ErrorCode.METHOD_ARGUMENT_NOT_VALID;
		StringBuilder message = new StringBuilder(errorCode.getMessage() + ": ");
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			message.append(fieldError.getDefaultMessage() + "; ");
		}
		return new ResponseEntity<>(
				ResponseBody.builder().code(errorCode.getCode()).message(message.toString()).build(),
				errorCode.getHttpStatus());
	}

	/**
	 * Handle DataIntegrityViolationException, inspects the cause for different DB
	 * causes.
	 *
	 * @param ex the DataIntegrityViolationException
	 * @return the ResponseEntity object
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
			WebRequest request) {
		ErrorCode errorCode = ErrorCode.DATA_INTEGRITY_VIOLATION;
		return new ResponseEntity<>(
				ResponseBody.builder().code(errorCode.getCode()).message(errorCode.getMessage()).build(),
				errorCode.getHttpStatus());
	}

	@ExceptionHandler(value = CustomServiceException.class)
	public <T> WrapperResponse<T> customException(CustomServiceException serviceCustomException) {
		ErrorCode errorCode = serviceCustomException.getErrorCode();
		log.error("Custom Exception : {} {}", errorCode, serviceCustomException.getMessage());
		return new WrapperResponse<>(errorCode.getHttpStatus(), errorCode.getCode(), serviceCustomException.getMessage());
	}

	@ExceptionHandler(value = Exception.class)
	public <T> WrapperResponse<T> exception(Exception exception) {
		ErrorCode errorCode = ErrorCode.UNKNOWN;
		log.error("Exception : {} {}", errorCode, exception);
		return new WrapperResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, errorCode.getCode(), errorCode.getMessage());
	}

}
