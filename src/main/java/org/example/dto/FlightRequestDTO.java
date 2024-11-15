package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FlightRequestDTO {

    private Long raceNumber;
    private String departurePoint;
    private String arrivalPoint;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private String aviaCompany;
}