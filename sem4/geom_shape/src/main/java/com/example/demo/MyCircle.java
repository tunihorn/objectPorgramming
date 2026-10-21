package com.example.demo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyCircle extends GeometricShape {

    private double angle = 0;
    private final double radius = 35;

    public MyCircle(double x, double y) {
        shape = new Circle(20, Color.RED);

        shape.setLayoutX(x);
        shape.setLayoutY(y);

        draw();
        registerShape();
    }

    @Override
    public void draw() {
        ((Circle) shape).setStroke(Color.BLACK);
    }

    @Override
    public void move() {
        angle += 0.05;

        shape.setTranslateX(radius * Math.cos(angle));
        shape.setTranslateY(radius * Math.sin(angle));
    }

    public void changeColorToBlue() {
        ((Circle) shape).setFill(Color.BLUE);
    }
}
