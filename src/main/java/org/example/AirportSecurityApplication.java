package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AirportSecurityApplication extends Application {

    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        // Запуск Spring Boot приложения
        launch(args);  // Вызов метода launch для старта JavaFX
    }

    @Override
    public void start(Stage primaryStage) {
        // Инициализация Spring контекста
        context = SpringApplication.run(AirportSecurityApplication.class);

        // Здесь инициализируйте JavaFX компоненты, например, сцены и т.д.
        primaryStage.setTitle("Airport Security System");

        // Создание сцены и отображение окна
        primaryStage.show();
    }

    @SneakyThrows
    @Override
    public void stop() {
        super.stop();
        // Закрытие Spring контекста при завершении работы приложения
        context.close();
    }
}
