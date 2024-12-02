package org.example.view;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import org.example.dto.FlightResponseDTO;
import org.example.dto.PassengerResponseDTO;
import org.example.mapper.FlightMapper;
import org.example.mapper.PassengerMapper;

public class PassengerViewModel {

    private final SimpleLongProperty id;
    private final SimpleStringProperty fullName;
    private final SimpleStringProperty passportNumber;
    private final SimpleStringProperty birthDate;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty contactInfo;

    public PassengerViewModel fromDTO(PassengerResponseDTO dto) {
        return PassengerMapper.toPassengerViewModel(dto);
    }

    public PassengerViewModel() {
        this.id = new SimpleLongProperty();
        this.fullName = new SimpleStringProperty();
        this.passportNumber = new SimpleStringProperty();
        this.birthDate = new SimpleStringProperty();
        this.gender = new SimpleStringProperty();
        this.contactInfo = new SimpleStringProperty();
    }

    public PassengerViewModel(Long id, String fullName, String passportNumber, String birthDate,
                           String gender, String contactInfo) {
        this.id = new SimpleLongProperty(id);
        this.fullName = new SimpleStringProperty(fullName);
        this.passportNumber = new SimpleStringProperty(passportNumber);
        this.birthDate = new SimpleStringProperty(birthDate);
        this.gender = new SimpleStringProperty(gender);
        this.contactInfo = new SimpleStringProperty(contactInfo);
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

    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public SimpleStringProperty fullNameProperty() {
        return fullName;
    }

    public String getPassportNumber() {
        return passportNumber.get();
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber.set(passportNumber);
    }

    public SimpleStringProperty passportNumberProperty() {
        return passportNumber;
    }

    public String getBirthDate() {
        return birthDate.get();
    }

    public void setBirthDate(String birthDate) {
        this.birthDate.set(birthDate);
    }

    public SimpleStringProperty birthDateProperty() {
        return birthDate;
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public String getContactInfo() {
        return contactInfo.get();
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo.set(contactInfo);
    }

    public SimpleStringProperty contactInfoProperty() {
        return contactInfo;
    }

}
