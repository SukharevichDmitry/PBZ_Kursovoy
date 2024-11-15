package org.example.exception;

public class SecurityObjectNotFoundException extends RuntimeException {
    public SecurityObjectNotFoundException(Long id) {
        super("Security Object with ID " + id + " not found.");
    }
}
