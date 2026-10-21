package com.example.demo;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Snowflake extends WeatherObject {

    private double verticalSpeed = 2;
    private double angle = 0;

    public Snowflake(double x, double y) {
        super(x, y, 24, 24);
        draw();
    }

    @Override
    public void draw() {
        gc.clearRect(0, 0, getWidth(), getHeight());

        gc.setStroke(Color.DEEPSKYBLUE);

        gc.strokeLine(12, 2, 12, 22);
        gc.strokeLine(2, 12, 22, 12);
        gc.strokeLine(4, 4, 20, 20);
        gc.strokeLine(20, 4, 4, 20);
    }

    @Override
    public void move() {
        angle += 0.08;

        setLayoutY(getLayoutY() + verticalSpeed);
        setLayoutX(getLayoutX() + Math.sin(angle) * 0.8);

        if (getParent() != null) {
            double parentHeight = getParent().getLayoutBounds().getHeight();

            if (getLayoutY() > parentHeight) {
                ((Pane) getParent()).getChildren().remove(this);
            }
        }
    }
}
