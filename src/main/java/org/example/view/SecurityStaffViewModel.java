package org.example.view;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import org.example.dto.FlightResponseDTO;
import org.example.mapper.FlightMapper;

public class SecurityStaffViewModel {

    private final SimpleLongProperty id;
    private final SimpleStringProperty fullName;
    private final SimpleStringProperty post;
    private final SimpleStringProperty contacts;
    private final SimpleStringProperty shift;
    private final SimpleStringProperty areaOfResponsibility;

    public FlightViewModel fromDTO(FlightResponseDTO dto) {
        return FlightMapper.toFlightViewModel(dto);
    }

    public SecurityStaffViewModel() {
        this.id = new SimpleLongProperty();
        this.fullName = new SimpleStringProperty();
        this.post = new SimpleStringProperty();
        this.contacts = new SimpleStringProperty();
        this.shift = new SimpleStringProperty();
        this.areaOfResponsibility = new SimpleStringProperty();
    }

    public SecurityStaffViewModel(Long id, String fullName, String post, String contacts,
                                   String shift, String areaOfResponsibility) {
        this.id = new SimpleLongProperty(id);
        this.fullName = new SimpleStringProperty(fullName);
        this.post = new SimpleStringProperty(post);
        this.contacts = new SimpleStringProperty(contacts);
        this.shift = new SimpleStringProperty(shift);
        this.areaOfResponsibility = new SimpleStringProperty(areaOfResponsibility);
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

    public String getPost() {
        return post.get();
    }

    public void setPost(String post) {
        this.post.set(post);
    }

    public SimpleStringProperty postProperty() {
        return post;
    }

    public String getContacts() {
        return contacts.get();
    }

    public void setContacts(String contacts) {
        this.contacts.set(contacts);
    }

    public SimpleStringProperty contactsProperty() {
        return contacts;
    }

    public String getShift() {
        return shift.get();
    }

    public void setShift(String shift) {
        this.shift.set(shift);
    }

    public SimpleStringProperty shiftProperty() {
        return shift;
    }

    public String getAreaOfResponsibility() {
        return areaOfResponsibility.get();
    }

    public void setAreaOfResponsibility(String areaOfResponsibility) {
        this.areaOfResponsibility.set(areaOfResponsibility);
    }

    public SimpleStringProperty areaOfResponsibilityProperty() {
        return areaOfResponsibility;
    }

}
