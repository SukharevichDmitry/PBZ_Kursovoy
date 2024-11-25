package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {
    @FXML
    private Label label;

    public void initialize() {
        label.setText("Привет, мир!");
    }

    @FXML
    public void onButtonClick() {
        label.setText("Кнопка нажата");
    }
}

