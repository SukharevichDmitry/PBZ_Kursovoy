package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PassengerRequestDTO {

    private String fullName;
    private Long passportNumber;
    private LocalDate birthDate;
    private String gender;
    private String contactInfo;
}