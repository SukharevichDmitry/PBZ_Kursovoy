package org.example.view;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import org.example.dto.FlightResponseDTO;
import org.example.mapper.FlightMapper;

public class SecurityObjectViewModel {

    private final SimpleLongProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty type;
    private final SimpleStringProperty description;
    private final SimpleStringProperty accessLevel;

    public FlightViewModel fromDTO(FlightResponseDTO dto) {
        return FlightMapper.toFlightViewModel(dto);
    }

    public SecurityObjectViewModel() {
        this.id = new SimpleLongProperty();
        this.name = new SimpleStringProperty();
        this.type = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.accessLevel = new SimpleStringProperty();
    }

    public SecurityObjectViewModel(Long id, String name, String type, String description,
                           String accessLevel) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.description = new SimpleStringProperty(description);
        this.accessLevel = new SimpleStringProperty(accessLevel);
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

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public SimpleStringProperty typeProperty() {
        return type;
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

    public String getAccessLevel() {
        return accessLevel.get();
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel.set(accessLevel);
    }

    public SimpleStringProperty accessLevelProperty() {
        return accessLevel;
    }

}
