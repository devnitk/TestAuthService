package com.sumologic.authservice.services;

import com.sumologic.authservice.request.UserSignInRequest;
import com.sumologic.authservice.request.UserSignUpRequest;
import com.sumologic.authservice.response.UserSignInResponse;
import com.sumologic.authservice.response.UserSignUpResponse;

public interface UserService {
	
	UserSignUpResponse registerUser(UserSignUpRequest signUpRequest) throws Exception;
	
	UserSignInResponse signInUser(UserSignInRequest signInRequest) throws Exception;

}
