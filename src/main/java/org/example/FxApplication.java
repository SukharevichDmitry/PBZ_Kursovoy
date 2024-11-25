package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class FxApplication {

    @Autowired
    private MainController mainController;  // Пример инъекции Spring контроллера

    public void init(Stage primaryStage) throws Exception {

        // Загружаем FXML файл
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("sample.fxml"));
        loader.setController(mainController);  // Устанавливаем контроллер Spring
        StackPane root = loader.load();

        // Создаем сцену и устанавливаем ее в окно
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("JavaFX + Spring Boot");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
