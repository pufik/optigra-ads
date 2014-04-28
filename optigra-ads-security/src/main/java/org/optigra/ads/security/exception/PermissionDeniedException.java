package org.optigra.ads.security.exception;

public class PermissionDeniedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PermissionDeniedException() {
        super();
    }

    public PermissionDeniedException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PermissionDeniedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PermissionDeniedException(final String message) {
        super(message);
    }

    public PermissionDeniedException(final Throwable cause) {
        super(cause);
    }
}
