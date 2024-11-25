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
public class PassengerRequestDTO {

    private String fullName;
    private String passportNumber;
    private String birthDate;
    private String gender;
    private String contactInfo;
}