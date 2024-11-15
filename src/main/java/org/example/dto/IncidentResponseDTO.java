package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class IncidentResponseDTO {
    private Long id;

    private LocalDate incidentDate;
    private Long incidentPlaceId;
    private String description;
    private Long passengerId;
    private Long staffId;
    private String measuresTaken;

}