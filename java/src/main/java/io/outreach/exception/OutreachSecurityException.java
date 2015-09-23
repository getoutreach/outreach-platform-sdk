package io.outreach.exception;

public class OutreachSecurityException extends OutreachException {

	private static final long serialVersionUID = 1L;

	public OutreachSecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public OutreachSecurityException(String message) {
		super(message);
	}

	public OutreachSecurityException(Throwable cause) {
		super(cause);
	}

}
