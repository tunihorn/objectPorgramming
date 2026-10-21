package com.example.demo;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public abstract class Animal extends Canvas {

    protected GraphicsContext gc;
    protected double speed = 2;
    protected boolean stopped = false;

    public Animal(double x, double y, double width, double height) {
        super(width, height);

        setLayoutX(x);
        setLayoutY(y);

        gc = getGraphicsContext2D();
    }

    public abstract void draw();

    public abstract void move();

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}
