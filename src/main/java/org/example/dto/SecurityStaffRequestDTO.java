package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityStaffRequestDTO {
    private String fullName;
    private String post;
    private String contacts;
    private String shift;
    private String areaOfResponsibility;
}