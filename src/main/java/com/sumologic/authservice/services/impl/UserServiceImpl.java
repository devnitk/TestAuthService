package com.sumologic.authservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumologic.authservice.enitites.UserEntity;
import com.sumologic.authservice.exception.custom.IncorrectPasswordException;
import com.sumologic.authservice.exception.custom.UserAlreadyExistsException;
import com.sumologic.authservice.exception.custom.UserNotFoundException;
import com.sumologic.authservice.repository.UserRepository;
import com.sumologic.authservice.request.UserSignInRequest;
import com.sumologic.authservice.request.UserSignUpRequest;
import com.sumologic.authservice.response.UserSignInResponse;
import com.sumologic.authservice.response.UserSignUpResponse;
import com.sumologic.authservice.services.UserService;
import com.sumologic.authservice.services.helper.UserServiceImplHelper;

import lombok.extern.slf4j.Slf4j;

/***
 * 
 * User Service Implementation

 *
 * This service class handles user registration and sign-in operations.
 * 
 * @author devender.kumar
 *
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserServiceImplHelper userServiceHelper;

	/**
	 * Register User
	 *
	 * This method is used to register a new user.
	 *
	 * @param signUpRequest The user sign-up request object containing the user
	 *                      details.
	 * @return The user sign-up response.
	 * @throws UserAlreadyExistsException If the user with the provided email
	 *                                    already exists.
	 */
	@Override
	public UserSignUpResponse registerUser(UserSignUpRequest signUpRequest) throws UserAlreadyExistsException {
		userServiceHelper.validateUserDoesNotExist(signUpRequest.getEmail());
		
		// 	TODO: Perform password encryption and decryption
		UserEntity userEntity = userServiceHelper.createUserEntity(signUpRequest);
		userEntity = userRepository.save(userEntity);
		log.info("User registration successful. UserID: {}", userEntity.getId());

		return userServiceHelper.createUserSignUpResponse(userEntity);
	}

	/**
	 * Sign-In User
	 *
	 * This method is used to authenticate a user and generate a sign-in response.
	 *
	 * @param signInRequest The user sign-in request object containing the user
	 *                      credentials.
	 * @return The user sign-in response.
	 * @throws UserNotFoundException      If the user with the provided email does
	 *                                    not exist.
	 * @throws IncorrectPasswordException If the provided password is incorrect.
	 */
	@Override
	public UserSignInResponse signInUser(UserSignInRequest signInRequest)
			throws UserNotFoundException, IncorrectPasswordException {
		UserEntity userEntity = userServiceHelper.getUserByEmail(signInRequest.getEmail());
		userServiceHelper.validatePassword(userEntity, signInRequest.getPassword());

		log.info("User login successful. UserID: {}", userEntity.getId());

		// TODO: Generate and return JWT token instead of manually creating the token
		return UserSignInResponse.builder().authToken("testtoken" + Math.random()).build();
	}

}
