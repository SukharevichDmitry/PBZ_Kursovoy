package org.example.service;

import org.example.dto.FlightRequestDTO;
import org.example.dto.FlightResponseDTO;
import org.example.entity.Flight;
import org.example.exception.FlightNotFoundException;
import org.example.exception.InvalidException;
import org.example.mapper.FlightMapper;
import org.example.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightMapper flightMapper;

    public List<FlightResponseDTO> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();

        return flights.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private FlightResponseDTO convertToResponseDTO(Flight flight) {
        return new FlightResponseDTO(
                flight.getId(),
                flight.getRaceNumber(),
                flight.getDeparturePoint(),
                flight.getArrivalPoint(),
                flight.getDepartureDate(),
                flight.getArrivalDate(),
                flight.getAviaCompany()
        );
    }

    public FlightResponseDTO getFlightById(Long id) {
        return flightMapper.toResponseDTO(flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id)));
    }

    public FlightResponseDTO createFlight(FlightRequestDTO flightRequestDTO) {
//        Optional<Flight> existingFlight = flightRepository.findByRaceNumber(flightRequestDTO.getRaceNumber());
//        if (existingFlight.isPresent()) {
//            throw new InvalidException("Flight with that race number already exists.");
//        }

        Flight flight = flightMapper.toEntity(flightRequestDTO);
        flightRepository.save(flight);

        return flightMapper.toResponseDTO(flight);
    }
    public FlightResponseDTO updateFlight(Long id, FlightRequestDTO flightDetails) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));

        Optional<Flight> existingFlight = flightRepository.findByRaceNumber(flightDetails.getRaceNumber());
        if (existingFlight.isPresent() && !existingFlight.get().getId().equals(id)) {
            throw new InvalidException("Flight with that race number already exists.");
        }

        flight.setRaceNumber(flightDetails.getRaceNumber());
        flight.setDeparturePoint(flightDetails.getDeparturePoint());
        flight.setArrivalPoint(flightDetails.getArrivalPoint());
        flight.setDepartureDate(flightDetails.getDepartureDate());
        flight.setArrivalDate(flightDetails.getArrivalDate());
        flight.setAviaCompany(flightDetails.getAviaCompany());

        Flight updatedFlight = flightRepository.save(flight);

        return flightMapper.toResponseDTO(flight);
    }

    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new FlightNotFoundException(id);
        }
        flightRepository.deleteById(id);
    }
}
