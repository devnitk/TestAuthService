package com.sumologic.authservice.services.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumologic.authservice.enitites.UserEntity;
import com.sumologic.authservice.exception.custom.IncorrectPasswordException;
import com.sumologic.authservice.exception.custom.UserAlreadyExistsException;
import com.sumologic.authservice.exception.custom.UserNotFoundException;
import com.sumologic.authservice.repository.UserRepository;
import com.sumologic.authservice.request.UserSignUpRequest;
import com.sumologic.authservice.response.UserSignUpResponse;

/***
 * 
 * This helper class provides utility methods used within the UserServiceImpl
 * class for user-related operations.
 * 
 * @author devender.kumar
 *
 */
@Component
public class UserServiceImplHelper {

	@Autowired
	private UserRepository userRepository;

	// Helper method to check if a user with the given email already exists
	public void validateUserDoesNotExist(String email) throws UserAlreadyExistsException {
		if (userRepository.findByEmail(email).isPresent()) {
			throw new UserAlreadyExistsException("User with email '" + email + "' already exists.");
		}
	}

	// Helper method to retrieve a user by email and throw an exception if not found
	public UserEntity getUserByEmail(String email) throws UserNotFoundException {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User with email '" + email + "' not found."));
	}

	// Helper method to validate the provided password against the user's password
	public void validatePassword(UserEntity userEntity, String password) throws IncorrectPasswordException {
		if (!userEntity.getPassword().equals(password)) {
			throw new IncorrectPasswordException(
					"Incorrect password for user with email '" + userEntity.getEmail() + "'.");
		}
	}

	// Helper method to create a UserSignUpResponse object from a UserEntity
	public UserSignUpResponse createUserSignUpResponse(UserEntity userEntity) {
		return UserSignUpResponse.builder().id(userEntity.getId()).name(userEntity.getName())
				.email(userEntity.getEmail()).build();
	}

	// Helper method to create a UserEntity object from a UserSignUpRequest
	public UserEntity createUserEntity(UserSignUpRequest signUpRequest) {
		return UserEntity.builder().name(signUpRequest.getName()).password(signUpRequest.getPassword())
				.email(signUpRequest.getEmail()).build();
	}

}
