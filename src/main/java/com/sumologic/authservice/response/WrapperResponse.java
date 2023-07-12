package com.sumologic.authservice.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WrapperResponse<T> extends ResponseEntity<ResponseBody<T>> {

	public WrapperResponse(T data) {
		super(ResponseBody.<T>builder().data(data).build(), HttpStatus.OK);
	}

	public WrapperResponse(T data, String code, String message) {
		super(ResponseBody.<T>builder().data(data).code(code).message(message).build(), HttpStatus.OK);
	}

	public WrapperResponse(HttpStatus httpStatus, String code, String message) {
		super(ResponseBody.<T>builder().code(code).message(message).build(), httpStatus);
	}

}
