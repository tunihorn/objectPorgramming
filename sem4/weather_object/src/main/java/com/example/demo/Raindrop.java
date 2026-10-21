package com.example.demo;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Raindrop extends WeatherObject {

    private double speed = 5;
    private boolean stopped = false;

    public Raindrop(double x, double y) {
        super(x, y, 12, 24);
        draw();
    }

    @Override
    public void draw() {
        gc.clearRect(0, 0, getWidth(), getHeight());

        gc.setFill(Color.DODGERBLUE);
        gc.fillOval(2, 5, 8, 15);

    }

    @Override
    public void move() {
        if (stopped) {
            return;
        }

        setLayoutY(getLayoutY() + speed);

        if (getParent() != null) {
            double parentHeight = getParent().getLayoutBounds().getHeight();

            if (getLayoutY() > parentHeight) {
                ((Pane) getParent()).getChildren().remove(this);
            }
        }
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}
