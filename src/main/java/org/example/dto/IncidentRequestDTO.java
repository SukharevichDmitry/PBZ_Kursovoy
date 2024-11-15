package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class IncidentRequestDTO {

    private LocalDate incidentDate;
    private Long incidentPlaceId;
    private String description;
    private Long passengerId;
    private Long staffId;
    private String measuresTaken;
}