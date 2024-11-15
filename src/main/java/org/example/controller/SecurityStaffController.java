package org.example.controller;

import org.example.dto.SecurityStaffRequestDTO;
import org.example.dto.SecurityStaffResponseDTO;
import org.example.service.SecurityStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class SecurityStaffController {

    @Autowired
    SecurityStaffService securityStaffService;

    @GetMapping
    public ResponseEntity<List<SecurityStaffResponseDTO>> getAllIncidents(){
        List<SecurityStaffResponseDTO> securityStaffs = securityStaffService.getAllSecurityStaff();
        return new ResponseEntity<>(securityStaffs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecurityStaffResponseDTO> getIncidentById(@PathVariable Long id){
        SecurityStaffResponseDTO securityStaffResponseDTO = securityStaffService.getSecurityStaffById(id);
        return new ResponseEntity<>(securityStaffResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SecurityStaffResponseDTO> createIncident(
            @RequestBody SecurityStaffRequestDTO securityStaffRequestDTO) {
        SecurityStaffResponseDTO createdSecurityStaff = securityStaffService.createSecurityStaff(
                securityStaffRequestDTO);
        return new ResponseEntity<>(createdSecurityStaff, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SecurityStaffResponseDTO> updateFlight(
            @PathVariable Long id,
            @RequestBody SecurityStaffRequestDTO securityStaffDetails){
        SecurityStaffResponseDTO updatedSecurityStaff = securityStaffService.updateSecurityStaff(
                id,
                securityStaffDetails);
        return new ResponseEntity<>(updatedSecurityStaff, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecurityStaff(@PathVariable Long id) {
        securityStaffService.deleteSecurityStaff(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }

}