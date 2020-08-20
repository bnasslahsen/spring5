package com.training.sample.hellorest.common;

import java.util.UUID;

public class ErrorDetail {

	private String id = UUID.randomUUID().toString();

	private String message;

	public ErrorDetail(String message2) {
		this.message = message2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
