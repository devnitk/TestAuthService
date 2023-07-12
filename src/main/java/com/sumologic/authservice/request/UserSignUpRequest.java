package com.sumologic.authservice.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sumologic.authservice.constant.ValidationConstant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequest {

	@NotNull(message = ValidationConstant.NAME_NOT_NULL)
	@NotEmpty(message = ValidationConstant.NAME_NOT_EMPTY)
	@Size(min = 3, message = ValidationConstant.NAME_SIZE_VALIDATION)
	private String name;

	@NotNull(message = ValidationConstant.PASSWORD_NOT_NULL)
	@NotEmpty(message = ValidationConstant.PASSWORD_NOT_EMPTY)
	@Size(min = 5, max = 16, message = ValidationConstant.PASSWORD_SIZE_VALIDATION)
	private String password;

	@NotNull(message = ValidationConstant.EMAIL_NOT_EMPTY)
	@NotEmpty(message = ValidationConstant.EMAIL_NOT_EMPTY)
	@Size(min = 4, message = ValidationConstant.EMAIL_SIZE_VALIDATION)
	@Email
	private String email;

}
