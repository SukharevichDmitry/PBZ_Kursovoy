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
public class FlightResponseDTO {

    private Long id;

    private Long raceNumber;
    private String departurePoint;
    private String arrivalPoint;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private String aviaCompany;
}