package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import org.example.dto.*;
import org.example.mapper.*;
import org.example.view.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

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
    private final String flightControllerUrl = "http://127.0.0.1:8080/flight";
    private final String passengerControllerUrl = "http://127.0.0.1:8080/passenger";
    private final String incidentControllerUrl = "http://127.0.0.1:8080/incident";
    private final String sstaffControllerUrl = "http://127.0.0.1:8080/staff";
    private final String sobjectControllerUrl = "http://127.0.0.1:8080/object";

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

        TableView<FlightViewModel> tableView = new TableView<>();

        TableColumn<FlightViewModel, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());

        TableColumn<FlightViewModel, String> raceNumberColumn = new TableColumn<>("Race Number");
        raceNumberColumn.setCellValueFactory(cellData -> cellData.getValue().raceNumberProperty());

        TableColumn<FlightViewModel, String> departurePointColumn = new TableColumn<>("Departure Point");
        departurePointColumn.setCellValueFactory(cellData -> cellData.getValue().departurePointProperty());

        TableColumn<FlightViewModel, String> arrivalPointColumn = new TableColumn<>("Arrival Point");
        arrivalPointColumn.setCellValueFactory(cellData -> cellData.getValue().arrivalPointProperty());

        TableColumn<FlightViewModel, String> departureDateColumn = new TableColumn<>("Departure Date");
        departureDateColumn.setCellValueFactory(cellData -> cellData.getValue().departureDateProperty());

        TableColumn<FlightViewModel, String> arrivalDateColumn = new TableColumn<>("Arrival Date");
        arrivalDateColumn.setCellValueFactory(cellData -> cellData.getValue().arrivalDateProperty());

        TableColumn<FlightViewModel, String> aviaCompanyColumn = new TableColumn<>("Avia Company");
        aviaCompanyColumn.setCellValueFactory(cellData -> cellData.getValue().aviaCompanyProperty());

        tableView.getColumns().addAll(
                idColumn, raceNumberColumn, departurePointColumn, arrivalPointColumn,
                departureDateColumn, arrivalDateColumn, aviaCompanyColumn
        );


        // Обработчик кнопки Get All
        getAllButton.setOnAction(event -> {
            try {
                // Получаем данные с сервера
                FlightResponseDTO[] flights = restTemplate.getForObject(flightControllerUrl, FlightResponseDTO[].class);
                List<FlightResponseDTO> flightsList = Arrays.asList(flights);
                // Очищаем таблицу перед добавлением новых данных
                tableView.getItems().clear();

                tableView.getItems().addAll(FlightMapper.toFlightViewModelList(flightsList));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Обработчик кнопки Get by ID
        getByIdButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                try {
                    FlightResponseDTO flight = restTemplate.getForObject(
                            flightControllerUrl + "/" + idField.getText(), FlightResponseDTO.class);

                    tableView.getItems().clear();
                    if (flight != null) {
                        tableView.getItems().add(FlightMapper.toFlightViewModel(flight));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
            ResponseEntity<String> response = restTemplate.postForEntity(flightControllerUrl,
                    flightRequestDTO, String.class);
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

        flightControls.getChildren().addAll(buttonBox, inputBox, tableView);

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
        Button getByPassportNumberButton = new Button("Get by Passport Number");
        Button createButton = new Button("Create");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        buttonBox.getChildren().addAll(getAllButton, getByIdButton, getByPassportNumberButton, createButton, updateButton, deleteButton);

        VBox inputBox = new VBox(10);
        TextField fullName = new TextField();
        fullName.setPromptText("Full Name");

        TextField passportNumber = new TextField();
        passportNumber.setPromptText("Passport Number");

        TextField birthDate = new TextField();
        birthDate.setPromptText("Birth Date (YYYY-MM-DD)");

        TextField gender = new TextField();
        gender.setPromptText("Gender");

        TextField contactInfo = new TextField();
        contactInfo.setPromptText("Contact Info");

        TextField idField = new TextField();
        idField.setPromptText("ID");

        inputBox.getChildren().addAll(fullName, passportNumber, birthDate, gender, contactInfo, idField);

        TableView<PassengerViewModel> tableView = new TableView<>();

        TableColumn<PassengerViewModel, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());

        TableColumn<PassengerViewModel, String> fullNameColumn = new TableColumn<>("Full Name");
        fullNameColumn.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());

        TableColumn<PassengerViewModel, String> passportNumberColumn = new TableColumn<>("Passport Number");
        passportNumberColumn.setCellValueFactory(cellData -> cellData.getValue().passportNumberProperty());

        TableColumn<PassengerViewModel, String> birthDateColumn = new TableColumn<>("Birth Date");
        birthDateColumn.setCellValueFactory(cellData -> cellData.getValue().birthDateProperty());

        TableColumn<PassengerViewModel, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(cellData -> cellData.getValue().genderProperty());

        TableColumn<PassengerViewModel, String> contactInfoColumn = new TableColumn<>("Contact Info");
        contactInfoColumn.setCellValueFactory(cellData -> cellData.getValue().contactInfoProperty());

        tableView.getColumns().addAll(idColumn, fullNameColumn, passportNumberColumn, birthDateColumn, genderColumn, contactInfoColumn);

        // Обработчик кнопки Get All
        getAllButton.setOnAction(event -> {
            try {
                PassengerResponseDTO[] passengers = restTemplate.getForObject(passengerControllerUrl, PassengerResponseDTO[].class);
                List<PassengerResponseDTO> passengersList = Arrays.asList(passengers);
                tableView.getItems().clear();
                tableView.getItems().addAll(PassengerMapper.toPassengerViewModelList(passengersList));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Обработчик кнопки Get by ID
        getByIdButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                try {
                    PassengerResponseDTO passenger = restTemplate.getForObject(passengerControllerUrl + "/" + idField.getText(), PassengerResponseDTO.class);
                    tableView.getItems().clear();
                    if (passenger != null) {
                        tableView.getItems().add(PassengerMapper.toPassengerViewModel(passenger));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Обработчик кнопки Get by Passport Number
        getByPassportNumberButton.setOnAction(event -> {
            if (!passportNumber.getText().isEmpty()) {
                try {
                    PassengerResponseDTO passenger = restTemplate.getForObject(passengerControllerUrl + "/passport/" + passportNumber.getText(), PassengerResponseDTO.class);
                    tableView.getItems().clear();
                    if (passenger != null) {
                        tableView.getItems().add(PassengerMapper.toPassengerViewModel(passenger));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Обработчик кнопки Create
        createButton.setOnAction(event -> {
            PassengerRequestDTO passengerRequestDTO = new PassengerRequestDTO(
                    fullName.getText(),
                    passportNumber.getText(),
                    birthDate.getText(),
                    gender.getText(),
                    contactInfo.getText()
            );
            ResponseEntity<String> response = restTemplate.postForEntity(passengerControllerUrl, passengerRequestDTO, String.class);
            outputArea.setText("Create Passenger Response:\n" + response.getBody());
        });

        // Обработчик кнопки Update
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
            }
        });

        // Обработчик кнопки Delete
        deleteButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                restTemplate.delete(passengerControllerUrl + "/" + idField.getText());
                outputArea.setText("Passenger with ID " + idField.getText() + " deleted successfully.");
            }
        });

        passengerControls.getChildren().addAll(buttonBox, inputBox, tableView);
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

        TableView<IncidentViewModel> tableView = new TableView<>();

        TableColumn<IncidentViewModel, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());

        TableColumn<IncidentViewModel, String> dateColumn = new TableColumn<>("Incident Date");
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().incidentDateProperty());

        TableColumn<IncidentViewModel, String> placeColumn = new TableColumn<>("Place ID");
        placeColumn.setCellValueFactory(cellData -> cellData.getValue().incidentPlaceNumProperty());

        TableColumn<IncidentViewModel, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        TableColumn<IncidentViewModel, String> passengerIdColumn = new TableColumn<>("Passenger ID");
        passengerIdColumn.setCellValueFactory(cellData -> cellData.getValue().passengerNumProperty());

        TableColumn<IncidentViewModel, String> staffIdColumn = new TableColumn<>("Staff ID");
        staffIdColumn.setCellValueFactory(cellData -> cellData.getValue().staffNumProperty());

        TableColumn<IncidentViewModel, String> measuresColumn = new TableColumn<>("Measures Taken");
        measuresColumn.setCellValueFactory(cellData -> cellData.getValue().measuresTakenProperty());

        tableView.getColumns().addAll(idColumn, dateColumn, placeColumn, descriptionColumn, passengerIdColumn, staffIdColumn, measuresColumn);

        // Get All Incidents
        getAllButton.setOnAction(event -> {
            try {
                IncidentResponseDTO[] incidents = restTemplate.getForObject(incidentControllerUrl, IncidentResponseDTO[].class);
                List<IncidentResponseDTO> incidentList = Arrays.asList(incidents);
                tableView.getItems().clear();
                tableView.getItems().addAll(IncidentMapper.toIncidentViewModelList(incidentList));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Get Incident by ID
        getByIdButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                try {
                    IncidentResponseDTO incident = restTemplate.getForObject(incidentControllerUrl + "/" + idField.getText(), IncidentResponseDTO.class);
                    tableView.getItems().clear();
                    if (incident != null) {
                        tableView.getItems().add(IncidentMapper.toIncidentViewModel(incident));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Create Incident
        createButton.setOnAction(event -> {
            IncidentRequestDTO incidentRequestDTO = new IncidentRequestDTO(
                    incidentDate.getText(),
                    incidentPlaceId.getText(),
                    description.getText(),
                    passengerId.getText(),
                    staffId.getText(),
                    measuresTaken.getText()
            );
            try {
                ResponseEntity<String> response = restTemplate.postForEntity(incidentControllerUrl, incidentRequestDTO, String.class);
                outputArea.setText("Create Incident Response:\n" + response.getBody());
            } catch (HttpServerErrorException ex) {
                outputArea.setText("Error creating incident: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
            }

        });

        // Update Incident
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
            }
        });

        // Delete Incident
        deleteButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                restTemplate.delete(incidentControllerUrl + "/" + idField.getText());
                outputArea.setText("Incident with ID " + idField.getText() + " deleted successfully.");
            }
        });

        incidentControls.getChildren().addAll(buttonBox, inputBox, tableView);
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

        inputBox.getChildren().addAll(name, type, description, accessLevel, idField);

        TableView<SecurityObjectViewModel> tableView = new TableView<>();

        TableColumn<SecurityObjectViewModel, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());

        TableColumn<SecurityObjectViewModel, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<SecurityObjectViewModel, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

        TableColumn<SecurityObjectViewModel, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        TableColumn<SecurityObjectViewModel, String> accessLevelColumn = new TableColumn<>("Access Level");
        accessLevelColumn.setCellValueFactory(cellData -> cellData.getValue().accessLevelProperty());

        tableView.getColumns().addAll(idColumn, nameColumn, typeColumn, descriptionColumn, accessLevelColumn);

        // Get All Security Objects
        getAllButton.setOnAction(event -> {
            try {
                SecurityObjectResponseDTO[] securityObjects = restTemplate.getForObject(sobjectControllerUrl, SecurityObjectResponseDTO[].class);
                List<SecurityObjectResponseDTO> securityObjectList = Arrays.asList(securityObjects);
                tableView.getItems().clear();
                tableView.getItems().addAll(SecurityObjectMapper.toSecurityObjectViewModelList(securityObjectList));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Get Security Object by ID
        getByIdButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                try {
                    SecurityObjectResponseDTO securityObject = restTemplate.getForObject(sobjectControllerUrl + "/" + idField.getText(), SecurityObjectResponseDTO.class);
                    tableView.getItems().clear();
                    if (securityObject != null) {
                        tableView.getItems().add(SecurityObjectMapper.toSecurityObjectViewModel(securityObject));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Create Security Object
        createButton.setOnAction(event -> {
            SecurityObjectRequestDTO securityObjectRequestDTO = new SecurityObjectRequestDTO(
                    name.getText(),
                    type.getText(),
                    description.getText(),
                    accessLevel.getText()
            );
            try {
                ResponseEntity<String> response = restTemplate.postForEntity(sobjectControllerUrl, securityObjectRequestDTO, String.class);
                outputArea.setText("Create Security Object Response:\n" + response.getBody());
            } catch (HttpServerErrorException ex) {
                outputArea.setText("Error creating security object: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
            }
        });

        // Update Security Object
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
            }
        });

        // Delete Security Object
        deleteButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                restTemplate.delete(sobjectControllerUrl + "/" + idField.getText());
                outputArea.setText("Security Object with ID " + idField.getText() + " deleted successfully.");
            }
        });

        sObjectControls.getChildren().addAll(buttonBox, inputBox, tableView);
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

        inputBox.getChildren().addAll(fullName, post, contacts, shift, areaOfResponsibility, idField);

        TableView<SecurityStaffViewModel> tableView = new TableView<>();

        TableColumn<SecurityStaffViewModel, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());

        TableColumn<SecurityStaffViewModel, String> fullNameColumn = new TableColumn<>("Full Name");
        fullNameColumn.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());

        TableColumn<SecurityStaffViewModel, String> postColumn = new TableColumn<>("Post");
        postColumn.setCellValueFactory(cellData -> cellData.getValue().postProperty());

        TableColumn<SecurityStaffViewModel, String> contactsColumn = new TableColumn<>("Contacts");
        contactsColumn.setCellValueFactory(cellData -> cellData.getValue().contactsProperty());

        TableColumn<SecurityStaffViewModel, String> shiftColumn = new TableColumn<>("Shift");
        shiftColumn.setCellValueFactory(cellData -> cellData.getValue().shiftProperty());

        TableColumn<SecurityStaffViewModel, String> areaOfResponsibilityColumn = new TableColumn<>("Area of Responsibility");
        areaOfResponsibilityColumn.setCellValueFactory(cellData -> cellData.getValue().areaOfResponsibilityProperty());

        tableView.getColumns().addAll(idColumn, fullNameColumn, postColumn, contactsColumn, shiftColumn, areaOfResponsibilityColumn);

        // Get All Security Staff
        getAllButton.setOnAction(event -> {
            try {
                SecurityStaffResponseDTO[] securityStaff = restTemplate.getForObject(sstaffControllerUrl, SecurityStaffResponseDTO[].class);
                List<SecurityStaffResponseDTO> securityStaffList = Arrays.asList(securityStaff);
                tableView.getItems().clear();
                tableView.getItems().addAll(SecurityStaffMapper.toSecurityStaffViewModelList(securityStaffList));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Get Security Staff by ID
        getByIdButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                try {
                    SecurityStaffResponseDTO securityStaff = restTemplate.getForObject(sstaffControllerUrl + "/" + idField.getText(), SecurityStaffResponseDTO.class);
                    tableView.getItems().clear();
                    if (securityStaff != null) {
                        tableView.getItems().add(SecurityStaffMapper.toSecurityStaffViewModel(securityStaff));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Create Security Staff
        createButton.setOnAction(event -> {
            SecurityStaffRequestDTO securityStaffRequestDTO = new SecurityStaffRequestDTO(
                    fullName.getText(),
                    post.getText(),
                    contacts.getText(),
                    shift.getText(),
                    areaOfResponsibility.getText()
            );
            try {
                ResponseEntity<String> response = restTemplate.postForEntity(sstaffControllerUrl, securityStaffRequestDTO, String.class);
                outputArea.setText("Create Security Staff Response:\n" + response.getBody());
            } catch (HttpServerErrorException ex) {
                outputArea.setText("Error creating security staff: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
            }
        });

        // Update Security Staff
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
            }
        });

        // Delete Security Staff
        deleteButton.setOnAction(event -> {
            if (!idField.getText().isEmpty()) {
                restTemplate.delete(sstaffControllerUrl + "/" + idField.getText());
                outputArea.setText("Security Staff with ID " + idField.getText() + " deleted successfully.");
            }
        });

        sStaffControls.getChildren().addAll(buttonBox, inputBox, tableView);
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
