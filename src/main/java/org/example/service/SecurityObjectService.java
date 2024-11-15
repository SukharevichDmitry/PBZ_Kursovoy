package org.example.service;

import org.example.dto.SecurityObjectRequestDTO;
import org.example.dto.SecurityObjectResponseDTO;
import org.example.entity.SecurityObject;
import org.example.exception.FlightNotFoundException;
import org.example.exception.IncidentNotFoundException;
import org.example.mapper.SecurityObjectMapper;
import org.example.repository.SecurityObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecurityObjectService {

    @Autowired
    private SecurityObjectRepository securityObjectRepository;

    @Autowired
    private SecurityObjectMapper securityObjectMapper;

    public List<SecurityObjectResponseDTO> getAllSecurityObject() {
        List<SecurityObject> securityObjects = securityObjectRepository.findAll();

        return securityObjects.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private SecurityObjectResponseDTO convertToResponseDTO(SecurityObject securityObject) {
        return new SecurityObjectResponseDTO(
                securityObject.getId(),
                securityObject.getName(),
                securityObject.getType(),
                securityObject.getDescription(),
                securityObject.getAccessLevel()
        );
    }

    public SecurityObjectResponseDTO getSecurityObjectById(Long id) {
        return securityObjectMapper.toResponseDTO(securityObjectRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id)));
    }

    public SecurityObjectResponseDTO createSecurityObject(SecurityObjectRequestDTO securityObjectRequestDTO) {

        SecurityObject securityObject = securityObjectMapper.toEntity(securityObjectRequestDTO);
        securityObjectRepository.save(securityObject);

        return securityObjectMapper.toResponseDTO(securityObject);
    }

    public SecurityObjectResponseDTO updateSecurityObject(Long id, SecurityObjectRequestDTO securityObjectDetails) {
        SecurityObject securityObject = securityObjectRepository.findById(id)
                .orElseThrow(() -> new IncidentNotFoundException(id));

        securityObject.setDescription(securityObjectDetails.getDescription());
        securityObject.setName(securityObjectDetails.getName());
        securityObject.setAccessLevel(securityObjectDetails.getAccessLevel());
        securityObject.setType(securityObjectDetails.getType());

        SecurityObject updatedSecurityObject = securityObjectRepository.save(securityObject);

        return securityObjectMapper.toResponseDTO(securityObject);
    }

    public void deleteSecurityObject(Long id) {
        if (!securityObjectRepository.existsById(id)) {
            throw new IncidentNotFoundException(id);
        }
        securityObjectRepository.deleteById(id);
    }
}
