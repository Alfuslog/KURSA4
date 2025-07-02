package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Загружаем FXML и получаем корневой элемент (BorderPane)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("warehouse_view.fxml"));
            Parent root = loader.load();

            // Создаем сцену с загруженным layout
            Scene scene = new Scene(root);

            // Настраиваем окно
            primaryStage.setTitle("Система управления складом");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(600);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Ошибка загрузки FXML файла: " + e.getMessage());
        }
    }

    // Точка входа при запуске из Main.java
    public static void show() {
        launch();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
