package org.example.mapper;

import org.example.dto.SecurityStaffRequestDTO;
import org.example.dto.SecurityStaffResponseDTO;
import org.example.entity.SecurityStaff;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SecurityStaffMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public SecurityStaff toEntity(SecurityStaffRequestDTO securityStaffRequestDTO) {
        return modelMapper.map(securityStaffRequestDTO, SecurityStaff.class);
    }

    public SecurityStaffResponseDTO toResponseDTO(SecurityStaff securityStaff) {
        return modelMapper.map(securityStaff, SecurityStaffResponseDTO.class);
    }

}
