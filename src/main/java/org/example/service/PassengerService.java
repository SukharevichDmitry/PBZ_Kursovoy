package org.example.service;

import org.example.dto.PassengerRequestDTO;
import org.example.dto.PassengerResponseDTO;
import org.example.entity.Passenger;
import org.example.exception.InvalidPassengerException;
import org.example.exception.PassengerNotFoundException;
import org.example.mapper.PassengerMapper;
import org.example.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassengerMapper passengerMapper;

    public PassengerResponseDTO getPassengerById(Long id) {
        return passengerMapper.toResponseDTO(passengerRepository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id)));
    }

    public Optional<PassengerResponseDTO> getPassengerByPassportNumber(Long passportNumber) {
        return passengerRepository.findByPassportNumber(passportNumber)
                .map(passengerMapper::toResponseDTO);
    }

    public List<PassengerResponseDTO> getAllPassengers() {
        List<Passenger> passengers = passengerRepository.findAll();

        return passengers.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private PassengerResponseDTO convertToResponseDTO(Passenger passenger) {
        return new PassengerResponseDTO(
                passenger.getId(),
                passenger.getFullName(),
                passenger.getPassportNumber(),
                passenger.getBirthDate(),
                passenger.getGender(),
                passenger.getContactInfo()
        );
    }

    public PassengerResponseDTO createPassenger (PassengerRequestDTO passengerRequestDTO) {
            Optional<Passenger> existingPassenger = passengerRepository.findByPassportNumber(passengerRequestDTO.getPassportNumber());
            if (existingPassenger.isPresent()) {
                throw new InvalidPassengerException("Passenger with that passport number already exists.");
            }

            Passenger passenger = passengerMapper.toEntity(passengerRequestDTO);
            passengerRepository.save(passenger);

            return passengerMapper.toResponseDTO(passenger);
    }

    public PassengerResponseDTO updatePassenger(Long id, PassengerRequestDTO passengerDetails) {

        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));

        Optional<Passenger> existingPassenger = passengerRepository.findByPassportNumber(passengerDetails.getPassportNumber());
        if (existingPassenger.isPresent() && !existingPassenger.get().getId().equals(id)) {
            throw new InvalidPassengerException("Passenger with that passport number already exists.");
        }

        passenger.setFullName(passengerDetails.getFullName());
        passenger.setPassportNumber(passengerDetails.getPassportNumber());
        passenger.setBirthDate(passengerDetails.getBirthDate());
        passenger.setGender(passengerDetails.getGender());
        passenger.setContactInfo(passengerDetails.getContactInfo());

        Passenger updatedPassenger = passengerRepository.save(passenger);

        return passengerMapper.toResponseDTO(passenger);
    }

    public void deletePassenger(Long id) {
        if (!passengerRepository.existsById(id)) {
            throw new PassengerNotFoundException(id);
        }
        passengerRepository.deleteById(id);
    }



}
