package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import org.example.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    @FXML
    private Button flightButton, passengerButton, incidentButton, sobjectButton, sstaffButton;

    @FXML
    private StackPane contentPane;

    @FXML
    private Label label;

    private Button selectedButton;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String flightControllerUrl = "http://localhost:8080/flight";
    private final String passengerControllerUrl = "http://localhost:8080/passenger";
    private final String incidentControllerUrl = "http://localhost:8080/incident";
    private final String sstaffControllerUrl = "http://localhost:8080/staff";
    private final String sobjectControllerUrl = "http://localhost:8080/object";

    @FXML
    private TextArea outputArea; // Область вывода

    public void initialize() {
        label.setText("Привет, мир!");
        selectedButton = flightButton;
        highlightButton(selectedButton);
    }

    @FXML
    public void onFlightClick() {
        updateContentForFlight();
        highlightButton(flightButton);
    }

    private void updateContentForFlight() {
        contentPane.getChildren().clear();

        VBox flightControls = new VBox(10);
        flightControls.setStyle("-fx-padding: 10;");

        HBox buttonBox = new HBox(10);
        Button getAllButton = new Button("Get All");
        Button getByIdButton = new Button("Get by ID");
        Button createButton = new Button("Create");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        buttonBox.getChildren().addAll(getAllButton, getByIdButton, createButton, updateButton, deleteButton);

        VBox inputBox = new VBox(10);
        TextField raceNumberField = new TextField();
        raceNumberField.setPromptText("Race Number");

        TextField departurePointField = new TextField();
        departurePointField.setPromptText("Departure Point");

        TextField arrivalPointField = new TextField();
        arrivalPointField.setPromptText("Arrival Point");

        TextField departureDateField = new TextField();
        departureDateField.setPromptText("Departure Date (YYYY-MM-DD)");

        TextField arrivalDateField = new TextField();
        arrivalDateField.setPromptText("Arrival Date (YYYY-MM-DD)");

        TextField aviaCompanyField = new TextField();
        aviaCompanyField.setPromptText("Avia Company");

        TextField idField = new TextField();
        idField.setPromptText("ID");

        inputBox.getChildren().addAll(
                raceNumberField, departurePointField, arrivalPointField,
                departureDateField, arrivalDateField, aviaCompanyField, idField
        );

        getAllButton.setOnAction(event -> {
            String response = restTemplate.getForObject(flightControllerUrl, String.class);
            outputArea.setText("Get All Flights Response:\n" + response);
        });

        getByIdButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                String response = restTemplate.getForObject(flightControllerUrl + "/" + idField.getText(), String.class);
                outputArea.setText("Get Flight by ID Response:\n" + response);
            } else {
                outputArea.setText("ID field cannot be empty!");
            }
        });

        createButton.setOnAction(event -> {
            FlightRequestDTO flightRequestDTO = new FlightRequestDTO(
                    raceNumberField.getText(),
                    departurePointField.getText(),
                    arrivalPointField.getText(),
                    departureDateField.getText(),
                    arrivalDateField.getText(),
                    aviaCompanyField.getText()
            );
            ResponseEntity<String> response = restTemplate.postForEntity(flightControllerUrl, flightRequestDTO, String.class);
            outputArea.setText("Create Flight Response:\n" + response.getBody());
        });

        updateButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                FlightRequestDTO flightRequestDTO = new FlightRequestDTO(
                        raceNumberField.getText(),
                        departurePointField.getText(),
                        arrivalPointField.getText(),
                        departureDateField.getText(),
                        arrivalDateField.getText(),
                        aviaCompanyField.getText()
                );
                restTemplate.put(flightControllerUrl + "/" + idField.getText(), flightRequestDTO);
                outputArea.setText("Flight with ID " + idField.getText() + " updated successfully.");
            } else {
                outputArea.setText("ID field cannot be empty for update!");
            }
        });

        deleteButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                restTemplate.delete(flightControllerUrl + "/" + idField.getText());
                outputArea.setText("Flight with ID " + idField.getText() + " deleted successfully.");
            } else {
                outputArea.setText("ID field cannot be empty for deletion!");
            }
        });

        flightControls.getChildren().addAll(buttonBox, inputBox);

        contentPane.getChildren().add(flightControls);
    }

    @FXML
    public void onPassengerClick() {
        updateContentForPassenger();
        highlightButton(passengerButton);
    }

    private void updateContentForPassenger() {
        contentPane.getChildren().clear();

        VBox passengerControls = new VBox(10);
        passengerControls.setStyle("-fx-padding: 10;");

        HBox buttonBox = new HBox(10);
        Button getAllButton = new Button("Get All");
        Button getByIdButton = new Button("Get by ID");
        Button getByPassportNumberButton = new Button("Get By passport number");
        Button createButton = new Button("Create");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        buttonBox.getChildren().addAll(getAllButton, getByIdButton, getByPassportNumberButton, createButton, updateButton, deleteButton);

        VBox inputBox = new VBox(10);
        TextField fullName = new TextField();
        fullName.setPromptText("Full name");

        TextField passportNumber = new TextField();
        passportNumber.setPromptText("Passport number");

        TextField birthDate = new TextField();
        birthDate.setPromptText("Birth date (YYYY-MM-DD)");

        TextField gender = new TextField();
        gender.setPromptText("Gender");

        TextField contactInfo = new TextField();
        contactInfo.setPromptText("Contact info");

        TextField idField = new TextField();
        idField.setPromptText("ID");

        inputBox.getChildren().addAll(
                fullName, passportNumber, birthDate,
                gender, contactInfo, idField
        );

        getAllButton.setOnAction(event -> {
            String response = restTemplate.getForObject(passengerControllerUrl, String.class);
            outputArea.setText("Get All Passengers Response:\n" + response);
        });

        getByIdButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                String response = restTemplate.getForObject(passengerControllerUrl + "/" + idField.getText(), String.class);
                outputArea.setText("Get Passenger by ID Response:\n" + response);
            } else {
                outputArea.setText("ID field cannot be empty!");
            }
        });

        getByPassportNumberButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                String response = restTemplate.getForObject(passengerControllerUrl + "/" + idField.getText(), String.class);
                outputArea.setText("Get Passenger by Passport Number:\n" + response);
            } else {
                outputArea.setText("Passport number cannot be empty!");
            }
        });

        createButton.setOnAction(event -> {
            PassengerRequestDTO passengerRequestDTO = new PassengerRequestDTO(
                    fullName.getText(),
                    passportNumber.getText(),
                    birthDate.getText(),
                    gender.getText(),
                    contactInfo.getText()
            );
            ResponseEntity<String> response = restTemplate.postForEntity(passengerControllerUrl,
                    passengerRequestDTO, String.class);
            outputArea.setText("Create Passenger Response:\n" + response.getBody());
        });

        updateButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                PassengerRequestDTO passengerRequestDTO = new PassengerRequestDTO(
                        fullName.getText(),
                        passportNumber.getText(),
                        birthDate.getText(),
                        gender.getText(),
                        contactInfo.getText()
                );
                restTemplate.put(passengerControllerUrl + "/" + idField.getText(), passengerRequestDTO);
                outputArea.setText("Passenger with ID " + idField.getText() + " updated successfully.");
            } else {
                outputArea.setText("ID field cannot be empty for update!");
            }
        });

        deleteButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                restTemplate.delete(passengerControllerUrl + "/" + idField.getText());
                outputArea.setText("Passenger with ID " + idField.getText() + " deleted successfully.");
            } else {
                outputArea.setText("ID field cannot be empty for deletion!");
            }
        });

        passengerControls.getChildren().addAll(buttonBox, inputBox);

        contentPane.getChildren().add(passengerControls);
    }

    @FXML
    public void onIncidentClick() {
        updateContentForIncident();
        highlightButton(incidentButton);
    }

    private void updateContentForIncident() {
        contentPane.getChildren().clear();

        VBox incidentControls = new VBox(10);
        incidentControls.setStyle("-fx-padding: 10;");

        HBox buttonBox = new HBox(10);
        Button getAllButton = new Button("Get All");
        Button getByIdButton = new Button("Get by ID");
        Button createButton = new Button("Create");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        buttonBox.getChildren().addAll(getAllButton, getByIdButton, createButton, updateButton, deleteButton);

        VBox inputBox = new VBox(10);
        TextField incidentDate = new TextField();
        incidentDate.setPromptText("Incident date(YYYY-MM-DD)");

        TextField incidentPlaceId = new TextField();
        incidentPlaceId.setPromptText("Incident place ID");

        TextField description = new TextField();
        description.setPromptText("Description");

        TextField passengerId = new TextField();
        passengerId.setPromptText("Passenger ID");

        TextField staffId = new TextField();
        staffId.setPromptText("Staff ID");

        TextField measuresTaken = new TextField();
        measuresTaken.setPromptText("Measures taken");

        TextField idField = new TextField();
        idField.setPromptText("ID");

        inputBox.getChildren().addAll(
                incidentDate, incidentPlaceId, description,
                passengerId, staffId, measuresTaken, idField
        );

        getAllButton.setOnAction(event -> {
            String response = restTemplate.getForObject(incidentControllerUrl, String.class);
            outputArea.setText("Get All Incidents Response:\n" + response);
        });

        getByIdButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                String response = restTemplate.getForObject(incidentControllerUrl + "/" + idField.getText(), String.class);
                outputArea.setText("Get Incident by ID Response:\n" + response);
            } else {
                outputArea.setText("ID field cannot be empty!");
            }
        });

        createButton.setOnAction(event -> {
            IncidentRequestDTO incidentRequestDTO    = new IncidentRequestDTO(
                    incidentDate.getText(),
                    incidentPlaceId.getText(),
                    description.getText(),
                    passengerId.getText(),
                    staffId.getText(),
                    measuresTaken.getText()
            );
            ResponseEntity<String> response = restTemplate.postForEntity(incidentControllerUrl,
                    incidentRequestDTO, String.class);
            outputArea.setText("Create Incident Response:\n" + response.getBody());
        });

        updateButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                IncidentRequestDTO incidentRequestDTO = new IncidentRequestDTO(
                        incidentDate.getText(),
                        incidentPlaceId.getText(),
                        description.getText(),
                        passengerId.getText(),
                        staffId.getText(),
                        measuresTaken.getText()
                );
                restTemplate.put(incidentControllerUrl + "/" + idField.getText(), incidentRequestDTO);
                outputArea.setText("Incident with ID " + idField.getText() + " updated successfully.");
            } else {
                outputArea.setText("ID field cannot be empty for update!");
            }
        });

        deleteButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                restTemplate.delete(incidentControllerUrl + "/" + idField.getText());
                outputArea.setText("Incident with ID " + idField.getText() + " deleted successfully.");
            } else {
                outputArea.setText("ID field cannot be empty for deletion!");
            }
        });

        incidentControls.getChildren().addAll(buttonBox, inputBox);

        contentPane.getChildren().add(incidentControls);
    }

    @FXML
    public void onSObjectClick() {
        updateContentForSObject();
        highlightButton(sobjectButton);
    }

    private void updateContentForSObject() {
        contentPane.getChildren().clear();

        VBox sObjectControls = new VBox(10);
        sObjectControls.setStyle("-fx-padding: 10;");

        HBox buttonBox = new HBox(10);
        Button getAllButton = new Button("Get All");
        Button getByIdButton = new Button("Get by ID");
        Button createButton = new Button("Create");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        buttonBox.getChildren().addAll(getAllButton, getByIdButton, createButton, updateButton, deleteButton);

        VBox inputBox = new VBox(10);
        TextField name = new TextField();
        name.setPromptText("Name");

        TextField type = new TextField();
        type.setPromptText("Type");

        TextField description = new TextField();
        description.setPromptText("Description");

        TextField accessLevel = new TextField();
        accessLevel.setPromptText("Access Level");

        TextField idField = new TextField();
        idField.setPromptText("ID");

        inputBox.getChildren().addAll(
                name, type, description, accessLevel
        );

        getAllButton.setOnAction(event -> {
            String response = restTemplate.getForObject(sobjectControllerUrl, String.class);
            outputArea.setText("Get All Security Objects Response:\n" + response);
        });

        getByIdButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                String response = restTemplate.getForObject(sobjectControllerUrl + "/" + idField.getText(), String.class);
                outputArea.setText("Get Security Object by ID Response:\n" + response);
            } else {
                outputArea.setText("ID field cannot be empty!");
            }
        });

        createButton.setOnAction(event -> {
            SecurityObjectRequestDTO securityObjectRequestDTO    = new SecurityObjectRequestDTO(
                    name.getText(),
                    type.getText(),
                    description.getText(),
                    accessLevel.getText()
            );
            ResponseEntity<String> response = restTemplate.postForEntity(sobjectControllerUrl,
                    securityObjectRequestDTO, String.class);
            outputArea.setText("Create Security Object Response:\n" + response.getBody());
        });

        updateButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                SecurityObjectRequestDTO securityObjectRequestDTO = new SecurityObjectRequestDTO(
                        name.getText(),
                        type.getText(),
                        description.getText(),
                        accessLevel.getText()
                );
                restTemplate.put(sobjectControllerUrl + "/" + idField.getText(), securityObjectRequestDTO);
                outputArea.setText("Security Object with ID " + idField.getText() + " updated successfully.");
            } else {
                outputArea.setText("ID field cannot be empty for update!");
            }
        });

        deleteButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                restTemplate.delete(sobjectControllerUrl + "/" + idField.getText());
                outputArea.setText("Security Object with ID " + idField.getText() + " deleted successfully.");
            } else {
                outputArea.setText("ID field cannot be empty for deletion!");
            }
        });

        sObjectControls.getChildren().addAll(buttonBox, inputBox);

        contentPane.getChildren().add(sObjectControls);
    }

    @FXML
    public void onSStaffClick() {
        updateContentForSStaff();
        highlightButton(sstaffButton);
    }

    private void updateContentForSStaff() {
        contentPane.getChildren().clear();

        VBox sStaffControls = new VBox(10);
        sStaffControls.setStyle("-fx-padding: 10;");

        HBox buttonBox = new HBox(10);
        Button getAllButton = new Button("Get All");
        Button getByIdButton = new Button("Get by ID");
        Button createButton = new Button("Create");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        buttonBox.getChildren().addAll(getAllButton, getByIdButton, createButton, updateButton, deleteButton);

        VBox inputBox = new VBox(10);
        TextField fullName = new TextField();
        fullName.setPromptText("Full Name");

        TextField post = new TextField();
        post.setPromptText("Post");

        TextField contacts = new TextField();
        contacts.setPromptText("Contacts");

        TextField shift = new TextField();
        shift.setPromptText("Shift");

        TextField areaOfResponsibility = new TextField();
        areaOfResponsibility.setPromptText("Area of Responsibility");

        TextField idField = new TextField();
        idField.setPromptText("ID");

        inputBox.getChildren().addAll(
               fullName, post, contacts, shift, areaOfResponsibility, idField
        );

        getAllButton.setOnAction(event -> {
            String response = restTemplate.getForObject(sstaffControllerUrl, String.class);
            outputArea.setText("Get All Security Staff Response:\n" + response);
        });

        getByIdButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                String response = restTemplate.getForObject(sstaffControllerUrl + "/" + idField.getText(), String.class);
                outputArea.setText("Get Security Staff by ID Response:\n" + response);
            } else {
                outputArea.setText("ID field cannot be empty!");
            }
        });

        createButton.setOnAction(event -> {
            SecurityStaffRequestDTO securityStaffRequestDTO    = new SecurityStaffRequestDTO(
                    fullName.getText(),
                    post.getText(),
                    contacts.getText(),
                    shift.getText(),
                    areaOfResponsibility.getText()
            );
            ResponseEntity<String> response = restTemplate.postForEntity(sstaffControllerUrl,
                    securityStaffRequestDTO, String.class);
            outputArea.setText("Create Security Staff Response:\n" + response.getBody());
        });

        updateButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                SecurityStaffRequestDTO securityStaffRequestDTO = new SecurityStaffRequestDTO(
                        fullName.getText(),
                        post.getText(),
                        contacts.getText(),
                        shift.getText(),
                        areaOfResponsibility.getText()
                );
                restTemplate.put(sstaffControllerUrl + "/" + idField.getText(), securityStaffRequestDTO);
                outputArea.setText("Security Staff with ID " + idField.getText() + " updated successfully.");
            } else {
                outputArea.setText("ID field cannot be empty for update!");
            }
        });

        deleteButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                restTemplate.delete(sstaffControllerUrl + "/" + idField.getText());
                outputArea.setText("Security Staff with ID " + idField.getText() + " deleted successfully.");
            } else {
                outputArea.setText("ID field cannot be empty for deletion!");
            }
        });

        sStaffControls.getChildren().addAll(buttonBox, inputBox);

        contentPane.getChildren().add(sStaffControls);
    }

    private void highlightButton(Button button) {
        // Сброс стилей для всех кнопок
        flightButton.setStyle(null);
        passengerButton.setStyle(null);
        incidentButton.setStyle(null);
        sobjectButton.setStyle(null);
        sstaffButton.setStyle(null);

        // Установка выделенного стиля
        button.setStyle("-fx-background-color: lightblue;");
        selectedButton = button;
    }
}
