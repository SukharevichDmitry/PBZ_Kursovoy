package org.example.mapper;

import org.example.dto.SecurityStaffResponseDTO;
import org.example.dto.SecurityStaffRequestDTO;
import org.example.dto.SecurityStaffResponseDTO;
import org.example.entity.SecurityStaff;
import org.example.view.SecurityStaffViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SecurityStaffMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public SecurityStaff toEntity(SecurityStaffRequestDTO securityStaffRequestDTO) {
        return modelMapper.map(securityStaffRequestDTO, SecurityStaff.class);
    }

    public SecurityStaffResponseDTO toResponseDTO(SecurityStaff securityStaff) {
        return modelMapper.map(securityStaff, SecurityStaffResponseDTO.class);
    }

    public static SecurityStaffResponseDTO toResponseDTO(SecurityStaffViewModel model) {
        return new SecurityStaffResponseDTO(
                model.getId(),
                model.getFullName(),
                model.getPost(),
                model.getContacts(),
                model.getShift(),
                model.getAreaOfResponsibility()
        );
    }

    public static SecurityStaffViewModel toSecurityStaffViewModel(SecurityStaffResponseDTO dto) {
        return new SecurityStaffViewModel(
                dto.getId(),
                dto.getFullName(),
                dto.getPost(),
                dto.getContacts(),
                dto.getShift(),
                dto.getAreaOfResponsibility()
        );
    }

    public static List<SecurityStaffViewModel> toSecurityStaffViewModelList(List<SecurityStaffResponseDTO> dtoList) {
        return dtoList.stream()
                .map(SecurityStaffMapper::toSecurityStaffViewModel)
                .collect(Collectors.toList());
    }

}
