package org.example.exception;

public class PassengerNotFoundException extends RuntimeException {
    public PassengerNotFoundException(Long id) {
        super("Passenger with ID " + id + " not found.");
    }
}
