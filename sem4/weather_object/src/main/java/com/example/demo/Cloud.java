package com.example.demo;

import javafx.scene.paint.Color;

public class Cloud extends WeatherObject {

    private double speed = 0.8;
    private Color color = Color.LIGHTGRAY;

    public Cloud(double x, double y) {
        super(x, y, 90, 50);
        draw();
    }

    @Override
    public void draw() {
        gc.clearRect(0, 0, getWidth(), getHeight());

        gc.setFill(color);

        gc.fillOval(8, 20, 35, 22);
        gc.fillOval(25, 10, 40, 30);
        gc.fillOval(50, 20, 30, 22);
    }

    @Override
    public void move() {
        setLayoutX(getLayoutX() + speed);

        if (getParent() != null) {
            double maxX = getParent().getLayoutBounds().getWidth();

            if (getLayoutX() > maxX) {
                setLayoutX(-getWidth());
            }
        }
    }

    public void changeColorToBlue() {
        color = Color.LIGHTBLUE;
        draw();
    }
}
