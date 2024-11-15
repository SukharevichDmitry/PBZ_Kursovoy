package org.example.mapper;

import org.example.dto.FlightRequestDTO;
import org.example.dto.FlightResponseDTO;
import org.example.entity.Flight;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Flight toEntity(FlightRequestDTO flightRequestDTO) {
        return modelMapper.map(flightRequestDTO, Flight.class);
    }

    public FlightResponseDTO toResponseDTO(Flight flight) {
        return modelMapper.map(flight, FlightResponseDTO.class);
    }

}
