package com.example.demo;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public abstract class WeatherObject extends Canvas {

    protected GraphicsContext gc;

    public WeatherObject(double x, double y, double width, double height) {
        super(width, height);

        setLayoutX(x);
        setLayoutY(y);

        gc = getGraphicsContext2D();
    }

    public abstract void draw();

    public abstract void move();

    public void moveLeft() {
        setLayoutX(getLayoutX() - 20);
    }
}
