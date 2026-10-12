package sk.robotapp;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class RobotController {
    @FXML private Pane robotPane;
    @FXML private Label modeLabel;
    private Robot robot;

    @FXML private void initialize() {
        robot = new Robot(robotPane, 100, 100);
        robotPane.getChildren().add(robot);
        robot.setOnMouseClicked(e -> { robot.toggleAutomatic(); updateLabel(); robotPane.requestFocus(); e.consume(); });
        robotPane.setOnMouseClicked(e -> robotPane.requestFocus());
        updateLabel();
    }

    public void bindScene(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if (robot.isAutomatic()) return;
            if (e.getCode() == KeyCode.W) robot.move(Robot.Direction.UP);
            else if (e.getCode() == KeyCode.S) robot.move(Robot.Direction.DOWN);
            else if (e.getCode() == KeyCode.A) robot.move(Robot.Direction.LEFT);
            else if (e.getCode() == KeyCode.D) robot.move(Robot.Direction.RIGHT);
            updateLabel();
        });
        robotPane.requestFocus();
    }

    private void updateLabel() {
        modeLabel.setText((robot.isAutomatic() ? "AUTOMATICKÝ" : "MANUÁLNY") + " režim | smer: " + robot.getDirection());
    }
}
