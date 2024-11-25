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

    private LocalDate incidentDate;
    private Long incidentPlaceId;
    private String description;
    private Long passengerId;
    private Long staffId;
    private String measuresTaken;

}