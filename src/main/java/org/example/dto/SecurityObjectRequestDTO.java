package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityObjectRequestDTO {

    private String name;
    private String type;
    private String description;
    private short accessLevel;
}