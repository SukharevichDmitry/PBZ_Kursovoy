package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
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