package com.training.sample.hellorest.common;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDetail {

	@JsonProperty
	private String id = UUID.randomUUID().toString();

	@JsonProperty
	private String message;

	public ErrorDetail(String message2) {
		this.message = message2;
	}

}
