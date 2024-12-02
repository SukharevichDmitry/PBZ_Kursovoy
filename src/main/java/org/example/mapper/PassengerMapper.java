package org.example.mapper;

import org.example.dto.PassengerResponseDTO;
import org.example.dto.PassengerRequestDTO;
import org.example.entity.Passenger;
import org.example.view.PassengerViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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


    public static PassengerResponseDTO toResponseDTO(PassengerViewModel model) {
        return new PassengerResponseDTO(
                model.getId(),
                model.getFullName(),
                model.getPassportNumber(),
                model.getBirthDate(),
                model.getGender(),
                model.getContactInfo()
        );
    }

    public static PassengerViewModel toPassengerViewModel(PassengerResponseDTO dto) {
        return new PassengerViewModel(
                dto.getId(),
                dto.getFullName(),
                dto.getPassportNumber(),
                dto.getBirthDate(),
                dto.getGender(),
                dto.getContactInfo()
        );
    }

    public static List<PassengerViewModel> toPassengerViewModelList(List<PassengerResponseDTO> dtoList) {
        return dtoList.stream()
                .map(PassengerMapper::toPassengerViewModel)
                .collect(Collectors.toList());
    }
}
