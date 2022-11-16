package org.mesa.pkwrapper.exceptions;

public class StringTooLongException extends Exception {
    public StringTooLongException(String errorMessage) {
        super(errorMessage);
    }
}
