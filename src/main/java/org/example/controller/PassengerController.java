package org.example.controller;

import org.example.dto.PassengerRequestDTO;
import org.example.dto.PassengerResponseDTO;
import org.example.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/{id}")
    public ResponseEntity<PassengerResponseDTO> getPassengerById(@PathVariable Long id) {
        PassengerResponseDTO passengerResponse = passengerService.getPassengerById(id);
        return new ResponseEntity<>(passengerResponse, HttpStatus.OK);
    }

    @GetMapping("/passport/{passportNumber}")
    public ResponseEntity<PassengerResponseDTO> getPassengerByPassportNumber(@PathVariable String passportNumber) {
        return passengerService.getPassengerByPassportNumber(passportNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PassengerResponseDTO>> getAllPassengers() {
        List<PassengerResponseDTO> passengers = passengerService.getAllPassengers();
        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PassengerResponseDTO> createPassenger(@RequestBody PassengerRequestDTO passengerRequestDTO) {
        PassengerResponseDTO createdPassenger = passengerService.createPassenger(passengerRequestDTO);
        return new ResponseEntity<>(createdPassenger, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassengerResponseDTO> updatePassenger(
            @PathVariable Long id,
            @RequestBody PassengerRequestDTO passengerDetails) {
        PassengerResponseDTO updatedPassenger = passengerService.updatePassenger(id, passengerDetails);
        return new ResponseEntity<>(updatedPassenger, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
}
