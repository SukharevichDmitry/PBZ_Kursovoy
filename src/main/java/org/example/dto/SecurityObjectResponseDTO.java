package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SecurityObjectResponseDTO {
    private Long id;

    private String name;
    private String type;
    private String description;
    private short accessLevel;
}