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
    private Button stopButton;

    private final Random random = new Random();

    private boolean animalsStopped = false;

    @FXML
    public void initialize() {
        Timeline timer = new Timeline(
                new KeyFrame(
                        Duration.millis(20),
                        event -> moveAnimals()
                )
        );

        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }

    private void moveAnimals() {
        for (Node node : rootPane.getChildren()) {
            if (node instanceof Animal) {
                ((Animal) node).move();
            }
        }
    }

    private double randomX(double reserve) {
        double width = rootPane.getWidth();

        if (width <= 0) {
            width = rootPane.getPrefWidth();
        }

        return 20 + random.nextDouble()
                * Math.max(1, width - reserve - 40);
    }

    private double randomY(double reserve) {
        double height = rootPane.getHeight();

        if (height <= 0) {
            height = rootPane.getPrefHeight();
        }

        return 20 + random.nextDouble()
                * Math.max(1, height - reserve - 40);
    }

    @FXML
    private void addHerbivore() {

        Herbivore herbivore = new Herbivore(
                randomX(170),
                randomY(170)
        );

        herbivore.setStopped(animalsStopped);

        rootPane.getChildren().add(herbivore);
    }

    @FXML
    private void addCarnivore() {
        Carnivore carnivore = new Carnivore(
                randomX(60),
                randomY(60)
        );

        carnivore.setStopped(animalsStopped);

        rootPane.getChildren().add(carnivore);
    }

    @FXML
    private void changeCarnivoreDirection() {
        for (Node node : rootPane.getChildren()) {
            if (node instanceof Carnivore) {
                ((Carnivore) node).changeDirection();
            }
        }
    }

    @FXML
    private void stopOrStartAnimals() {
        animalsStopped = !animalsStopped;

        for (Node node : rootPane.getChildren()) {
            if (node instanceof Animal) {
                ((Animal) node).setStopped(animalsStopped);
            }
        }

        if (animalsStopped) {
            stopButton.setText("Spusti všetky zvieratá");
        } else {
            stopButton.setText("Zastav všetky zvieratá");
        }
    }
}
