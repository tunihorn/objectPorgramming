package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class HelloController {

    @FXML
    private Pane rootPane;

    private final Random random = new Random();

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
        for (Node node : rootPane.getChildren()) {
            Object object = node.getUserData();

            if (object instanceof GeometricShape) {
                ((GeometricShape) object).move();
            }
        }
    }

    private double randomX() {
        double width = rootPane.getWidth();

        if (width <= 0) {
            width = rootPane.getPrefWidth();
        }

        return 50 + random.nextDouble() * Math.max(1, width - 150);
    }

    private double randomY() {
        double height = rootPane.getHeight();

        if (height <= 0) {
            height = rootPane.getPrefHeight();
        }

        return 50 + random.nextDouble() * Math.max(1, height - 150);
    }

    @FXML
    private void addRectangle() {
        MyRectangle rectangle =
                new MyRectangle(randomX(), randomY());

        rootPane.getChildren().add(
                rectangle.getShape()
        );
    }

    @FXML
    private void addCircle() {
        MyCircle circle =
                new MyCircle(randomX(), randomY());

        rootPane.getChildren().add(
                circle.getShape()
        );
    }

    @FXML
    private void addSquare() {
        MySquare square =
                new MySquare(randomX(), randomY());

        rootPane.getChildren().add(
                square.getShape()
        );
    }

    @FXML
    private void changeCirclesToBlue() {
        for (Node node : rootPane.getChildren()) {
            Object object = node.getUserData();

            if (object instanceof MyCircle) {
                ((MyCircle) object).changeColorToBlue();
            }
        }
    }

    @FXML
    private void moveAllDown() {
        for (Node node : rootPane.getChildren()) {
            Object object = node.getUserData();

            if (object instanceof GeometricShape) {
                ((GeometricShape) object).moveDown();
            }
        }
    }
}
