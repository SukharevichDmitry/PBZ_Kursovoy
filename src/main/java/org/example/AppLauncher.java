package org.example;

import javafx.application.Application;
import javafx.scene.image.Image;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppLauncher extends Application {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        // Запуск Spring Boot приложения в отдельном потоке
        new Thread(() -> {
            context = SpringApplication.run(SpringBootApp.class, args);
        }).start();

        // Запуск JavaFX приложения
        launch(args);
    }

    @Override
    public void start(javafx.stage.Stage primaryStage) throws Exception {

        FxApplication fxApp = new FxApplication();
        fxApp.init(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (context != null) {
            context.close();
        }
    }
}
