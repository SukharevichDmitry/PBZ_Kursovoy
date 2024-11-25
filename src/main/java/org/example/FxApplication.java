package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FxApplication {

    @Autowired
    private MainController mainController;  // Инъекция Spring контроллера

    public void init(Stage primaryStage) throws Exception {

        // Загружаем FXML файл
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
        loader.setController(mainController);  // Устанавливаем контроллер Spring
        VBox root = loader.load(); // Корневой элемент теперь VBox

        // Создаем сцену и устанавливаем ее в окно
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Airport API");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
