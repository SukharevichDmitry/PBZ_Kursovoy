package org.example.mapper;

import org.example.dto.FlightRequestDTO;
import org.example.dto.FlightResponseDTO;
import org.example.entity.Flight;
import org.example.view.FlightViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Flight toEntity(FlightRequestDTO flightRequestDTO) {
        return modelMapper.map(flightRequestDTO, Flight.class);
    }

    public FlightResponseDTO toResponseDTO(Flight flight) {
        return modelMapper.map(flight, FlightResponseDTO.class);
    }

    public static FlightResponseDTO toResponseDTO(FlightViewModel model) {
        return new FlightResponseDTO(
                model.getId(),
                model.getRaceNumber(),
                model.getDeparturePoint(),
                model.getArrivalPoint(),
                model.getDepartureDate(),
                model.getArrivalDate(),
                model.getAviaCompany()
        );
    }

    public static FlightViewModel toFlightViewModel(FlightResponseDTO dto) {
        return new FlightViewModel(
                dto.getId(),
                dto.getRaceNumber(),
                dto.getDeparturePoint(),
                dto.getArrivalPoint(),
                dto.getDepartureDate(),
                dto.getArrivalDate(),
                dto.getAviaCompany()
        );
    }

    public static List<FlightViewModel> toFlightViewModelList(List<FlightResponseDTO> dtoList) {
        return dtoList.stream()
                .map(FlightMapper::toFlightViewModel)
                .collect(Collectors.toList());
    }

}
