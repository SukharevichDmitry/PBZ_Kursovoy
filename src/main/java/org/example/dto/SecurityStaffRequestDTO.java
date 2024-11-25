package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SecurityStaffRequestDTO {
    private String fullName;
    private String post;
    private String contacts;
    private String shift;
    private String areaOfResponsibility;
}