package org.example.service;

import org.example.dto.SecurityStaffRequestDTO;
import org.example.dto.SecurityStaffResponseDTO;
import org.example.entity.SecurityStaff;
import org.example.exception.FlightNotFoundException;
import org.example.exception.IncidentNotFoundException;
import org.example.mapper.SecurityStaffMapper;
import org.example.repository.SecurityStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecurityStaffService {

    @Autowired
    private SecurityStaffRepository securityStaffRepository;

    @Autowired
    private SecurityStaffMapper securityStaffMapper;

    public List<SecurityStaffResponseDTO> getAllSecurityStaff() {
        List<SecurityStaff> securityStaffs = securityStaffRepository.findAll();

        return securityStaffs.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private SecurityStaffResponseDTO convertToResponseDTO(SecurityStaff securityStaff) {
        return new SecurityStaffResponseDTO(
                securityStaff.getId(),
                securityStaff.getFullName(),
                securityStaff.getPost(),
                securityStaff.getContacts(),
                securityStaff.getShift(),
                securityStaff.getAreaOfResponsibility()
        );
    }

    public SecurityStaffResponseDTO getSecurityStaffById(Long id) {
        return securityStaffMapper.toResponseDTO(securityStaffRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id)));
    }

    public SecurityStaffResponseDTO createSecurityStaff(SecurityStaffRequestDTO securityStaffRequestDTO) {

        SecurityStaff securityStaff = securityStaffMapper.toEntity(securityStaffRequestDTO);
        securityStaffRepository.save(securityStaff);

        return securityStaffMapper.toResponseDTO(securityStaff);
    }

    public SecurityStaffResponseDTO updateSecurityStaff(Long id, SecurityStaffRequestDTO securityStaffDetails) {
        SecurityStaff securityStaff = securityStaffRepository.findById(id)
                .orElseThrow(() -> new IncidentNotFoundException(id));

        securityStaff.setFullName(securityStaffDetails.getFullName());
        securityStaff.setPost(securityStaffDetails.getPost());
        securityStaff.setContacts(securityStaffDetails.getContacts());
        securityStaff.setShift(securityStaffDetails.getShift());
        securityStaff.setAreaOfResponsibility(securityStaffDetails.getAreaOfResponsibility());

        SecurityStaff updatedSecurityStaff = securityStaffRepository.save(securityStaff);

        return securityStaffMapper.toResponseDTO(securityStaff);
    }

    public void deleteSecurityStaff(Long id) {
        if (!securityStaffRepository.existsById(id)) {
            throw new IncidentNotFoundException(id);
        }
        securityStaffRepository.deleteById(id);
    }
}
