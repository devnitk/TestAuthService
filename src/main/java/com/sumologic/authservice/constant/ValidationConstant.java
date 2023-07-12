package com.sumologic.authservice.constant;

public class ValidationConstant {

	private ValidationConstant() {

	}

	public static final String NAME_NOT_EMPTY = "Name must not be EMPTY";
	public static final String NAME_NOT_NULL = "Name must not be NULL";
	public static final String NAME_SIZE_VALIDATION = "Name should have atleast 3 characters";

	public static final String PASSWORD_NOT_NULL = "Password must not be NULL";
	public static final String PASSWORD_NOT_EMPTY = "Password must not be EMPTY";
	public static final String PASSWORD_SIZE_VALIDATION = "Password must greater than or equal to 5 and less than or equal to 16.";

	public static final String EMAIL_NOT_NULL = "Email must not be NULL";
	public static final String EMAIL_NOT_EMPTY = "Email must not be EMPTY";
	public static final String EMAIL_SIZE_VALIDATION = "Email must greater than or equal to 4 and less than or equal to 30.";

}
