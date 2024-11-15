package org.example.controller;

import org.example.dto.SecurityObjectRequestDTO;
import org.example.dto.SecurityObjectResponseDTO;
import org.example.service.SecurityObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/object")
public class SecurityObjectController {

    @Autowired
    SecurityObjectService securityObjectService;

    @GetMapping
    public ResponseEntity<List<SecurityObjectResponseDTO>> getAllIncidents(){
        List<SecurityObjectResponseDTO> securityObjects = securityObjectService.getAllSecurityObject();
        return new ResponseEntity<>(securityObjects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecurityObjectResponseDTO> getIncidentById(@PathVariable Long id){
        SecurityObjectResponseDTO securityObjectResponseDTO = securityObjectService.getSecurityObjectById(id);
        return new ResponseEntity<>(securityObjectResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SecurityObjectResponseDTO> createIncident(
            @RequestBody SecurityObjectRequestDTO securityObjectRequestDTO) {
        SecurityObjectResponseDTO createdSecurityObject = securityObjectService.createSecurityObject(
                securityObjectRequestDTO);
        return new ResponseEntity<>(createdSecurityObject, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SecurityObjectResponseDTO> updateFlight(
            @PathVariable Long id,
            @RequestBody SecurityObjectRequestDTO securityObjectDetails){
        SecurityObjectResponseDTO updatedSecurityObject = securityObjectService.updateSecurityObject(
                id,
                securityObjectDetails);
        return new ResponseEntity<>(updatedSecurityObject, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecurityObject(@PathVariable Long id) {
        securityObjectService.deleteSecurityObject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }

}