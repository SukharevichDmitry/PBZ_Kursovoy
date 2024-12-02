package org.example.mapper;

import org.example.dto.FlightRequestDTO;
import org.example.dto.FlightResponseDTO;
import org.example.dto.IncidentResponseDTO;
import org.example.dto.IncidentRequestDTO;
import org.example.entity.Flight;
import org.example.entity.Incident;
import org.example.view.IncidentViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IncidentMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Incident toEntity(IncidentRequestDTO incidentRequestDTO) {
        return modelMapper.map(incidentRequestDTO, Incident.class);
    }

    public IncidentResponseDTO toResponseDTO(Incident incident) {
        return modelMapper.map(incident, IncidentResponseDTO.class);
    }

    public static IncidentResponseDTO toResponseDTO(IncidentViewModel model) {
        return new IncidentResponseDTO(
                model.getId(),
                model.getIncidentDate(),
                model.getIncidentPlaceNum(),
                model.getDescription(),
                model.getPassengerNum(),
                model.getStaffNum(),
                model.getMeasuresTaken()
        );
    }

    public static IncidentViewModel toIncidentViewModel(IncidentResponseDTO dto) {
        return new IncidentViewModel(
                dto.getId(),
                dto.getIncidentDate(),
                dto.getIncidentPlaceNum(),
                dto.getDescription(),
                dto.getPassengerNum(),
                dto.getStaffNum(),
                dto.getMeasuresTaken()
        );
    }

    public static List<IncidentViewModel> toIncidentViewModelList(List<IncidentResponseDTO> dtoList) {
        return dtoList.stream()
                .map(IncidentMapper::toIncidentViewModel)
                .collect(Collectors.toList());
    }

}