package org.example.mapper;

import org.example.dto.SecurityObjectRequestDTO;
import org.example.dto.SecurityObjectResponseDTO;
import org.example.entity.SecurityObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SecurityObjectMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public SecurityObject toEntity(SecurityObjectRequestDTO securityObjectRequestDTO) {
        return modelMapper.map(securityObjectRequestDTO, SecurityObject.class);
    }

    public SecurityObjectResponseDTO toResponseDTO(SecurityObject securityObject) {
        return modelMapper.map(securityObject, SecurityObjectResponseDTO.class);
    }

}
