package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IncidentResponseDTO {
    private Long id;

    private String incidentDate;
    private String incidentPlaceId;
    private String description;
    private String passengerId;
    private String staffId;
    private String measuresTaken;

}