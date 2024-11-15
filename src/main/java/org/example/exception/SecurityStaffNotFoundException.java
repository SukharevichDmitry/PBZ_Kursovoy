package org.example.exception;

public class SecurityStaffNotFoundException extends RuntimeException {
    public SecurityStaffNotFoundException(Long id) {
        super("Security Staff with ID " + id + " not found.");
    }
}
