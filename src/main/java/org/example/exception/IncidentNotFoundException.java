package org.example.exception;

public class IncidentNotFoundException extends RuntimeException {
    public IncidentNotFoundException(Long id) {
        super("Incident with place ID " + id + " not found.");
    }
}
