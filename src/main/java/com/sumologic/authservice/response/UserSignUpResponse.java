package com.sumologic.authservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class UserSignUpResponse {

	@JsonProperty
	private Long id;

	@JsonProperty
	private String name;

	@JsonProperty
	private String email;

}
