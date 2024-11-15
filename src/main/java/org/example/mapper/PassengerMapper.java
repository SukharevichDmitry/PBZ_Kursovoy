package org.example.mapper;

import org.example.dto.PassengerRequestDTO;
import org.example.dto.PassengerResponseDTO;
import org.example.entity.Passenger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PassengerMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    // Преобразование из PassengerRequestDTO в Passenger (для создания/обновления)
    public Passenger toEntity(PassengerRequestDTO passengerRequestDTO) {
        return modelMapper.map(passengerRequestDTO, Passenger.class);
    }

    // Преобразование из Passenger в PassengerResponseDTO (для ответа)
    public PassengerResponseDTO toResponseDTO(Passenger passenger) {
        return modelMapper.map(passenger, PassengerResponseDTO.class);
    }

}
