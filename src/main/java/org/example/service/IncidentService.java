package org.example.service;

import org.example.dto.FlightResponseDTO;
import org.example.dto.IncidentRequestDTO;
import org.example.dto.IncidentResponseDTO;
import org.example.entity.Incident;
import org.example.exception.FlightNotFoundException;
import org.example.exception.IncidentNotFoundException;
import org.example.mapper.IncidentMapper;
import org.example.repository.IncidentRepository;
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
                incident.getIncidentPlaceId(),
                incident.getDescription(),
                incident.getPassengerId(),
                incident.getStaffId(),
                incident.getMeasuresTaken()
        );
    }

    public IncidentResponseDTO getIncidentById(Long id) {
        return incidentMapper.toResponseDTO(incidentRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id)));
    }

    public IncidentResponseDTO createIncident(IncidentRequestDTO incidentRequestDTO) {

        Incident incident = incidentMapper.toEntity(incidentRequestDTO);
        incidentRepository.save(incident);

        return incidentMapper.toResponseDTO(incident);
    }

    public IncidentResponseDTO updateIncident(Long id, IncidentRequestDTO incidentDetails) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new IncidentNotFoundException(id));

        incident.setIncidentDate(incidentDetails.getIncidentDate());
        incident.setIncidentPlaceId(incidentDetails.getIncidentPlaceId());
        incident.setDescription(incidentDetails.getDescription());
        incident.setPassengerId(incidentDetails.getPassengerId());
        incident.setStaffId(incidentDetails.getStaffId());
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
