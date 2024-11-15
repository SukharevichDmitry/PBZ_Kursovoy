package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SecurityStaffResponseDTO {
    private Long id;

    private String fullName;
    private String post;
    private String contacts;
    private String shift;
    private String areaOfResponsibility;
}