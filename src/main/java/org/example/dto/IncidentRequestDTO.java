package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncidentRequestDTO {

    private String incidentDate;
    private String incidentPlaceId;
    private String description;
    private String passengerId;
    private String staffId;
    private String measuresTaken;
}