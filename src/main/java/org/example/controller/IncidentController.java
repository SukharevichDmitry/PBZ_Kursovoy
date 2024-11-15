package org.example.controller;

import org.example.dto.IncidentRequestDTO;
import org.example.dto.IncidentResponseDTO;
import org.example.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incident")
public class IncidentController {

    @Autowired
    IncidentService incidentService;

    @GetMapping
    public ResponseEntity<List<IncidentResponseDTO>> getAllIncidents(){
        List<IncidentResponseDTO> incidents = incidentService.getAllIncidents();
        return new ResponseEntity<>(incidents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidentResponseDTO> getIncidentById(@PathVariable Long id){
        IncidentResponseDTO incidentResponseDTO = incidentService.getIncidentById(id);
        return new ResponseEntity<>(incidentResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IncidentResponseDTO> createIncident(@RequestBody IncidentRequestDTO incidentRequestDTO) {
        IncidentResponseDTO createdIncident = incidentService.createIncident(incidentRequestDTO);
        return new ResponseEntity<>(createdIncident, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncidentResponseDTO> updateFlight(
            @PathVariable Long id,
            @RequestBody IncidentRequestDTO incidentDetails){
        IncidentResponseDTO updatedIncident = incidentService.updateIncident(id, incidentDetails);
        return new ResponseEntity<>(updatedIncident, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }

}