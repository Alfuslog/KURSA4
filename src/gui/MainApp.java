package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/warehouse_view.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Складской учёт");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void show() {
        Application.launch(MainApp.class);
    }
}
