package io.outreach.exception;

public class OutreachException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OutreachException(String message, Throwable cause) {
		super(message, cause);
	}

	public OutreachException(String message) {
		super(message);
	}

	public OutreachException(Throwable cause) {
		super(cause);
	}

}
