package org.example.controller;

import org.example.dto.FlightRequestDTO;
import org.example.dto.FlightResponseDTO;
import org.example.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping
    public ResponseEntity<List<FlightResponseDTO>> getAllFlights(){
        List<FlightResponseDTO> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> getFlightById(@PathVariable Long id){
        FlightResponseDTO flightResponseDTO = flightService.getFlightById(id);
        return new ResponseEntity<>(flightResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FlightResponseDTO> createFlight(@RequestBody FlightRequestDTO flightRequestDTO) {
        FlightResponseDTO createdFlight = flightService.createFlight(flightRequestDTO);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> updateFlight(
            @PathVariable Long id,
            @RequestBody FlightRequestDTO flightDetails){
        FlightResponseDTO updatedFlight = flightService.updateFlight(id, flightDetails);
        return new ResponseEntity<>(updatedFlight, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }

}
