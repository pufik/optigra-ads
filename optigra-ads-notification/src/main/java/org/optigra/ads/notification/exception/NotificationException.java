package org.optigra.ads.notification.exception;

public class NotificationException extends RuntimeException {
	private static final long serialVersionUID = -2003648780067765539L;

	public static final String DEFAULT_MESSAGE = "Exception occurs while sending message to device";
	
	public NotificationException() {
		super();
	}

	public NotificationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotificationException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotificationException(String message) {
		super(message);
	}

	public NotificationException(Throwable cause) {
		super(cause);
	}

}
