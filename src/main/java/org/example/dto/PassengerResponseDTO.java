package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class PassengerResponseDTO {
    private Long id;

    private String fullName;
    private Long passportNumber;
    private LocalDate birthDate;
    private String gender;
    private String contactInfo;
}