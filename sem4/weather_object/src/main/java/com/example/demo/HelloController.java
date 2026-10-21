package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class HelloController {

    @FXML
    private Pane rootPane;

    @FXML
    private Button rainButton;

    private final Random random = new Random();

    private boolean rainStopped = false;

    @FXML
    public void initialize() {
        Timeline timer = new Timeline(
                new KeyFrame(
                        Duration.millis(20),
                        event -> moveObjects()
                )
        );

        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }

    private void moveObjects() {
        Node[] objects = rootPane.getChildren().toArray(new Node[0]);

        for (Node node : objects) {
            if (node instanceof WeatherObject) {
                ((WeatherObject) node).move();
            }
        }
    }

    private double randomX(double objectWidth) {
        double width = rootPane.getWidth();

        if (width <= 0) {
            width = rootPane.getPrefWidth();
        }

        return random.nextDouble() * Math.max(1, width - objectWidth);
    }

    private double randomY() {
        double height = rootPane.getHeight();

        if (height <= 0) {
            height = rootPane.getPrefHeight();
        }

        return 30 + random.nextDouble() * Math.max(1, height - 100);
    }

    @FXML
    private void addOblak() {
        Cloud cloud = new Cloud(
                randomX(100),
                10 + random.nextDouble() * 60
        );

        rootPane.getChildren().add(cloud);
    }

    @FXML
    private void addRaindrop() {
        Raindrop raindrop = new Raindrop(
                randomX(20),
                randomY()
        );

        raindrop.setStopped(rainStopped);

        rootPane.getChildren().add(raindrop);
    }

    @FXML
    private void addSnowflake() {
        Snowflake snowflake = new Snowflake(
                randomX(30),
                randomY()
        );

        rootPane.getChildren().add(snowflake);
    }

    @FXML
    private void stopOrStartRain() {
        rainStopped = !rainStopped;

        for (Node node : rootPane.getChildren()) {
            if (node instanceof Raindrop) {
                ((Raindrop) node).setStopped(rainStopped);
            }
        }

        if (rainStopped) {
            rainButton.setText("Spusti dážď");
        } else {
            rainButton.setText("Zastav dážď");
        }
    }

    @FXML
    private void changeCloudsToBlue() {
        for (Node node : rootPane.getChildren()) {
            if (node instanceof Cloud) {
                ((Cloud) node).changeColorToBlue();
            }
        }
    }

    @FXML
    private void moveAllLeft() {
        for (Node node : rootPane.getChildren()) {
            if (node instanceof WeatherObject) {
                ((WeatherObject) node).moveLeft();
            }
        }
    }
}
