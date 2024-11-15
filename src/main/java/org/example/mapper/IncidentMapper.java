package org.example.mapper;

import org.example.dto.FlightResponseDTO;
import org.example.dto.IncidentRequestDTO;
import org.example.dto.IncidentResponseDTO;
import org.example.entity.Incident;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class IncidentMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Incident toEntity(IncidentRequestDTO incidentRequestDTO) {
        return modelMapper.map(incidentRequestDTO, Incident.class);
    }

    public IncidentResponseDTO toResponseDTO(Incident incident) {
        return modelMapper.map(incident, FlightResponseDTO.class);
    }
}