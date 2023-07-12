package com.sumologic.authservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class UserSignInResponse {

	@JsonProperty
	private String authToken;

}
