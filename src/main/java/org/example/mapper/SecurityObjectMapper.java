package org.example.mapper;

import org.example.dto.SecurityObjectResponseDTO;
import org.example.dto.SecurityObjectRequestDTO;
import org.example.dto.SecurityObjectResponseDTO;
import org.example.entity.SecurityObject;
import org.example.view.SecurityObjectViewModel;
import org.example.view.SecurityObjectViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SecurityObjectMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public SecurityObject toEntity(SecurityObjectRequestDTO securityObjectRequestDTO) {
        return modelMapper.map(securityObjectRequestDTO, SecurityObject.class);
    }

    public SecurityObjectResponseDTO toResponseDTO(SecurityObject securityObject) {
        return modelMapper.map(securityObject, SecurityObjectResponseDTO.class);
    }

    public static SecurityObjectResponseDTO toResponseDTO(SecurityObjectViewModel model) {
        return new SecurityObjectResponseDTO(
                model.getId(),
                model.getName(),
                model.getType(),
                model.getDescription(),
                model.getAccessLevel()
        );
    }

    public static SecurityObjectViewModel toSecurityObjectViewModel(SecurityObjectResponseDTO dto) {
        return new SecurityObjectViewModel(
                dto.getId(),
                dto.getName(),
                dto.getType(),
                dto.getDescription(),
                dto.getAccessLevel()
        );
    }

    public static List<SecurityObjectViewModel> toSecurityObjectViewModelList(List<SecurityObjectResponseDTO> dtoList) {
        return dtoList.stream()
                .map(SecurityObjectMapper::toSecurityObjectViewModel)
                .collect(Collectors.toList());
    }

}
