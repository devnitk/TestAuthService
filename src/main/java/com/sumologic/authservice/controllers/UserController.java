package com.sumologic.authservice.controllers;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumologic.authservice.constant.Path;
import com.sumologic.authservice.request.UserSignInRequest;
import com.sumologic.authservice.request.UserSignUpRequest;
import com.sumologic.authservice.response.UserSignInResponse;
import com.sumologic.authservice.response.UserSignUpResponse;
import com.sumologic.authservice.response.WrapperResponse;
import com.sumologic.authservice.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/***
 * 
 * 
 * User Controller
 *
 * This controller handles user registration and sign-in operations.
 * 
 * @author devender.kumar
 *
 */

@RestController
@RequestMapping(value = Path.BASE_PATH_V1)
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	/**
     * Register User API
     *
     * This API is used to create/register a new user.
     *
     * @param signupRequest The user sign-up request object containing the user details.
     * @return A wrapper response containing the user sign-up response.
     * @throws Exception If an error occurs during the user registration process.
     */

	@PostMapping(value = Path.SIGNUP, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create/register User.")
	  @ApiResponses(
	      value = {
	        @ApiResponse(
	            responseCode = "200",
	            description = "User registered successfully",
	            content = {
	              @Content(
	                  mediaType = MediaType.APPLICATION_JSON_VALUE,
	                  schema = @Schema(implementation = UserSignUpResponse.class))
	            }),
	        @ApiResponse(
	            responseCode = "400",
	            description = "User signup Request is not valid",
	            content = @Content),
	        @ApiResponse(
		            responseCode = "409",
		            description = "User already exists",
		            content = @Content)
	        
	      })
	public WrapperResponse<UserSignUpResponse> registerUser(@RequestBody @Valid UserSignUpRequest signupRequest)
			throws Exception {
		// Delegate the user registration logic to the UserService
        UserSignUpResponse signUpResponse = userService.registerUser(signupRequest);

        // Wrap the response in a WrapperResponse and return
        return new WrapperResponse<>(signUpResponse);
	}

	
	/**
	 * User Sign-In API
	 *
	 * This API is used to authenticate a user and generate a sign-in response.
	 *
	 * @param request The user sign-in request object containing the user credentials.
	 * @return A wrapper response containing the user sign-in response.
	 * @throws Exception If an error occurs during the sign-in process.
	 */
	@PostMapping(value = Path.LOGIN, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "User login or sign in.")
	  @ApiResponses(
	      value = {
	        @ApiResponse(
	            responseCode = "200",
	            description = "User login successfully",
	            content = {
	              @Content(
	                  mediaType = MediaType.APPLICATION_JSON_VALUE,
	                  schema = @Schema(implementation = UserSignInResponse.class))
	            }),
	        @ApiResponse(
	            responseCode = "401",
	            description = "User password is not valid",
	            content = @Content),
	        @ApiResponse(
		            responseCode = "404",
		            description = "User not found",
		            content = @Content)
	      })
	public WrapperResponse<UserSignInResponse> userSignIn(@RequestBody @Valid UserSignInRequest request)
			throws Exception {
		// Delegate the sign-in logic to the UserService
	    UserSignInResponse signInResponse = userService.signInUser(request);
	    
	    // Wrap the response in a WrapperResponse and return
	    return new WrapperResponse<>(signInResponse);
	}

}
