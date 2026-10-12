package sk.robotapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("robot-view.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);
        stage.setTitle("Robot - JavaFX");
        stage.setMinWidth(500); stage.setMinHeight(400);
        stage.setScene(scene); stage.show();
        loader.<RobotController>getController().bindScene(scene);
    }
    public static void main(String[] args) { launch(args); }
}
