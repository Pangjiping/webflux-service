package org.epha.r2dbc.exception;

/**
 * @author pangjiping
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(final String message) {
        super(message);
    }

}
