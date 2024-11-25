package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SecurityObjectResponseDTO {
    private Long id;

    private String name;
    private String type;
    private String description;
    private String accessLevel;
}