package org.example.view;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import org.example.dto.IncidentResponseDTO;
import org.example.mapper.IncidentMapper;

public class IncidentViewModel {

    private final SimpleLongProperty id;
    private final SimpleStringProperty incidentDate;
    private final SimpleStringProperty incidentPlaceNum;
    private final SimpleStringProperty description;
    private final SimpleStringProperty passengerNum;
    private final SimpleStringProperty staffNum;
    private final SimpleStringProperty measuresTaken;

    public IncidentViewModel fromDTO(IncidentResponseDTO dto) {
        return IncidentMapper.toIncidentViewModel(dto);
    }

    public IncidentViewModel() {
        this.id = new SimpleLongProperty();
        this.incidentDate = new SimpleStringProperty();
        this.incidentPlaceNum = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.passengerNum = new SimpleStringProperty();
        this.staffNum = new SimpleStringProperty();
        this.measuresTaken = new SimpleStringProperty();
    }

    public IncidentViewModel(Long id, String incidentDate, String incidentPlaceNum, String description,
                             String passengerNum, String staffNum, String measuresTaken) {
        this.id = new SimpleLongProperty(id);
        this.incidentDate = new SimpleStringProperty(incidentDate);
        this.incidentPlaceNum = new SimpleStringProperty(incidentPlaceNum);
        this.description = new SimpleStringProperty(description);
        this.passengerNum = new SimpleStringProperty(passengerNum);
        this.staffNum = new SimpleStringProperty(staffNum);
        this.measuresTaken = new SimpleStringProperty(measuresTaken);
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

    public String getIncidentDate() {
        return incidentDate.get();
    }

    public void setIncidentDate(String incidentDate) {
        this.incidentDate.set(incidentDate);
    }

    public SimpleStringProperty incidentDateProperty() {
        return incidentDate;
    }

    public String getIncidentPlaceNum() {
        return incidentPlaceNum.get();
    }

    public void setIncidentPlaceNum(String incidentPlaceNum) {
        this.incidentPlaceNum.set(incidentPlaceNum);
    }

    public SimpleStringProperty incidentPlaceNumProperty() {
        return incidentPlaceNum;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public String getPassengerNum() {
        return passengerNum.get();
    }

    public void setPassengerNum(String passengerNum) {
        this.passengerNum.set(passengerNum);
    }

    public SimpleStringProperty passengerNumProperty() {
        return passengerNum;
    }

    public String getStaffNum() {
        return staffNum.get();
    }

    public void setStaffNum(String staffNum) {
        this.staffNum.set(staffNum);
    }

    public SimpleStringProperty staffNumProperty() {
        return staffNum;
    }

    public String getMeasuresTaken() {
        return measuresTaken.get();
    }

    public void setMeasuresTaken(String measuresTaken) {
        this.measuresTaken.set(measuresTaken);
    }

    public SimpleStringProperty measuresTakenProperty() {
        return measuresTaken;
    }
}
