package com.demo.exception;

public class InternalServerError extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InternalServerError() {
	}

	public InternalServerError(String message) {
		super(message);
	}

	public InternalServerError(String message, Throwable cause) {
		super(message, cause);
	}

	public InternalServerError(Throwable cause) {
		super(cause);
	}
}
