package org.mesa.pkwrapper.exceptions;

public class NotAuthorizedException extends Exception {
    public NotAuthorizedException(String errorMessage) {
        super(errorMessage);
    }
}
