package org.example.view;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import org.example.dto.FlightResponseDTO;
import org.example.mapper.FlightMapper;

public class FlightViewModel {

    private final SimpleLongProperty id;
    private final SimpleStringProperty raceNumber;
    private final SimpleStringProperty departurePoint;
    private final SimpleStringProperty arrivalPoint;
    private final SimpleStringProperty departureDate;
    private final SimpleStringProperty arrivalDate;
    private final SimpleStringProperty aviaCompany;

    public FlightViewModel fromDTO(FlightResponseDTO dto) {
        return FlightMapper.toFlightViewModel(dto);
    }

    public FlightViewModel() {
        this.id = new SimpleLongProperty();
        this.raceNumber = new SimpleStringProperty();
        this.departurePoint = new SimpleStringProperty();
        this.arrivalPoint = new SimpleStringProperty();
        this.departureDate = new SimpleStringProperty();
        this.arrivalDate = new SimpleStringProperty();
        this.aviaCompany = new SimpleStringProperty();
    }

    public FlightViewModel(Long id, String raceNumber, String departurePoint, String arrivalPoint,
                            String departureDate, String arrivalDate, String aviaCompany) {
        this.id = new SimpleLongProperty(id);
        this.raceNumber = new SimpleStringProperty(raceNumber);
        this.departurePoint = new SimpleStringProperty(departurePoint);
        this.arrivalPoint = new SimpleStringProperty(arrivalPoint);
        this.departureDate = new SimpleStringProperty(departureDate);
        this.arrivalDate = new SimpleStringProperty(arrivalDate);
        this.aviaCompany = new SimpleStringProperty(aviaCompany);
    }

    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public String getRaceNumber() {
        return raceNumber.get();
    }

    public void setRaceNumber(String raceNumber) {
        this.raceNumber.set(raceNumber);
    }

    public SimpleStringProperty raceNumberProperty() {
        return raceNumber;
    }

    public String getDeparturePoint() {
        return departurePoint.get();
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint.set(departurePoint);
    }

    public SimpleStringProperty departurePointProperty() {
        return departurePoint;
    }

    public String getArrivalPoint() {
        return arrivalPoint.get();
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint.set(arrivalPoint);
    }

    public SimpleStringProperty arrivalPointProperty() {
        return arrivalPoint;
    }

    public String getDepartureDate() {
        return departureDate.get();
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate.set(departureDate);
    }

    public SimpleStringProperty departureDateProperty() {
        return departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate.get();
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate.set(arrivalDate);
    }

    public SimpleStringProperty arrivalDateProperty() {
        return arrivalDate;
    }

    public String getAviaCompany() {
        return aviaCompany.get();
    }

    public void setAviaCompany(String aviaCompany) {
        this.aviaCompany.set(aviaCompany);
    }

    public SimpleStringProperty aviaCompanyProperty() {
        return aviaCompany;
    }
}
