package org.example.exception;

public class InvalidPassengerException extends RuntimeException {
    public InvalidPassengerException(String message) {
        super(message);
    }
}
