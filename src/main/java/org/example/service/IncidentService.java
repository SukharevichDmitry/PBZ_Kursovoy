package org.example.service;

import org.example.dto.FlightResponseDTO;
import org.example.dto.IncidentRequestDTO;
import org.example.dto.IncidentResponseDTO;
import org.example.entity.Incident;
import org.example.exception.FlightNotFoundException;
import org.example.exception.IncidentNotFoundException;
import org.example.mapper.IncidentMapper;
import org.example.repository.IncidentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidentService {
    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private IncidentMapper incidentMapper;

    public List<IncidentResponseDTO> getAllIncidents() {
        List<Incident> incidents = incidentRepository.findAll();

        return incidents.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private IncidentResponseDTO convertToResponseDTO(Incident incident) {
        return new IncidentResponseDTO(
                incident.getId(),
                incident.getIncidentDate(),
                incident.getIncidentPlaceNum(),
                incident.getDescription(),
                incident.getPassengerNum(),
                incident.getStaffNum(),
                incident.getMeasuresTaken()
        );
    }

    public IncidentResponseDTO getIncidentById(Long id) {
        return incidentMapper.toResponseDTO(incidentRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id)));
    }

    public IncidentResponseDTO createIncident(IncidentRequestDTO incidentRequestDTO) {

        System.out.println("Creating incident with DTO: {" + incidentRequestDTO + "}");
        Incident incident = incidentMapper.toEntity(incidentRequestDTO);
        incidentRepository.save(incident);

        return incidentMapper.toResponseDTO(incident);
    }

    public IncidentResponseDTO updateIncident(Long id, IncidentRequestDTO incidentDetails) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new IncidentNotFoundException(id));

        incident.setIncidentDate(incidentDetails.getIncidentDate());
        incident.setIncidentPlaceNum(incidentDetails.getIncidentPlaceNum());
        incident.setDescription(incidentDetails.getDescription());
        incident.setPassengerNum(incidentDetails.getPassengerNum());
        incident.setStaffNum(incidentDetails.getStaffNum());
        incident.setMeasuresTaken(incidentDetails.getMeasuresTaken());

        Incident updatedIncident = incidentRepository.save(incident);

        return incidentMapper.toResponseDTO(incident);
    }

    public void deleteIncident(Long id) {
        if (!incidentRepository.existsById(id)) {
            throw new IncidentNotFoundException(id);
        }
        incidentRepository.deleteById(id);
    }
}
